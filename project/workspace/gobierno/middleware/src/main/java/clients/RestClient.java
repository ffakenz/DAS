package clients;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class RestClient implements ConcesionariaServiceContract {

    private final String url;
    private final HttpClient client;

    public RestClient(String url) {
        this.url = url;
        this.client = HttpClientBuilder.create().build();
    }

    private String getUrl() { return this.url; }
    private HttpClient getCliente() { return this.client; }

    private BiFunction<String, String, HttpUriRequest> HTTPFactory = (method, callTo) -> {
        URI uri = URI.create(getUrl() + callTo);
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
            default: throw new IllegalArgumentException("Invalid method: " + method);
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

    @Override
    public String consultarPlanes() {
        return call.apply("GET", "/consultarPlanes");
    }

    @Override
    public String consultarPlan(Long planId) {
        return call.apply("GET", "/consultarPlan?planId=" + planId.toString());
    }
	// TODO: Create method that will parse multiple parameters
    @Override
    public void cancelarPlan(Long planId) {
        fireAndForget.accept("PUT", "/cancelarPlan?planId=" + planId.toString());
    }
}