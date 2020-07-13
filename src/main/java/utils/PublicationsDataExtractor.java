package utils;

import entities.Abstract;
import entities.DataProvider;
import entities.Publication;
import entities.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static utils.XMLDataExtractor.getDoi;

public class PublicationsDataExtractor {
    private static final Logger LOGGER = Logger.getLogger(PublicationsDataExtractor.class.getSimpleName());

    private PublicationsDataExtractor(){};

    public static List<Publication> getPublicationData(String xmlString){
        ArrayList<Publication> publications = new ArrayList<>();

        ArrayList<String> publicationDataString = new ArrayList<>(
                XMLDataExtractor.getListFromXmlData(xmlString, "<fris:journalContribution ", "</fris:journalContribution>")
        );

        publicationDataString.forEach(s -> publications.add(new Publication(
                getProjectUUID(s),
                getKeywords(s, "en"),
                getKeywords(s, "nl"),
                getAbstract(s),
                getDataProvider(s),
                getTitle(s),
                getDoi(s)
                )
        ));

        return publications;
    }

    private static String getDoi(String text){
        return XMLDataExtractor.getDoi(text, "<fris:source id=\"295054011\" authorityScheme=\"Identifier Authority Type\" authority=\"DOI\">", "</fris:source>");
    }

    private static UUID getProjectUUID(String text) {
        return XMLDataExtractor.getUUID(text);
    }

    /**
     * Get title of a publication
     *
     * @param text the String from the SOAP API xml response
     * @return the title of the publication
     */
    private static Title getTitle(String text){
        return XMLDataExtractor.getTitle(text, "<fris:title id=", "</fris:title>");
    }

    /**
     * Get all keywords for a project from the API response
     *
     * @param text the String from the SOAP API xml response
     * @param language the language of the abstract needed
     * @return a List with all keywords for a language
     */
    private static List<String> getKeywords(String text, String language) {

        return XMLDataExtractor.getKeywords(text, "<fris:keyword locale=\""+language+"\">", "</fris:keyword>");
    }

    /**
     * Get required data provider's information
     *
     * @param text the String from the SOAP API xml response
     * @return a DataProvider object containing the required data provider information
     */
    private static DataProvider getDataProvider(String text) {
        return XMLDataExtractor.getDataProvider(text);
    }

    /**
     * Extract the english and dutch abstracts from a SOAP API response
     *
     * @param text the String from the SOAP API xml response
     * @return an Abstract object containing the abstracts of a project in english and dutch
     */
    private static Abstract getAbstract(String text){
        return XMLDataExtractor.getAbstract(text, "<fris:projectAbstract ", "</fris:projectAbstract>");
    }
}
