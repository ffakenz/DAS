package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.MiddlewareConstants.*;

public class RestClient implements ConcesionariaServiceContract {

    protected Logger log = LoggerFactory.getLogger(RestClient.class);

    private final String url;
    private final HttpClient client;

    private BiFunction<String, String, HttpUriRequest> HTTPFactory = (method, callTo) -> {

        System.out.println(getUrl() + callTo);
        URI uri = URI.create(getUrl() + callTo);
        System.out.println(uri.toString());

        switch (method) {
            case POST:
                HttpPost postReq = new HttpPost();
                postReq.setURI(uri);
                return postReq;
            case GET:
                HttpGet getReq = new HttpGet();
                getReq.setURI(uri);
                return getReq;
            case PUT:
                HttpPut putReq = new HttpPut();
                putReq.setURI(uri);
                return putReq;
            default:
                throw new IllegalArgumentException("Invalid method: " + method);
        }
    };

    private String call(final String method, final String callTo) throws ClientException {

        try {
            final HttpResponse resp = getCliente().execute(HTTPFactory.apply(method, callTo));
            final HttpEntity responseEntity = resp.getEntity();

            final int statusCode = resp.getStatusLine().getStatusCode();

            if (statusCode >= 500)
                throw new ClientException("ENDPOINT IS DOWN = " + resp.toString());
            if (statusCode >= 400)
                throw new ClientException("BAD REQUEST = " + resp.toString());

            final String jsonPlanBean = EntityUtils.toString(responseEntity);
            return jsonPlanBean;
        } catch (final IOException e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    private void fireAndForget(final String method, final String callTo) throws ClientException {
        try {
            final HttpResponse resp = getCliente().execute(HTTPFactory.apply(method, callTo));

            final int statusCode = resp.getStatusLine().getStatusCode();

            if (statusCode >= 500)
                throw new ClientException("ENDPOINT IS DOWN = " + resp.toString());
            if (statusCode >= 400)
                throw new ClientException("BAD REQUEST = " + resp.toString());

        } catch (final IOException e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    public RestClient(final String url) {
        this.url = url;
        this.client = HttpClientBuilder.create().build();
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String url = params.getOrDefault(REST_PARAM_URL, "");
        if (url.isEmpty())
            return Optional.empty();

        final RestClient restClient = new RestClient(url);
        return Optional.of(restClient);
    }

    private String getUrl() {
        return this.url;
    }

    private HttpClient getCliente() {
        return this.client;
    }

    public String getQuery(final String base, final String... params) {
        final String target = "/" + base + "?";
        final String args = Stream.of(params)
                .map(p -> p + "=%s")
                .collect(Collectors.joining("&"));
        final String arg = (params.length > 1 ? args : params[0] + "=%s");
        return target + arg;
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final Timestamp from, final Timestamp to) throws ClientException {

        final String fromParsed = nanosRepr(from.toString());
        final String toParsed = nanosRepr(to.toString());
        final String query = getQuery(CONSULTAR_PLANES, IDENTIFICADOR, FROM, TO);
        final String url = String.format(query, identificador, fromParsed, toParsed);
        final String jsonPlanBeans = call(GET, url);
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        log.info("[GET consultarPlanes][URL {}][jsonPlanBeansResponse = {}][notificationUpdates = {}]", url, jsonPlanBeans, notificationUpdates);

        return Stream.of(notificationUpdates).collect(Collectors.toList());

    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) throws ClientException {

        final String query = getQuery(CONSULTAR_PLAN, IDENTIFICADOR, PLANID);
        final String url = String.format(query, identificador, planId);
        final String jsonPlanBean = call(GET, url);
        log.info("[GET consultarPlan][URL {}][jsonPlanBean = {}]", url, jsonPlanBean);
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);
    }

    @Override
    public void cancelarPlan(final String identificador, final Long planId) throws ClientException {

        final String query = getQuery(CANCELAR_PLAN, IDENTIFICADOR, PLANID);
        final String url = String.format(query, identificador, planId);
        fireAndForget(PUT, url);
        log.info("[PUT cancelarPlan][URL {}]", url);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final String url = getQuery(HEALTH, IDENTIFICADOR);
        log.info("[GET health][URL {}]", url);
        return call(GET, String.format(url, identificador));
    }
}