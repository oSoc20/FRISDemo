package web;

import io.vertx.ext.web.Router;

/**
 * Class to create endpoints for a http server
 */
public class ApiEndPoint {
    private final DataRoutes dataRoutes = new DataRoutes();

    /**
     * Create routes for the API
     *
     * @param router a router {@link Router}
     */
    void installRoutes(Router router){

        /*
         * Route to test API responses
         */
        router.get("/api/test").handler(dataRoutes::returnTestResponse);

        /*
         * Get a number N of projects
         */
        router.get("/api/projects/size/:number").handler(dataRoutes::getProjects);

        /*
         * Get a project from its UUID
         */
        router.get("/api/projects/:uuid").handler(dataRoutes::getProject);

        /*
         * Get a number N of publications
         */
        router.get("/api/publications/size/:number").handler(dataRoutes::getPublications);

        /*
         * Get a publication from its UUID
         */
        router.get("/api/publications/:uuid").handler(dataRoutes::getPublication);
    }
}
