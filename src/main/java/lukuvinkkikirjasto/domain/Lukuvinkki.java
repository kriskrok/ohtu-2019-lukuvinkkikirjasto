package lukuvinkkikirjasto.domain;

public class Lukuvinkki implements Comparable<Lukuvinkki> {
    public int id;
    public String title;

    public String getTitle() {
        return title;
    }
    public int getId() { return this.id; }

    @Override
    public int compareTo(Lukuvinkki another) {
        return another.id - this.id;
    }

}


