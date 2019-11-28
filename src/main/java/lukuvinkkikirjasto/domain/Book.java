package lukuvinkkikirjasto.domain;

public class Book extends Lukuvinkki {

    private String booktitle;
    private String writer;

    public Book(String booktitle, String writer) {
        this.booktitle = booktitle;
        this.writer = writer;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        if (writer.equals("")) {
            return booktitle;
        } 
        return booktitle + " - " + writer;
    }
}
