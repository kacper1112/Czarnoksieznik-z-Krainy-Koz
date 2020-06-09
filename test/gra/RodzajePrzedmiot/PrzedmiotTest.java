package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrzedmiotTest {

    Przedmiot przedmiot = new PrzedmiotPozywienie("pp","ppOpis",100,100,100);
    @Test
    public void getNazwa() {
        assertEquals(przedmiot.getNazwa(),"pp");
    }

    @Test
    public void getOpis() {
        assertEquals(przedmiot.getOpis(),"ppOpis");
    }

    @Test
    public void getWartosc() {
        assertEquals(przedmiot.getWartosc(),100);
    }

    @Test
    public void getAtrybut() {
        Atrybut atrybut = new Atrybut(100);
        assertEquals(przedmiot.getAtrybut().getSzansaNaNatychmiastoweZabicie(), atrybut.getSzansaNaNatychmiastoweZabicie(),100);
        assertEquals(przedmiot.getAtrybut().getSzansaNaKrytyczne(), atrybut.getSzansaNaKrytyczne(),100);
        assertEquals(przedmiot.getAtrybut().getBonusDoInteligencji(), atrybut.getBonusDoInteligencji(),100);
        assertEquals(przedmiot.getAtrybut().getBonusDoSily(), atrybut.getBonusDoSily(),100);
    }
}