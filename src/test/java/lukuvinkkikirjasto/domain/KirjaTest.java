package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KirjaTest {

    private Kirja kirja;

    @Before
    public void luoTestiKirja() {
        this.kirja = new Kirja("Kirja", "Kalle");
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