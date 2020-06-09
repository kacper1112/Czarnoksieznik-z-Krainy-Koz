package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtrybutTest {

    private final Atrybut atrybut = new Atrybut(100);

    @Test
    public void losujAtrybuty() {
    }

    @Test
    public void getBonusDoSily() {
        double tmp = atrybut.getBonusDoSily();
        assertEquals(atrybut.getBonusDoSily(),tmp,0.0001);
    }

    @Test
    public void getBonusDoInteligencji() {
        double tmp = atrybut.getBonusDoInteligencji();
        assertEquals(atrybut.getBonusDoInteligencji(),tmp,0.0001);
    }

    @Test
    public void getSzansaNaKrytyczne() {
        double tmp = atrybut.getSzansaNaKrytyczne();
        assertEquals(atrybut.getSzansaNaKrytyczne(),tmp,0.0001);
    }

    @Test
    public void getSzansaNaNatychmiastoweZabicie() {
        double tmp = atrybut.getSzansaNaNatychmiastoweZabicie();
        assertEquals(atrybut.getSzansaNaNatychmiastoweZabicie(),tmp,0.0001);
    }
}