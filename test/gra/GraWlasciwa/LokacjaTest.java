package gra.GraWlasciwa;

import gra.NPC.Handlarz;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LokacjaTest {

    private final Lokacja lokacja = new Lokacja("lokalizacja0","opis",
            new Wydarzenie("wf","wfOpis",null,null,0,null),
            List.of(new Wydarzenie("wp","wpOpis",null,null,0,null)),
            List.of(1,2,3),
            new Handlarz("serdel"));

    @Test
    public void getNazwa() {
        assertEquals(lokacja.getNazwa(), "lokalizacja0");
    }

    @Test
    public void getOpis() {
        assertEquals(lokacja.getOpis(), "opis");
    }

    @Test
    public void getSasiednieLokacje() {
        int tmp1 = lokacja.getSasiednieLokacje().get(0);
        int tmp2 = lokacja.getSasiednieLokacje().get(1);
        int tmp3 = lokacja.getSasiednieLokacje().get(2);
        assertEquals(tmp1,1);
        assertEquals(tmp2,2);
        assertEquals(tmp3,3);
    }

    @Test
    public void getWydarzenieFabularne() {
        assertEquals(lokacja.getWydarzenieFabularne().getNazwa(),"wf");
        assertEquals(lokacja.getWydarzenieFabularne().getOpis(),"wfOpis");
    }

    @Test
    public void getWydarzeniaPoboczne() {
        assertEquals(lokacja.getWydarzeniaPoboczne().size(),1);
        assertEquals(lokacja.getWydarzeniaPoboczne().get(0).getNazwa(),"wp");
        assertEquals(lokacja.getWydarzeniaPoboczne().get(0).getOpis(),"wpOpis");
    }

    @Test
    public void getHandlarz() {
        assertEquals(lokacja.getHandlarz().getImie(),"serdel");
    }
}