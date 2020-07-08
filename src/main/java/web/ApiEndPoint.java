package web;

import io.vertx.ext.web.Router;

public class ApiEndPoint {
    private final DataRoutes dr = new DataRoutes();

    void installRoutes(Router router){

        router.get("/api/test").handler(dr::returnTestResponse);

        /*
         * Create routes for the API
         *
         */
    }
}
