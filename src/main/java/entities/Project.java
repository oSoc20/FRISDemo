package entities;

import java.util.List;
import java.util.UUID;

public class Project {
    private UUID id;
    private List<String> englishKeywords;
    private List<String> dutchKeywords;
    private Abstract projectAbstract;
    private DataProvider dataProvider;
    private String doi;

    public Project(){}

    public Project(UUID id, List<String> englishKeywords, List<String> dutchKeywords, Abstract projectAbstract, DataProvider dataProvider, String doi) {
        this.id = id;
        this.englishKeywords = englishKeywords;
        this.dutchKeywords = dutchKeywords;
        this.projectAbstract = projectAbstract;
        this.dataProvider = dataProvider;
        this.doi = doi;
    }

    public UUID getId() {
        return id;
    }

    public List<String> getEnglishKeywords() {
        return englishKeywords;
    }

    public List<String> getDutchKeywords() {
        return dutchKeywords;
    }

    public Abstract getAbstract() {
        return projectAbstract;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public String getDoi() {
        return doi;
    }

    public String toStringCSV(){
        return id +
                "," + separateValueWithSemicolon(englishKeywords) +
                "," + separateValueWithSemicolon(dutchKeywords) +
                "," + projectAbstract.toStringCSV() +
                "," + dataProvider.toStringCSV() +
                "," + doi;
    }

    private StringBuilder separateValueWithSemicolon(List<String> values) {
        StringBuilder result = new StringBuilder();

        values.forEach(v -> result.append(v).append(";"));

        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", englishKeywords=" + englishKeywords +
                ", dutchKeywords=" + dutchKeywords +
                ", projectAbstract=" + projectAbstract +
                ", dataProvider='" + dataProvider + '\'' +
                ", doi='" + doi + '\'' +
                '}';
    }
}
