package lukuvinkkikirjasto.domain;

public class Book extends Lukuvinkki {

    private String booktitle;
    private String writer;
    private int id;

    public Book(String booktitle, String writer, int id) {
        this.booktitle = booktitle;
        this.writer = writer;
        this.id = id;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public String getWriter() {
        return writer;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (writer.equals("")) {
            return booktitle;
        } 
        return booktitle + " - " + writer;
    }
}
