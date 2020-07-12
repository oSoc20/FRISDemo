package web;

import data.SoapRepository;
import entities.Project;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;
import java.util.logging.Logger;

public class DataRoutes {
    private static final Logger LOGGER = Logger.getLogger(DataRoutes.class.getSimpleName());

    DataRoutes(){}

    public void getProject(RoutingContext routingContext) {
        LOGGER.info("Get projects route called");
        UUID uuid = UUID.fromString(routingContext.request().getParam("uuid"));
        Project project = SoapRepository.getProject(uuid);

        if (project.isEmpty()){
            sendJson(routingContext.response().setStatusCode(404), "Project not found");
        }
        else {
            sendJson(routingContext.response().setStatusCode(200), project);
        }

    }


    public void returnTestResponse(RoutingContext routingContext) {
        LOGGER.info("Test route called");
        sendJson(routingContext.response().setStatusCode(200), "UhUH test successful");
    }

    public void getProjects(RoutingContext routingContext) {
        LOGGER.info("Get projects route called");
        int nOfProjects = getNumber(routingContext);

        if (nOfProjects < 1){
            sendJson(routingContext.response().setStatusCode(400), "The value is not valid for /api/projects/");
        }

        else {
            sendJson(routingContext.response().setStatusCode(202), SoapRepository.getProjects(nOfProjects));
        }
    }

    private void sendJson(HttpServerResponse response, Object object){
        response.putHeader("Content-Type", "application/json;charset=utf-8")
                .end(Json.encodePrettily(object));
    }

    private int getNumber(RoutingContext routingContext) {
        String n = routingContext.request().getParam("number");
        try {
            return Math.max(Integer.parseInt(n), 0);
        } catch (NumberFormatException e){
            return -1;
        }

    }
}
