package PFA.Doleance.Module;

public class ModuleDoleance {
    private String description;
    private int id;
    private String sujet;
    private String date;

    public ModuleDoleance(String description, int id, String sujet, String date) {
        this.description = description;
        this.id = id;
        this.sujet = sujet;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Doleance{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", sujet='" + sujet + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
