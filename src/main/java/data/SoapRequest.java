package data;

import entities.Abstract;
import entities.Project;
import utils.ProjectDataExtractor;
import utils.Writer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SoapRequest {
    private static final String URL_FRIS = "https://frisr4.researchportal.be/ws/ProjectServiceFRIS?wsdl";
    private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static final Logger LOGGER = Logger.getLogger(SoapRequest.class.getSimpleName());


    public SoapRequest() {
        // empty
    }

    public static void getProjects(int number){
        final String XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fris=\"http://fris.ewi.be/\" xmlns:crit=\"http://fris.ewi.be/criteria\"><soapenv:Header/><soapenv:Body><fris:getProjects>" +
                "<criteria>" +
                "<crit:window>" +
                "<crit:pageSize>"+number+"</crit:pageSize>" +
                "</crit:window>" +
                "</criteria>"+
                "</fris:getProjects></soapenv:Body></soapenv:Envelope>";
        try {
            URL url = new URL(URL_FRIS);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(XML);
            dos.flush();
            dos.close();
            String responseStatus = connection.getResponseMessage();
            LOGGER.info(responseStatus);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

            ArrayList<Project> projects = new ArrayList<>(
                    ProjectDataExtractor.getProjectData(response.toString())
            );

            Writer.writeToCSV(projects);

        } catch (IOException e) {
            LOGGER.severe(Arrays.toString(e.getStackTrace()));
        }
    }
}
