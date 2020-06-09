package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronFizyczna;
import org.junit.Test;

import static org.junit.Assert.*;

public class WojownikTest {

    private final Wojownik wojownik = new Wojownik();

    @Test
    public void zadajObrazenia() {
        BronFizyczna bronFizyczna = (BronFizyczna) wojownik.getEkwipunek().getWyekwipowanaBron();
        double obrazeniaMax = bronFizyczna.zadajObrazenia();
        for (int i = 0; i < 100; i++){
            double tmp = bronFizyczna.zadajObrazenia();
            if(tmp > obrazeniaMax) obrazeniaMax = tmp;
        }
        assertEquals(wojownik.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronFizyczna.zadajObrazenia(), obrazeniaMax/10);
    }

    @Test
    public void zadajMocneObrazenia() {
        BronFizyczna bronFizyczna = (BronFizyczna) wojownik.getEkwipunek().getWyekwipowanaBron();
        double obrazeniaMax = bronFizyczna.zadajMocneObrazenia();
        for (int i = 0; i < 100; i++){
            double tmp = bronFizyczna.zadajMocneObrazenia();
            if(tmp > obrazeniaMax) obrazeniaMax = tmp;
        }
        assertEquals(wojownik.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronFizyczna.zadajObrazenia(), obrazeniaMax/1.3);

    }

    @Test
    public void otrzymajObrazenia() {
        Wojownik wojownik2 = new Wojownik();
        double sumaSily = wojownik.getSila() + wojownik.getSumaBonusowDoSily();
        if (sumaSily > 90) {
            sumaSily = 90;
        }

        double obrazenia = (1 - sumaSily / 100) * 12;
        wojownik.zmniejszPunktyZycia(obrazenia);
        wojownik2.otrzymajObrazenia(12);

        assertEquals(wojownik.getObecnePunktyZycia(),wojownik2.getObecnePunktyZycia(),12);
    }

    @Test
    public void zwiekszLevel() {
        int newPoziom = wojownik.getPoziom() + 1;
        double newPD = wojownik.getPunktyDoswiadczenia() % 300;
        double newSila = wojownik.getSila() + 8;
        double newInteligencja = wojownik.getInteligencja() + 3;
        wojownik.zwiekszLevel();
        assertEquals(wojownik.getPoziom(),newPoziom);
        assertEquals(wojownik.getPunktyDoswiadczenia(),newPD,0.001);
        assertEquals(wojownik.getSila(),newSila,0.001);
        assertEquals(wojownik.getInteligencja(),newInteligencja,0.001);
    }

    @Test
    public void generujEkwipunek() {
        assertEquals(wojownik.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(wojownik.getEkwipunek().getEkwipunekFabularne().size(),0);
        assertEquals(wojownik.getEkwipunek().getEkwipunekBronMagiczna().size(),0);
        assertEquals(wojownik.getEkwipunek().getEkwipunekBronFizyczna().size(),1);
        assertEquals(wojownik.getEkwipunek().getEkwipunekPozywienie().size(),2);
    }
}