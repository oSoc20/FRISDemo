package web;

import io.vertx.ext.web.Router;

public class ApiEndPoint {
    private final DataRoutes dr = new DataRoutes();

    void installRoutes(Router router){

        /*
         * Route to test API responses
         */
        router.get("/api/test").handler(dr::returnTestResponse);

        /*
         * Get route for a number N of projects
         */
        router.get("/api/projects/:number").handler(dr::getProjects);

        /*
         * Create routes for the API
         *
         */
    }
}
