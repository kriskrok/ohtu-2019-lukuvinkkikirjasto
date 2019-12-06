package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PodcastTest {

    private Podcast podcast;

    @Before
    public void createANewPodcast() {
        this.podcast = new Podcast("Live life to the minimum", "Auta Antti", "Antti Holma", "http://www.antinapu.fi", "Maailman paras podcast");
    }

    @Test
    public void aValidEpisodeTitleCanBeSet() {
        assertEquals(podcast.getEpisodeTitle(), "Live life to the minimum");
    }

    @Test
    public void aValidCreatorCanBeSet() {
        assertEquals(podcast.getCreator(), "Antti Holma");
    }

    @Test
    public void aValidSeriesCanBeSet() {
        assertEquals(podcast.getSeries(), "Auta Antti");
    }

    @Test
    public void aValidUrlCanBeSet() {
        assertEquals(podcast.getUrl(), "http://www.antinapu.fi");
    }

    @Test
    public void theMethodToStringWorks() {
        assertEquals(podcast.toString(), "Antti Holma - Live life to the minimum - http://www.antinapu.fi");
    }
}