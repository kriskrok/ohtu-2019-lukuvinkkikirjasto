package lukuvinkkikirjasto.domain;

public class Podcast extends Lukuvinkki {

    public String creator;
    public String episode_title;
    public String description;
    public String series;
    public String url;

    
    public Podcast() {
        
    }
    
    public Podcast(String creator, String episodeTitle, String url) {
        //empty constructor, dependency injections are the way forward
        this.creator = creator;
        this.episode_title = episodeTitle;
        this.url = url;
   
    }

    public int getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getEpisode_Title() {
        return episode_title;
    }

    public String getSeries() {
        return series;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        if(creator == null) {
            return episode_title + " - " + url;
        }
        return creator + " - " + episode_title + " - " + url;
    }
    

}

