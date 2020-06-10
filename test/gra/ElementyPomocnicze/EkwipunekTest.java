package gra.ElementyPomocnicze;

import gra.GraWlasciwa.Gra;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;
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
        assertEquals(ekwipunekTEST.getEkwipunekBronMagiczna().size(),0);
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
        assertEquals(ekwipunekTEST.getEkwipunekFabularne().size(),1,1);
    }

    @Test
    public void getEkwipunekBronFizyczna() {
        assertEquals(ekwipunekTEST.getEkwipunekBronFizyczna().size(),1);
    }

    @Test
    public void getEkwipunekBronMagiczna() {
        assertEquals(ekwipunekTEST.getEkwipunekBronMagiczna().size(),0);
    }

    @Test
    public void getWyekwipowanaBron() {
        boolean tmp = ekwipunekTEST.getWyekwipowanaBron() instanceof BronFizyczna;
        assertTrue(tmp);
    }

    @Test
    public void getTYP() {
        assertEquals(TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK,ekwipunekTEST.getTYP());
    }

    @Test
    public void wyciagnijBronFizyczna() {
        BronFizyczna bronFizyczna = ekwipunekTEST.getEkwipunekBronFizyczna().get(0);
        ekwipunekTEST.wyciagnijBronFizyczna(0);
        boolean tmp;
        tmp = ekwipunekTEST.getEkwipunekBronFizyczna().stream().anyMatch(x-> bronFizyczna.getNazwa().equals(x.getNazwa()));
        assertFalse(tmp);
    }

    @Test
    public void wyciagnijBronMagiczna() {
        Mag mag = new Mag();
        BronMagiczna bronMagiczna = mag.getEkwipunek().getEkwipunekBronMagiczna().get(0);
        ekwipunekTEST.wyciagnijBronFizyczna(0);
        boolean tmp;
        tmp = mag.getEkwipunek().getEkwipunekBronFizyczna().stream().anyMatch(x-> bronMagiczna.getNazwa().equals(x.getNazwa()));
        assertFalse(tmp);
    }

    @Test
    public void wyciagnijPozywienie() {
        PrzedmiotPozywienie przedmiotPozywienie = ekwipunekTEST.getEkwipunekPozywienie().get(0);
        ekwipunekTEST.wyciagnijPozywienie(0);
        boolean tmp;
        tmp = ekwipunekTEST.getEkwipunekBronFizyczna().stream().anyMatch(x-> przedmiotPozywienie.getNazwa().equals(x.getNazwa()));
        assertFalse(tmp);
    }

    @Test
    public void zmienWyekwipowanaBronNaMagiczna() {
        Mag mag = new Mag();
        BronMagiczna bronMagiczna = (BronMagiczna) mag.getEkwipunek().getWyekwipowanaBron();
        mag.getEkwipunek().getEkwipunekBronMagiczna().add(new BronMagiczna(
                "abc","bcd",123,123,123,123
        ));
        mag.getEkwipunek().zmienWyekwipowanaBronNaMagiczna(1,true);
        boolean tmp = false;
        if(!bronMagiczna.getNazwa().equals(((BronMagiczna) mag.getEkwipunek().getWyekwipowanaBron()).getNazwa())) tmp = true;
        assertTrue(tmp);
    }

    @Test
    public void zmienWyekwipowanaBronNaFizyczna() {
        BronFizyczna bronFizyczna = (BronFizyczna) ekwipunekTEST.getWyekwipowanaBron();
        ekwipunekTEST.getEkwipunekBronFizyczna().add(new BronFizyczna(
                "abc","bcd",123,123,123,123
        ));
        ekwipunekTEST.zmienWyekwipowanaBronNaFizyczna(1,true);
        boolean tmp = false;
        if(!bronFizyczna.getNazwa().equals(((BronFizyczna) ekwipunekTEST.getWyekwipowanaBron()).getNazwa())) tmp = true;
        assertTrue(tmp);
    }

    @Test
    public void setTYP() {
        ekwipunekTEST.setTYP(TYP_POSIADACZA_EKWIPUNKU.BOSS);
        assertEquals(TYP_POSIADACZA_EKWIPUNKU.BOSS,ekwipunekTEST.getTYP());
    }

    @Test
    public void pokazEkwipunek() {
        assertEquals(ekwipunekTEST.pokazEkwipunek(),3);
    }

    @Test
    public void dodajEkwipunek() {
        Mag mag = new Mag();
        ekwipunekTEST.dodajEkwipunek(mag.generujEkwipunek());
        assertEquals(ekwipunekTEST.pokazEkwipunek(),6);
        assertEquals(ekwipunekTEST.getEkwipunekBronMagiczna().size(),1);
        assertEquals(ekwipunekTEST.getEkwipunekBronFizyczna().size(),1);
        assertEquals(ekwipunekTEST.getEkwipunekPozywienie().size(),4);
    }

    @Test
    public void wlozDoEkwipunku() {
        ekwipunekTEST.wlozDoEkwipunku(new PrzedmiotFabularny(
                "abc", "qwe", 123, 123,true,"wsk",123
        ));
        assertEquals(ekwipunekTEST.getEkwipunekFabularne().size(),1);
    }

    @Test
    public void testToString() {
        String tmp = "Ekwipunek typu: " + ekwipunekTEST.getTYP() +
                " Przedmioty: Fabularne=" + ekwipunekTEST.getEkwipunekFabularne() +
                ", Przedmioty: Pozywienie=" + ekwipunekTEST.getEkwipunekPozywienie() +
                ", Przedmioty: BronMagiczna=" + ekwipunekTEST.getEkwipunekBronMagiczna() +
                ", Przedmioty: BronFizczna=" + ekwipunekTEST.getEkwipunekBronFizyczna() +
                ", wyekwipowanaBron=" + ekwipunekTEST.getWyekwipowanaBron();

        assertEquals(ekwipunekTEST.toString(),tmp);
    }
}