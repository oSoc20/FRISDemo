package utils;

import data.SoapRepository;
import entities.Abstract;
import entities.DataProvider;
import entities.Project;
import entities.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for data extraction from SOAP API xml responses
 */
public class ProjectDataExtractor {
    private static final Logger LOGGER = Logger.getLogger(SoapRepository.class.getSimpleName());

    private ProjectDataExtractor(){}

    /**
     * This method will create a List of Projects containing all the data extracted from the API response
     *
     * @param xmlString the String response of the SOAP API
     * @return a List of Projects
     */
    public static List<Project> getProjectData (String xmlString){
        ArrayList<Project> projects = new ArrayList<>();

        ArrayList<String> projectsDataString = getProjectsDataString(xmlString);

        projectsDataString.forEach(s -> {
            projects.add(new Project(
                    getProjectUUID(s),
                    getTitle(s),
                    getKeywords(s, "en"),
                    getKeywords(s, "nl"),
                    getAbstract(s),
                    getDataProvider(s),
                    getDoi(s)
                    )
            );
        });

        return projects;
    }

    private static Title getTitle(String text){
        Matcher matcher = getMatcher(text, "<fris:name id=", "</fris:name>");
        if (matcher.find()){
            return new Title(
                    getTitle(matcher.group(1), "en"),
                    getTitle(matcher.group(1), "nl")
            );
        }
        return null;
    }

    private static String getTitle(String text, String language){
        Matcher matcher = getMatcher(text, "fris:text locale=\""+language+"\">", "</fris:text>");
        if (matcher.find()){
            return matcher.group(1);
        }

        else {
            LOGGER.severe("No Dutch abstract found");
            return null;
        }
    }

    /**
     *
     * @param text the String from the SOAP API xml response
     * @return null -> not access yet to this data
     */
    private static String getDoi(String text) {
        return null;
    }

    /**
     * Get all keywords for a project from the API response
     *
     * @param text the String from the SOAP API xml response
     * @param language the language of the abstract needed
     * @return a List with all keywords for a language
     */
    private static ArrayList<String> getKeywords(String text, String language) {
        ArrayList<String> keywords = new ArrayList<>();
        Matcher matcher = getMatcher(text, "<fris:keyword locale=\""+language+"\">", "</fris:keyword>");

        while (matcher.find()){
            String keyword = matcher.group(1).replace(",", ";");
            keywords.add(keyword);
        }

        return keywords;

    }

    /**
     * Get required data provider's information
     *
     * @param text the String from the SOAP API xml response
     * @return a DataProvider object containing the required data provider information
     */
    private static DataProvider getDataProvider(String text) {
        return new DataProvider(
                getProviderId(text),
                getProviderName(text)
        );
    }

    /**
     * Get a data provider's name
     *
     * @param text the String from the SOAP API xml response
     * @return return the name of a data provider
     */
    private static String getProviderName(String text) {
        Matcher matcher = getMatcher(text, "<fris:dataProvider>", "</fris:dataProvider>");

        if (matcher.find()){
            return matcher.group(1);
        }
        else return null;
    }

    /**
     * Get a data provider's ID
     *
     * @param text the String from the SOAP API xml response
     * @return return the ID of a data provider
     */
    private static String getProviderId(String text) {
        Matcher matcher = getMatcher(text, "<fris:dataProviderId>", "</fris:dataProviderId>");

        if (matcher.find()){
            return matcher.group(1);
        }
        else return null;
    }

    /**
     * Get the UUID of a project
     *
     * @param text the String from the SOAP API xml response
     * @return return the UUID of a project
     */
    private static UUID getProjectUUID(String text) {
        Matcher matcher = getMatcher(text, "uuid=\"", "\"");
        if (matcher.find()){
            return UUID.fromString(matcher.group(1));
        }
        else return null;
    }

    /**
     * Extract the String for each project from an API response containing all the projects' data for further cleaning
     *
     * @param text the String from the SOAP API xml response
     * @return a List of String containing the data of each project
     */
    private static ArrayList<String> getProjectsDataString (String text){
        ArrayList<String> result = new ArrayList<>();

        Matcher matcher = getMatcher(text, "<fris:project ", "</fris:project>");

        while (matcher.find()){
            result.add(matcher.group(1));
        }

        return result;
    }

    /**
     * Extract the english and dutch abstracts from a SOAP API response
     *
     * @param text the String from the SOAP API xml response
     * @return an Abstract object containing the abstracts of a project in english and dutch
     */
    private static Abstract getAbstract(String text){
        Matcher matcher = getMatcher(text, "<fris:projectAbstract ", "</fris:projectAbstract>");
        if (matcher.find()){
            return new Abstract(
                    getAbstractId(matcher.group(1)),
                    getAbstract(matcher.group(1), "en"),
                    getAbstract(matcher.group(1), "nl")
            );
        }
        return null;
    }

    /**
     * Extract an abstract for a language from a SOAP API response
     *
     * @param text the String from the SOAP API xml response
     * @param language the language of the abstract needed
     * @return a String with the dutch abstract
     */
    private static String getAbstract(String text, String language) {
        Matcher matcher = getMatcher(text, "fris:text locale=\""+language+"\">", "</fris:text>");
        if (matcher.find()){
            return matcher.group(1);
        }

        else {
            LOGGER.severe("No Dutch abstract found");
            return null;
        }
    }

    /**
     * Extract an abstract's ID from a SOAP API response
     *
     * @param text a String containing the XML response from SOAP
     * @return the int ID of the abstracts
     */
    private static int getAbstractId(String text) {
        Matcher matcher = getMatcher(text, "id=\"", "\"");

        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }

       else{
           LOGGER.severe("No id found");
           return 0;
        }
    }

    /**
     * Create a matcher to use during the data extraction
     *
     * @param text a String containing the XML response from SOAP
     * @param startLimit the upper limit from which we want data to be extracted
     * @param endLimit the lower limit from which we want data to be extracted
     * @return a Matcher
     */
    private static Matcher getMatcher(String text, String startLimit, String endLimit) {
        String regex = Pattern.quote(startLimit) + "(.*?)" + Pattern.quote(endLimit);
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }
}
