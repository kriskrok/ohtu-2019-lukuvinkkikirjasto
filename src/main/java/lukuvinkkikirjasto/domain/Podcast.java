package lukuvinkkikirjasto.domain;

public class Podcast extends Lukuvinkki {

    public String creator;
    public String description;
    public String series;
    public String url;
    
    public Podcast() {
        //empty constructor, dependency injections are the way forward!
        this("", "", "", "", "");
    }
    
    public Podcast(String episodeTitle, String series, String creator, String url, String description) {

        this.title = episodeTitle;
        this.series = series;
        this.creator = creator;
        this.url = url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getEpisodeTitle() {
        return title;
    }

    public String getSeries() {
        return series;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if(creator.length() == 0) {
            return title + " - " + url;
        }
        return creator + " - " + title + " - " + url;
    }
    

}

