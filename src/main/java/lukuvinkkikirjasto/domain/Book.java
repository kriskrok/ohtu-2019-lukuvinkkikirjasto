package lukuvinkkikirjasto.domain;

public class Book extends Lukuvinkki {

    public String title;
    public String author;
    public String isbn;
    public String description;
    public boolean status;
    public int id;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public Book() {

    }

    public String getBooktitle() {
        return title;
    }

    public String getWriter() {
        return author;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (author.equals("")) {
            return title;
        } 
        return title + " - " + author;
    }
}
