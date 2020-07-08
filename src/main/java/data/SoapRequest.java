package data;

import entities.Project;
import utils.ProjectDataExtractor;
import utils.Writer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * A class to send request to the FRIS SOAP API
 */
public class SoapRequest {
    private static final String URL_FRIS = "https://frisr4.researchportal.be/ws/ProjectServiceFRIS?wsdl";
    private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static final Logger LOGGER = Logger.getLogger(SoapRequest.class.getSimpleName());


    private SoapRequest() {
        // empty
    }

    /**
     * This method will return write in a CSV file the data needed for analysis.
     *
     * @param number the number of project to fetch from the SOAP API
     */
    public static void getProjects(int number){
        final String XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fris=\"http://fris.ewi.be/\" xmlns:crit=\"http://fris.ewi.be/criteria\"><soapenv:Header/><soapenv:Body><fris:getProjects>" +
                "<criteria>" +
                "<crit:window>" +
                "<crit:pageSize>"+number+"</crit:pageSize>" +
                "</crit:window>" +
                "</criteria>"+
                "</fris:getProjects></soapenv:Body></soapenv:Envelope>";
        try {
            HttpsURLConnection connection = getHttpsURLConnection();
            writeAndCloseOutputstream(XML, connection);
            String responseStatus = connection.getResponseMessage();
            LOGGER.info(responseStatus);

            ArrayList<Project> projects = getProjects(connection);

            Writer.writeToCSV(projects);

        } catch (IOException e) {
            LOGGER.severe(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Method to get all projects from the SOAP API response
     *
     * @param connection the connection to the SOAP API
     * @return a List of all the projects
     * @throws IOException in case of BufferReader errors
     */
    private static ArrayList<Project> getProjects(HttpsURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        return new ArrayList<>(
                ProjectDataExtractor.getProjectData(response.toString())
        );
    }

    /**
     * Method to write the response from the API
     *
     * @param xml the xml in string format from the SOAP API response
     * @param connection the connection to the API
     * @throws IOException in case of DataOutputStream errors
     */
    private static void writeAndCloseOutputstream(String xml, HttpsURLConnection connection) throws IOException {
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(xml);
        dos.flush();
        dos.close();
    }

    /**
     * Method to get the connection from the SOAP API
     *
     * @return the connection to the API
     * @throws IOException in case of HttpsURLConnection errors
     */
    private static HttpsURLConnection getHttpsURLConnection() throws IOException {
        URL url = new URL(URL_FRIS);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setDoOutput(true);
        return connection;
    }
}
