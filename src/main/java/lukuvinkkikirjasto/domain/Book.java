package lukuvinkkikirjasto.domain;

public class Book extends Lukuvinkki {

    public String title;
    public String author;
    public String isbn;
    public String description;
    public String comment;
    public boolean status;
    public String url;
    public int id;

    public Book(String title, String author, String url, String description, String comment, int id)  {
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
        this.comment = comment;
        this.id = id;
    }
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

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public String getIsbn() {
        return isbn;
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
