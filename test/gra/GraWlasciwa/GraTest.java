package gra.GraWlasciwa;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class GraTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void getInstance() {
        assertSame(Gra.getInstance(),Gra.getInstance());
    }

    @Test
    public void wygrana() {
        KolorTekstu.printCyan("Udalo Ci sie pokonac zlego Czarnoksieznika! Gratulacje!\n" + "To juz koniec Twojej przygody!");
        String tmp = KolorTekstu.CYAN + "Udalo Ci sie pokonac zlego Czarnoksieznika! Gratulacje!" + "\n" +"To juz koniec Twojej przygody!" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void przegrana() {
        KolorTekstu.printCyan("Nie zyjesz! Koniec gry! Powodzenia nastepnym razem.");
        String tmp = KolorTekstu.CYAN + "Nie zyjesz! Koniec gry! Powodzenia nastepnym razem." + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void wczytajWyborGracza() {
    }

    @Test
    public void wyczyscTerminal() {
    }

    @Test
    public void granko() {
    }

    @Test
    public void getLokalizacjaGracza() {
        Gra.getInstance().setLokalizacjaGracza(0);
        int tmp = Gra.getInstance().getLokalizacjaGracza();
        assertEquals(tmp,0);
        Gra.getInstance().setLokalizacjaGracza(5);
        tmp = Gra.getInstance().getLokalizacjaGracza();
        assertEquals(tmp,5);
    }

    @Test
    public void setLokalizacjaGracza() {
        Gra.getInstance().setLokalizacjaGracza(5);
        int tmp = Gra.getInstance().getLokalizacjaGracza();
        assertEquals(tmp,5);
    }

    @Test
    public void getGracz() {
        Gra.getInstance().setGracz(new Wojownik());
        Gracz graczTMP = new Wojownik();
        double tmp1 = graczTMP.getMaksymalnePunktyZycia();
        double tmp2 = Gra.getInstance().getGracz().getMaksymalnePunktyZycia();
        assertEquals(tmp1 ,tmp2, 0.001);
    }

    @Test
    public void setGracz() {
        Gracz graczTMP = new Mag();
        graczTMP.setPieniadze(123456789);
        Gra.getInstance().setGracz(graczTMP);
        double tmp1 = graczTMP.getPieniadze();
        double tmp2 = Gra.getInstance().getGracz().getPieniadze();
        assertEquals(tmp1,tmp2,0.001);
    }

    @Test
    public void setLokacje() {
        Lokacja l1 = new Lokacja("a","b",null,null,null,null);
        Gra.getInstance().setLokacje(List.of(new Lokacja("a","b",null,null,null,null)));
        assertEquals(l1.getNazwa(),Gra.getInstance().getLokacje().get(0).getNazwa());
        assertEquals(l1.getOpis(),Gra.getInstance().getLokacje().get(0).getOpis());
    }

    @Test
    public void getLokacje() {
        Lokacja l1 = new Lokacja("a","b",null,null,null,null);
        Gra.getInstance().setLokacje(List.of(new Lokacja("a","b",null,null,null,null)));
        assertEquals(l1.getNazwa(),Gra.getInstance().getLokacje().get(0).getNazwa());
        assertEquals(l1.getOpis(),Gra.getInstance().getLokacje().get(0).getOpis());
    }
}