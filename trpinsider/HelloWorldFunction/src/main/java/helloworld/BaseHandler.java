package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public abstract class BaseHandler
        implements RequestHandler<APIGatewayProxyRequestEvent,
                                  APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent req, Context ctx) {

        try {
            log(ctx, "Incoming request: " + req.getPath());
            return process(req, ctx);

        } catch (Exception e) {
            log(ctx, "ERROR: " + e.getMessage());

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Internal Error: " + e.getMessage());
        }
    }

    protected abstract APIGatewayProxyResponseEvent process(
            APIGatewayProxyRequestEvent req, Context ctx);

    protected void log(Context ctx, String msg) {
        ctx.getLogger().log(msg + "\n");
    }
}
