package utils;

import data.SoapRequest;
import entities.Abstract;
import entities.DataProvider;
import entities.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for data extraction from SOUP API xml responses
 */
public class ProjectDataExtractor {
    private static final Logger LOGGER = Logger.getLogger(SoapRequest.class.getSimpleName());

    private ProjectDataExtractor(){}

    public static List<Project> getProjectData (String xmlString){
        ArrayList<Project> projects = new ArrayList<>();

        ArrayList<String> projectsDataString = getProjectsDataString(xmlString);

        projectsDataString.forEach(s -> {
            projects.add(new Project(
                    getProjectUUID(s),
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

    private static String getDoi(String text) {
        return null;
    }

    private static ArrayList<String> getKeywords(String text, String language) {
        ArrayList<String> keywords = new ArrayList<>();
        Matcher matcher = getMatcher(text, "<fris:keyword locale=\""+language+"\">", "</fris:keyword>");

        while (matcher.find()){
            String keyword = matcher.group(1).replace(",", ";");
            keywords.add(keyword);
        }

        return keywords;

    }

    private static DataProvider getDataProvider(String text) {
        return new DataProvider(
                getProviderId(text),
                getProviderName(text)
        );
    }

    private static String getProviderName(String text) {
        Matcher matcher = getMatcher(text, "<fris:dataProvider>", "</fris:dataProvider>");

        if (matcher.find()){
            return matcher.group(1);
        }
        else return null;
    }

    private static String getProviderId(String text) {
        Matcher matcher = getMatcher(text, "<fris:dataProviderId>", "</fris:dataProviderId>");

        if (matcher.find()){
            return matcher.group(1);
        }
        else return null;
    }

    private static UUID getProjectUUID(String text) {
        Matcher matcher = getMatcher(text, "uuid=\"", "\"");
        if (matcher.find()){
            return UUID.fromString(matcher.group(1));
        }
        else return null;
    }

    private static ArrayList<String> getProjectsDataString (String text){
        ArrayList<String> result = new ArrayList<>();

        Matcher matcher = getMatcher(text, "<fris:project ", "</fris:project>");

        while (matcher.find()){
            result.add(matcher.group(1));
        }

        return result;
    }

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
