import data.SoapRequest;
import entities.Project;
import io.vertx.core.Vertx;
import utils.Writer;
import web.MainVerticle;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) { new Program().run();}

    private void run() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    private void writeCSVWithProjectsData(int numberOfProjects){
        ArrayList<Project> projects = new ArrayList<>(SoapRequest.getProjects(numberOfProjects));
        Writer.writeToCSV(projects);
    }
}
