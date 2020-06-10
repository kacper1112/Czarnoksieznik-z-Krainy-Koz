package gra.NPC;

import gra.RodzajePrzedmiot.Przedmiot;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import org.junit.Test;

import static org.junit.Assert.*;

public class FabularnyTest {

    private final Fabularny fabularny = new Fabularny("imieFab",true,
            new PrzedmiotFabularny( "pf","opis",123,123,false,"wsk",123.123
    ));

    @Test
    public void generujEkwipunek() {
        assertEquals(fabularny.getEkwipunek().pokazEkwipunek(),2);
        assertEquals(fabularny.getEkwipunek().getEkwipunekPozywienie().size(),1,1);
        assertEquals(fabularny.getEkwipunek().getEkwipunekBronMagiczna().size(),1,1);
        assertEquals(fabularny.getEkwipunek().getEkwipunekBronFizyczna().size(),1,1);
    }

    @Test
    public void podarujLosowyPrzedmiotNieFabularny() {
        Przedmiot p =  fabularny.podarujLosowyPrzedmiotNieFabularny();
        boolean tmp;
        tmp = !(p instanceof PrzedmiotFabularny);
        assertTrue(tmp);
    }

    @Test
    public void podarujPrzedmiotFabularny() {
        PrzedmiotFabularny p =  fabularny.podarujPrzedmiotFabularny();
        assertEquals(p.getNazwa(),"pf");
    }

    @Test
    public void isCzyPosiadaPrzedmiotFabularny() {
        assertEquals(fabularny.getEkwipunek().getEkwipunekFabularne().size(),1);
    }
}