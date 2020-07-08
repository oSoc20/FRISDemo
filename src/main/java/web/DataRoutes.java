package web;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class DataRoutes {
    public void returnTestResponse(RoutingContext routingContext) {
        sendJson(routingContext.response().setStatusCode(200), "UhUH test successful");
    }

    private void sendJson(HttpServerResponse response, Object object){
        response.putHeader("Content-Type", "application/json;charset=utf-8")
                .end(Json.encodePrettily(object));
    }
}
