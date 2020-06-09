package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrzedmiotPozywienieTest {

    PrzedmiotPozywienie przedmiotPozywienie = new PrzedmiotPozywienie("pp","ppOpis",100,100,100);

    @Test
    public void getPrzywracaneZycie() {
        assertEquals(przedmiotPozywienie.getPrzywracaneZycie(),100,0.001);
    }

    @Test
    public void testToString() {
        String tmp = przedmiotPozywienie.getNazwa() +
                " [przywracane zycie: " + String.format("%1.2f", przedmiotPozywienie.getPrzywracaneZycie()) + "]";
        assertEquals(tmp,przedmiotPozywienie.toString());
    }
}