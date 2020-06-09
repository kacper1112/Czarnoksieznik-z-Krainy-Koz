package gra.NPC;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandlarzTest {

    private final Handlarz handlarz = new Handlarz("imieHandlarz");

    @Test
    public void generujEkwipunek() {
        assertEquals(handlarz.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(handlarz.getEkwipunek().getEkwipunekPozywienie().size(),1);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronMagiczna().size(),1,1);
        assertEquals(handlarz.getEkwipunek().getEkwipunekBronFizyczna().size(),1,1);
    }

    @Test
    public void getPieniadze() {
        assertEquals(handlarz.getPieniadze(),300,100);
    }

    @Test
    public void setPieniadze() {
        handlarz.setPieniadze(1);
        assertEquals(handlarz.getPieniadze(),1);
    }

    @Test
    public void getOferta() {
        assertEquals(handlarz.getOferta().size(),3);
    }
}