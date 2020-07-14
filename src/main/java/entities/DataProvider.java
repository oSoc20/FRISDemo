package entities;

/**
 * Data Provider's entity that contains its id and name
 */
public class DataProvider {
    private String id;
    private String name;

    public DataProvider(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataProvider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String toStringCSV() {
        return id +
                ",\"" + name + "\"";
    }
}
