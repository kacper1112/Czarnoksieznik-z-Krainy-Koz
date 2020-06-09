package gra.NPC;

import gra.RodzajeGracz.Wojownik;
import org.junit.Test;

import static org.junit.Assert.*;

public class NPCTest {

    NPC handlarz = new Handlarz("imieHandlarz");

    @Test
    public void getImie() {
        assertEquals(handlarz.getImie(),"imieHandlarz");
    }

    @Test
    public void getEkwipunek() {
        assertEquals(handlarz.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(handlarz.getEkwipunek().getEkwipunekPozywienie().size(),1);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronMagiczna().size(),1);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronFizyczna().size(),1);
    }

    @Test
    public void setEkwipunek() {
        Wojownik wojownik = new Wojownik();
        handlarz.setEkwipunek(wojownik.getEkwipunek());

        assertEquals(handlarz.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(handlarz.getEkwipunek().getEkwipunekPozywienie().size(),2);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronMagiczna().size(),0);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronFizyczna().size(),1);
    }
}