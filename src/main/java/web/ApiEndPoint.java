package web;

import io.vertx.ext.web.Router;

public class ApiEndPoint {
    private final DataRoutes dr = new DataRoutes();

    /*
     * Create routes for the API
     */
    void installRoutes(Router router){

        /*
         * Route to test API responses
         */
        router.get("/api/test").handler(dr::returnTestResponse);

        /*
         * Get a number N of projects
         */
        router.get("/api/projects/size/:number").handler(dr::getProjects);

        /*
         * Get a project from its UUID
         */
        router.get("/api/projects/:uuid").handler(dr::getProject);

        /*
         * Get a number N of publications
         */
        router.get("/api/publications/size/:number").handler(dr::getPublications);
    }
}
