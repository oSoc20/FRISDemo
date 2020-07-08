package entities;

public class Abstract {
    private int id;
    private String enAbstract;
    private String nlAbstract;

    public Abstract(int id, String enAbstract, String nlAbstract) {
        this.id = id;
        this.enAbstract = removeUselessCharacters(enAbstract);
        this.nlAbstract = removeUselessCharacters(nlAbstract);
    }

    public int getId() {
        return id;
    }

    public String getEnglishAbstract() {
        return enAbstract;
    }

    public String getDutchAbstract() {
        return nlAbstract;
    }

    @Override
    public String toString() {
        return "Abstract{" +
                "id=" + id +
                ", enAbstract='" + enAbstract + '\'' +
                ", nlAbstract='" + nlAbstract + '\'' +
                '}';
    }

    public String toStringCSV() {
        return id +
                ",\"" + enAbstract + "\"" +
                ",\"" + nlAbstract + "\"";
    }

    private String removeUselessCharacters(String text){

        if (text != null)
            return text.replace("\"", "");
        else return null;
    }
}
