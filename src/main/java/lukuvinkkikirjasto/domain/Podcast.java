package lukuvinkkikirjasto.domain;

public class Podcast extends Lukuvinkki {

    public String creator;
    public String episodeTitle;
    public String description;
    public String series;
    public String url;

    
    public Podcast() {
        
    }
    
    public Podcast(String creator, String episodeTitle, String url) {
        //empty constructor, dependency injections are the way forward
        this.creator = creator;
        this.episodeTitle = episodeTitle;
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

    public String getEpisodeTitle() {
        return episodeTitle;
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
            return episodeTitle + " - " + url;
        }
        return creator + " - " + episodeTitle + " - " + url;
    }
    

}

