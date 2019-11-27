package lukuvinkkikirjasto.domain;

public class Kirja extends Lukuvinkki {

    private String kirjanNimi;
    private String kirjoittaja;

    public Kirja(String kirjanNimi, String kirjoittaja) {
        this.kirjanNimi = kirjanNimi;
        this.kirjoittaja = kirjoittaja;
    }

    public String getKirjanNimi() {
        return kirjanNimi;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    @Override
    public String toString() {
        return kirjanNimi + " - " + kirjoittaja;
    }
}
