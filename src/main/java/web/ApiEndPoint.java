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
        router.get("/api/projects/size/:number").handler(dr::getProjects);

        /*
         * Get route for a project using a UUID
         */
        router.get("/api/projects/:uuid").handler(dr::getProject);

        /*
         * Create routes for the API
         *
         */
    }
}
