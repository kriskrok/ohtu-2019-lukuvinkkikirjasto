package lukuvinkkikirjasto.domain;

public class Podcast extends Lukuvinkki {

    public String creator;
    public String episode_title;
    public String description;
    public String series;
    public String url;

    
    public Podcast() {
        
    }
    
    public Podcast(String episodeTitle, String series, String creator, String url, String description) {
        //empty constructor, dependency injections are the way forward
        this.episode_title = episodeTitle;
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
        return episode_title;
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
            return episode_title + " - " + url;
        }
        return creator + " - " + episode_title + " - " + url;
    }
    

}

