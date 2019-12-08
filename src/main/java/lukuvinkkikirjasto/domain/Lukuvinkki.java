package lukuvinkkikirjasto.domain;

public class Lukuvinkki implements Comparable<Lukuvinkki> {
    public int id;

    /*public int getId() {
        return id;
    }

*/
    @Override
    public int compareTo(Lukuvinkki another) {
        return another.id - this.id;
    }

}


