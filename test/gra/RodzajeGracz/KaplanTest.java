package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import org.junit.Test;

import static org.junit.Assert.*;

public class KaplanTest {

    private  final Kaplan kaplan = new Kaplan();

    @Test
    public void zadajObrazenia() {
        if(kaplan.getEkwipunek().getWyekwipowanaBron() instanceof  BronMagiczna) {
            BronMagiczna bronMagiczna = (BronMagiczna) kaplan.getEkwipunek().getWyekwipowanaBron();
            double obrazeniaMax = bronMagiczna.zadajObrazenia() + kaplan.getSumaBonusowDoInteligencji();
            for (int i = 0; i < 100; i++){
                double tmp = bronMagiczna.zadajObrazenia() + kaplan.getSumaBonusowDoInteligencji();
                if(tmp > obrazeniaMax) obrazeniaMax = tmp;
            }
            assertEquals(kaplan.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronMagiczna.zadajObrazenia(), obrazeniaMax/10);
        } else if(kaplan.getEkwipunek().getWyekwipowanaBron() instanceof  BronFizyczna){
            BronFizyczna bronFizyczna = (BronFizyczna) kaplan.getEkwipunek().getWyekwipowanaBron();
            double obrazeniaMax = bronFizyczna.zadajObrazenia();
            for (int i = 0; i < 100; i++){
                double tmp = bronFizyczna.zadajObrazenia();
                if(tmp > obrazeniaMax) obrazeniaMax = tmp;
            }
            assertEquals(kaplan.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronFizyczna.zadajObrazenia(), obrazeniaMax/10);
        }
    }

    @Test
    public void zadajMocneObrazenia() {
        if(kaplan.getEkwipunek().getWyekwipowanaBron() instanceof  BronMagiczna) {
            BronMagiczna bronMagiczna = (BronMagiczna) kaplan.getEkwipunek().getWyekwipowanaBron();
            double obrazeniaMax = bronMagiczna.zadajObrazenia() + kaplan.getSumaBonusowDoInteligencji();
            for (int i = 0; i < 100; i++){
                double tmp = bronMagiczna.zadajMocneObrazenia();
                if(tmp > obrazeniaMax) obrazeniaMax = tmp;
            }
            assertEquals(kaplan.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronMagiczna.zadajObrazenia(), obrazeniaMax/2);
        } else if(kaplan.getEkwipunek().getWyekwipowanaBron() instanceof  BronFizyczna){
            BronFizyczna bronFizyczna = (BronFizyczna) kaplan.getEkwipunek().getWyekwipowanaBron();
            double obrazeniaMax = bronFizyczna.zadajMocneObrazenia();
            for (int i = 0; i < 100; i++){
                double tmp = bronFizyczna.zadajMocneObrazenia();
                if(tmp > obrazeniaMax) obrazeniaMax = tmp;
            }
            assertEquals(kaplan.getEkwipunek().getWyekwipowanaBron().zadajObrazenia(),bronFizyczna.zadajObrazenia(), obrazeniaMax/1.3);
        }
    }

    @Test
    public void otrzymajObrazenia() {
        Kaplan kaplan1 = new Kaplan();

        double obrazenia =  12;
        kaplan.zmniejszPunktyZycia(obrazenia);
        kaplan1.otrzymajObrazenia(12);

        assertEquals(kaplan.getObecnePunktyZycia(),kaplan1.getObecnePunktyZycia(),12);
    }

    @Test
    public void zwiekszLevel() {
        int newPoziom = kaplan.getPoziom() + 1;
        double newPD = kaplan.getPunktyDoswiadczenia() % 300;
        double newSila = kaplan.getSila() + 5;
        double newInteligencja = kaplan.getInteligencja() + 5;
        kaplan.zwiekszLevel();
        assertEquals(kaplan.getPoziom(),newPoziom);
        assertEquals(kaplan.getPunktyDoswiadczenia(),newPD,0.001);
        assertEquals(kaplan.getSila(),newSila,0.001);
        assertEquals(kaplan.getInteligencja(),newInteligencja,0.001);
    }

    @Test
    public void generujEkwipunek() {
        assertEquals(kaplan.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(kaplan.getEkwipunek().getEkwipunekFabularne().size(),0);
        assertEquals(kaplan.getEkwipunek().getEkwipunekBronMagiczna().size(),1,1);
        assertEquals(kaplan.getEkwipunek().getEkwipunekBronFizyczna().size(),1,1);
        assertEquals(kaplan.getEkwipunek().getEkwipunekPozywienie().size(),2);
    }
}