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
//        String encodedUrl = "/";
//        try {
//            encodedUrl = URLEncoder.encode(getUrl() + callTo, "UTF-8");
//            System.out.println("Encoded Url: [ " + encodedUrl + " ]");
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
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
    private BiFunction<String, String, String> call = (method, callTo) -> {
        try {
            HttpResponse resp = getCliente().execute(HTTPFactory.apply(method, callTo));
            HttpEntity responseEntity = resp.getEntity();
            return EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No respuesta";
    };
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
    public List<NotificationUpdate> consultarPlanes(final String identificador, final String offset) {
        final String jsonPlanBeans =
                call.apply("GET", "/consultarPlanes?identificador=" + identificador + "&offset=" + offset);
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) {
        final String jsonPlanBean =
                call.apply("GET", "/consultarPlan?identificador=" + identificador + "&planId=" + planId.toString());
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);
    }

    // TODO: Create method that will parse multiple parameters
    @Override
    public void cancelarPlan(final String identificador, final Long planId) {
        fireAndForget.accept("PUT", "/cancelarPlan?identificador=" + identificador + "&planId=" + planId.toString());
    }

    @Override
    public String health(final String identificador) {
        final String jsonPlanBean =
                call.apply("GET", "/health?identificador=" + identificador);
        return jsonPlanBean;
    }
}