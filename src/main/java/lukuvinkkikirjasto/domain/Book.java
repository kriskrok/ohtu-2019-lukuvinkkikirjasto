package lukuvinkkikirjasto.domain;

public class Book extends Lukuvinkki {

    private String kirjanNimi;
    private String kirjoittaja;

    public Book(String kirjanNimi, String kirjoittaja) {
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
        if (kirjoittaja.equals("")) return kirjanNimi;
        return kirjanNimi + " - " + kirjoittaja;
    }
}
