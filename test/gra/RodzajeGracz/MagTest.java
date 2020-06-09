package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import org.junit.Test;

import static org.junit.Assert.*;

public class MagTest {

    private final Mag mag = new Mag();

    @Test
    public void zadajObrazenia() {
        BronMagiczna bronMagiczna = (BronMagiczna) mag.getEkwipunek().getWyekwipowanaBron();
        double obrazeniaMax = bronMagiczna.zadajObrazenia() + mag.getSumaBonusowDoInteligencji();
        for (int i = 0; i < 100; i++){
            double tmp = bronMagiczna.zadajObrazenia() + mag.getSumaBonusowDoInteligencji();
            if(tmp > obrazeniaMax) obrazeniaMax = tmp;
        }
        assertEquals(mag.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronMagiczna.zadajObrazenia(), obrazeniaMax/10);
    }

    @Test
    public void zadajMocneObrazenia() {
        BronMagiczna bronMagiczna = (BronMagiczna) mag.getEkwipunek().getWyekwipowanaBron();
        double obrazeniaMax = bronMagiczna.zadajObrazenia() + mag.getSumaBonusowDoInteligencji();
        for (int i = 0; i < 100; i++){
            double tmp = bronMagiczna.zadajMocneObrazenia();
            if(tmp > obrazeniaMax) obrazeniaMax = tmp;
        }
        assertEquals(mag.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronMagiczna.zadajObrazenia(), obrazeniaMax/2);

    }

    @Test
    public void otrzymajObrazenia() {
        Mag mag2 = new Mag();

        double obrazenia =  12;
        mag.zmniejszPunktyZycia(obrazenia);
        mag2.otrzymajObrazenia(12);

        assertEquals(mag.getObecnePunktyZycia(),mag2.getObecnePunktyZycia(),12);
    }

    @Test
    public void zwiekszLevel() {
        int newPoziom = mag.getPoziom() + 1;
        double newPD = mag.getPunktyDoswiadczenia() % 300;
        double newInteligencja = mag.getInteligencja() + 10;
        mag.zwiekszLevel();
        assertEquals(mag.getPoziom(),newPoziom);
        assertEquals(mag.getPunktyDoswiadczenia(),newPD,0.001);
        assertEquals(mag.getInteligencja(),newInteligencja,0.001);
    }

    @Test
    public void generujEkwipunek() {
        assertEquals(mag.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(mag.getEkwipunek().getEkwipunekFabularne().size(),0);
        assertEquals(mag.getEkwipunek().getEkwipunekBronMagiczna().size(),1);
        assertEquals(mag.getEkwipunek().getEkwipunekBronFizyczna().size(),0);
        assertEquals(mag.getEkwipunek().getEkwipunekPozywienie().size(),2);
    }
}