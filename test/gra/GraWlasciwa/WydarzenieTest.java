package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Zagadka;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Wojownik;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WydarzenieTest {

    private final Wydarzenie wydarzenie = new Wydarzenie(
            "wydarzenie",
            "opisWydarzenie",
            new Wojownik(),
            List.of(new Fabularny("fab",false,null)),
            List.of(new Wrog("wrog",123,100,null)),
            new Boss("boss",1234,1234,null),
            null
    );

    @Test
    public void getCzyWykonana() {
        wydarzenie.setCzyWykonana(false);
        assertEquals(wydarzenie.getCzyWykonana(),false);
    }

    @Test
    public void getZagadka() {
        assertNull(wydarzenie.getZagadka());
    }

    @Test
    public void getNazwa() {
        assertEquals(wydarzenie.getNazwa(),"wydarzenie");
    }

    @Test
    public void getOpis() {
        assertEquals(wydarzenie.getOpis(),"opisWydarzenie");
    }

    @Test
    public void getGracz() {
        Gracz gracz = new Wojownik();
        assertEquals(wydarzenie.getGracz().getMaksymalnePunktyZycia(),gracz.getMaksymalnePunktyZycia(),0.001);
    }

    @Test
    public void getPostacieFabularne() {
        assertEquals(wydarzenie.getPostacieFabularne().size(),1);
        assertEquals(wydarzenie.getPostacieFabularne().get(0).getImie(),"fab");
    }

    @Test
    public void getWrogowie() {
        assertEquals(wydarzenie.getWrogowie().size(),1);
        assertEquals(wydarzenie.getWrogowie().get(0).getImie(),"wrog");
        assertEquals(wydarzenie.getWrogowie().get(0).getMaksymalnePunktyZycia(),123,0.001);
        assertEquals(wydarzenie.getWrogowie().get(0).getBazowyAtak(),100,0.001);
    }

    @Test
    public void getBoss() {
        assertEquals(wydarzenie.getBoss().getImie(),"boss");
        assertEquals(wydarzenie.getBoss().getMaksymalnePunktyZycia(),1234,0.001);
        assertEquals(wydarzenie.getBoss().getBazowyAtak(),1234,0.001);
    }

    @Test
    public void setCzyWykonana() {
        wydarzenie.setCzyWykonana(false);
        assertEquals(wydarzenie.getCzyWykonana(),false);
    }

    @Test
    public void zagadka() {
    }
}