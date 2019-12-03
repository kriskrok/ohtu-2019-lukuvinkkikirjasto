package lukuvinkkikirjasto.domain;

public class Podcast extends Lukuvinkki {

    public String creator;
    public String episodeTitle;
    public String description;
    public String series;
    public String url;

    public Podcast() {
        //empty constructor, dependency injections are the way forward
    }

}

