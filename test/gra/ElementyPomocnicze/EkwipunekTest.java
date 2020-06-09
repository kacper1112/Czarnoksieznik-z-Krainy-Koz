package gra.ElementyPomocnicze;

import gra.RodzajeGracz.Wojownik;
import org.junit.Test;

import static org.junit.Assert.*;

public class EkwipunekTest {
    private final Wojownik wojownik = new Wojownik();
    private final Ekwipunek ekwipunekTEST = wojownik.getEkwipunek();

    @Test
    public void getIloscPozywienie() {
        assertEquals(ekwipunekTEST.getEkwipunekPozywienie().size(),2);
    }

    @Test
    public void getIloscFabularne() {
        assertEquals(ekwipunekTEST.getEkwipunekFabularne().size(),0);
    }

    @Test
    public void getIloscBronMagiczna() {
        assertEquals(ekwipunekTEST.getEkwipunekBronMagiczna().size(),1);
    }

    @Test
    public void getIloscBronFizyczna() {
        assertEquals(ekwipunekTEST.getEkwipunekBronFizyczna().size(),1);
    }

    @Test
    public void getEkwipunekFabularne() {
        assertEquals(ekwipunekTEST.getEkwipunekFabularne().size(),0);
    }

    @Test
    public void getEkwipunekPozywienie() {
    }

    @Test
    public void getEkwipunekBronFizyczna() {
    }

    @Test
    public void getEkwipunekBronMagiczna() {
    }

    @Test
    public void getWyekwipowanaBron() {
    }

    @Test
    public void getTYP() {
        assertEquals(TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK,ekwipunekTEST.getTYP());
    }

    @Test
    public void wyciagnijBronFizyczna() {
    }

    @Test
    public void wyciagnijBronMagiczna() {
    }

    @Test
    public void wyciagnijPozywienie() {
    }

    @Test
    public void zmienWyekwipowanaBronNaMagiczna() {
    }

    @Test
    public void zmienWyekwipowanaBronNaFizyczna() {
    }

    @Test
    public void setTYP() {
    }

    @Test
    public void pokazEkwipunek() {
    }

    @Test
    public void dodajEkwipunek() {
    }

    @Test
    public void wlozDoEkwipunku() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void testToString() {
    }
}