package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestClient implements ConcesionariaServiceContract {

    private final String url;
    private final HttpClient client;

    private BiFunction<String, String, HttpUriRequest> HTTPFactory = (method, callTo) -> {

        System.out.println(getUrl() + callTo);
        URI uri = URI.create(getUrl() + callTo);
        System.out.println(uri.toString());

        switch (method) {
            case "POST":
                HttpPost postReq = new HttpPost();
                postReq.setURI(uri);
                return postReq;
            case "GET":
                HttpGet getReq = new HttpGet();
                getReq.setURI(uri);
                return getReq;
            case "PUT":
                HttpPut putReq = new HttpPut();
                putReq.setURI(uri);
                return putReq;
            default:
                throw new IllegalArgumentException("Invalid method: " + method);
        }
    };

    private Optional<String> call(String method, String callTo) {

        try {
            HttpResponse resp = getCliente().execute(HTTPFactory.apply(method, callTo));
            HttpEntity responseEntity = resp.getEntity();

            int statusCode = resp.getStatusLine().getStatusCode();

            if(statusCode == 200) {
                String jsonPlanBean = EntityUtils.toString(responseEntity);
                return Optional.of(jsonPlanBean);
            }

            return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private BiConsumer<String, String> fireAndForget = (method, callTo) -> {
        try {
            getCliente().execute(HTTPFactory.apply(method, callTo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public RestClient(final String url) {
        this.url = url;
        this.client = HttpClientBuilder.create().build();
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String url = params.getOrDefault("url", "");
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

    @Override
    public Optional<List<NotificationUpdate>> consultarPlanes(final String identificador, final String offset) {

        String url = String.format("/consultarPlanes?identificador=%s&offset=%s", identificador, offset);

        final Optional<String> jsonPlanBeans = call("GET", url);

        return jsonPlanBeans.flatMap( json -> {
            final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(json, NotificationUpdate[].class);
            return Optional.of(Stream.of(notificationUpdates).collect(Collectors.toList()));
        });

    }

    @Override
    public Optional<PlanBean> consultarPlan(final String identificador, final Long planId) {

        String url = String.format("/consultarPlan?identificador=%s&planId=%s",identificador, planId.toString());
        final Optional<String> jsonPlanBean = call("GET", url);

        return jsonPlanBean.flatMap(json -> Optional.of(JsonUtils.toObject(json, PlanBean.class)));
    }

    // TODO: Create method that will parse multiple parameters
    @Override
    public void cancelarPlan(final String identificador, final Long planId) {
        String url = String.format("/cancelarPlan?identificador=%s&planId=%s", identificador, planId.toString());
        fireAndForget.accept("PUT", url);
    }

    @Override
    public Optional<String> health(final String identificador) {
        String url = String.format("/health?identificador=%s",identificador);
        return call("GET", url);
    }
}