package lukuvinkkikirjasto.domain;

public class Lukuvinkki implements Comparable<Lukuvinkki> {
    public int id;
    public String title;

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Lukuvinkki another) {
        return another.id - this.id;
    }

}


