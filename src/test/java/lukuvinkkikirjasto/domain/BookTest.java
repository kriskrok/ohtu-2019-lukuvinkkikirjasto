package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book kirja;

    @Before
    public void luoTestiKirja() {
        this.kirja = new Book("Kirja", "Kalle");
    }

    @Test
    public void nimiTallentuu() {
        assertEquals(kirja.getKirjanNimi(), "Kirja");
    }

    @Test
    public void kirjoittajaTallentuu() {
        assertEquals(kirja.getKirjoittaja(), "Kalle");
    }

    @Test
    public void kirjaTulostuuOikein() {
        assertEquals(kirja.toString(), "Kirja - Kalle");
    }
}