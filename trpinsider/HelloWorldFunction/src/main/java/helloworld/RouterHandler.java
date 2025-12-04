package helloworld;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.*;
import java.util.Map;

public class RouterHandler extends BaseHandler {

    @Override
    protected APIGatewayProxyResponseEvent process(
            APIGatewayProxyRequestEvent req, Context ctx) {

        String path = req.getPath();
        String method = req.getHttpMethod();

        // CORS (prevents 403 in browsers)
        if ("OPTIONS".equals(method)) {
            return corsResponse();
        }

        switch (path) {
            case "/":
                return ok("Welcome! Root endpoint works.");

            case "/employees":
                return ok("Hello from Lambda Niraj!");

            case "/favicon.ico":
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(204);  // empty success

            default:
                return notFound("No route found: " + path);
        }
    }

    private APIGatewayProxyResponseEvent ok(String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(body)
                .withHeaders(corsHeaders());
    }

    private APIGatewayProxyResponseEvent notFound(String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(404)
                .withBody(body)
                .withHeaders(corsHeaders());
    }

    private APIGatewayProxyResponseEvent corsResponse() {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(corsHeaders());
    }

    private Map<String, String> corsHeaders() {

        System.out.println(" Cross is running");

        return Map.of(
                "Access-Control-Allow-Origin", "*",
                "Access-Control-Allow-Headers", "*",
                "Access-Control-Allow-Methods", "GET,POST,OPTIONS"
        );
    }
}
