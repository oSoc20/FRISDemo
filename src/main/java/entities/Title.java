package entities;

public class Title {
    private String englishTitle;
    private String dutchTitle;

    public Title(String englishTitle, String dutchTitle) {
        this.englishTitle = englishTitle;
        this.dutchTitle = dutchTitle;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public String getDutchTitle() {
        return dutchTitle;
    }

    @Override
    public String toString() {
        return "Title{" +
                "englishTitle=" + englishTitle +
                ",dutchTitle=" + dutchTitle +
                '}';
    }

    public String toStringCSV() {
        return "\"" + englishTitle + "\"" +
                ",\"" + dutchTitle + "\"";
    }
}
