package gra.NPC;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrogTest {

    Wrog wrog = new Wrog("wrogImie");

    @Test
    public void getObecnePunktyZycia() {
        assertEquals(wrog.getObecnePunktyZycia(),100,0.001);
    }

    @Test
    public void getMaksymalnePunktyZycia() {
        assertEquals(wrog.getMaksymalnePunktyZycia(),100,0.001);
    }

    @Test
    public void getBazowyAtak() {
        assertEquals(wrog.getBazowyAtak(),100,0.001);
    }

    @Test
    public void otrzymajObrazenia() {
        wrog.otrzymajObrazenia(10);
        assertEquals(wrog.getObecnePunktyZycia(),90,0.001);
    }

    @Test
    public void zadajObrazenia() {
        double tmp = wrog.zadajObrazenia();
        assertEquals(tmp,100,0.001);
    }

    @Test
    public void generujEkwipunek() {
        assertEquals(wrog.getEkwipunek().pokazEkwipunek(),2);
        assertEquals(wrog.getEkwipunek().getEkwipunekPozywienie().size(),1);
        assertEquals(wrog.getEkwipunek().getEkwipunekBronMagiczna().size(),1,1);
        assertEquals(wrog.getEkwipunek().getEkwipunekBronFizyczna().size(),1,1);
    }
}