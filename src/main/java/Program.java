import data.SoapRequest;
import io.vertx.core.Vertx;
import web.MainVerticle;

public class Program {

    public static void main(String[] args) { new Program().run();}

    private void run() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    private void writeCSVWithProjectsData(int numberOfProjects){
        SoapRequest.getProjects(numberOfProjects);
    }
}
