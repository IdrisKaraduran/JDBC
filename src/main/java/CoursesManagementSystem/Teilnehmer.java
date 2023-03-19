package CoursesManagementSystem;

public class Teilnehmer {
    private int id;
    private String vorname;
    private String nachname;
    private String stadt;
    private int alt;
    private String email;

    public Teilnehmer() {

    }

    public Teilnehmer(String vorname, String nachname, String stadt, int alt,String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.stadt = stadt;
        this.alt = alt;
        this.email  = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return

                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", stadt='" + stadt + '\'' +
                ", alt=" + alt +
                ", email='" + email;
    }
}
