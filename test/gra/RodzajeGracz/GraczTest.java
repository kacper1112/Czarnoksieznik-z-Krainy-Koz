package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.Przedmiot;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraczTest {

    private final Gracz gracz1 = new Wojownik();
    private final Gracz gracz2 = new Mag();
    private final Gracz gracz3 = new Kaplan();

    @Test
    public void getMaksymalnePunktyZycia() {
        assertEquals(gracz1.getMaksymalnePunktyZycia(),300,0.0001);
        assertEquals(gracz2.getMaksymalnePunktyZycia(),200,0.0001);
        assertEquals(gracz3.getMaksymalnePunktyZycia(),230,0.0001);
    }

    @Test
    public void getEkwipunek() {
        assertEquals(gracz1.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(gracz2.getEkwipunek().pokazEkwipunek(),3);
        assertEquals(gracz3.getEkwipunek().pokazEkwipunek(),3);
    }

    @Test
    public void getObecnePunktyZycia() {
        assertEquals(gracz1.getObecnePunktyZycia(),300,0.0001);
        assertEquals(gracz2.getObecnePunktyZycia(),200,0.0001);
        assertEquals(gracz3.getObecnePunktyZycia(),230,0.0001);
    }

    @Test
    public void getSila() {
        assertEquals(gracz1.getSila(),20,0.0001);
        assertEquals(gracz2.getSila(),10,0.0001);
        assertEquals(gracz3.getSila(),15,0.0001);
    }

    @Test
    public void getInteligencja() {
        assertEquals(gracz1.getInteligencja(),10,0.0001);
        assertEquals(gracz2.getInteligencja(),20,0.0001);
        assertEquals(gracz3.getInteligencja(),15,0.0001);
    }

    @Test
    public void getPoziom() {
        assertEquals(gracz1.getPoziom(),1,0.0001);
        assertEquals(gracz2.getPoziom(),1,0.0001);
        assertEquals(gracz3.getPoziom(),1,0.0001);
    }

    @Test
    public void getPunktyDoswiadczenia() {
        assertEquals(gracz1.getPunktyDoswiadczenia(),0,0.0001);
        assertEquals(gracz2.getPunktyDoswiadczenia(),0,0.0001);
        assertEquals(gracz3.getPunktyDoswiadczenia(),0,0.0001);
    }

    @Test
    public void getPieniadze() {
        assertEquals(gracz1.getPoziom(),1,0.0001);
        assertEquals(gracz2.getPoziom(),1,0.0001);
        assertEquals(gracz3.getPoziom(),1,0.0001);
    }

    @Test
    public void getSumaBonusowDoInteligencji() {
        assertEquals(gracz1.getSumaBonusowDoInteligencji(),5,5);
        assertEquals(gracz2.getSumaBonusowDoInteligencji(),15,15);
        assertEquals(gracz3.getSumaBonusowDoInteligencji(),10,10);
    }

    @Test
    public void getSumaBonusowDoSily() {
        assertEquals(gracz1.getSumaBonusowDoSily(),15,15);
        assertEquals(gracz2.getSumaBonusowDoSily(),5,5);
        assertEquals(gracz3.getSumaBonusowDoSily(),10,10);
    }

    @Test
    public void getSzansaNaNatychmiastoweZabicie() {

        double sumaBonusow1 = 0;
        for (Przedmiot p : gracz1.getEkwipunek().getEkwipunekFabularne()) {
            sumaBonusow1 += p.getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }
        sumaBonusow1 += ((BronFizyczna) gracz1.getEkwipunek().getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();


        double sumaBonusow2 = 0;
        for (Przedmiot p : gracz2.getEkwipunek().getEkwipunekFabularne()) {
            sumaBonusow2 += p.getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }
        sumaBonusow2 += ((BronMagiczna) gracz2.getEkwipunek().getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();


        double sumaBonusow3 = 0;
        for (Przedmiot p : gracz3.getEkwipunek().getEkwipunekFabularne()) {
            sumaBonusow3 += p.getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }
        if (gracz3.getEkwipunek().getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow3 += ((BronFizyczna) gracz3.getEkwipunek().getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        } else {
            sumaBonusow3 += ((BronMagiczna) gracz3.getEkwipunek().getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }


        assertEquals(gracz1.getSzansaNaNatychmiastoweZabicie(),sumaBonusow1,0.001);
        assertEquals(gracz2.getSzansaNaNatychmiastoweZabicie(),sumaBonusow2,0.001);
        assertEquals(gracz3.getSzansaNaNatychmiastoweZabicie(),sumaBonusow3,0.001);
    }

    @Test
    public void setSila() {
        gracz1.setSila(200);
        gracz2.setSila(100);
        gracz3.setSila(150);
        assertEquals(gracz1.getSila(),200,0.0001);
        assertEquals(gracz2.getSila(),100,0.0001);
        assertEquals(gracz3.getSila(),150,0.0001);
    }

    @Test
    public void setInteligencja() {
        gracz1.setInteligencja(200);
        gracz2.setInteligencja(100);
        gracz3.setInteligencja(150);
        assertEquals(gracz1.getInteligencja(),200,0.0001);
        assertEquals(gracz2.getInteligencja(),100,0.0001);
        assertEquals(gracz3.getInteligencja(),150,0.0001);
    }

    @Test
    public void setPoziom() {
        gracz1.setPoziom(200);
        gracz2.setPoziom(100);
        gracz3.setPoziom(150);
        assertEquals(gracz1.getPoziom(),200,0.0001);
        assertEquals(gracz2.getPoziom(),100,0.0001);
        assertEquals(gracz3.getPoziom(),150,0.0001);
    }

    @Test
    public void setPunktyDoswiadczenia() {
        gracz1.setPunktyDoswiadczenia(200);
        gracz2.setPunktyDoswiadczenia(100);
        gracz3.setPunktyDoswiadczenia(150);
        assertEquals(gracz1.getPunktyDoswiadczenia(),200,0.0001);
        assertEquals(gracz2.getPunktyDoswiadczenia(),100,0.0001);
        assertEquals(gracz3.getPunktyDoswiadczenia(),150,0.0001);
    }

    @Test
    public void setPieniadze() {
        gracz1.setPieniadze(200);
        gracz2.setPieniadze(100);
        gracz3.setPieniadze(150);
        assertEquals(gracz1.getPieniadze(),200,0.0001);
        assertEquals(gracz2.getPieniadze(),100,0.0001);
        assertEquals(gracz3.getPieniadze(),150,0.0001);
    }

    @Test
    public void zmniejszPunktyZycia() {
        gracz1.zmniejszPunktyZycia(10);
        gracz2.zmniejszPunktyZycia(10);
        gracz3.zmniejszPunktyZycia(10);
        assertEquals(gracz1.getObecnePunktyZycia(),290,0.0001);
        assertEquals(gracz2.getObecnePunktyZycia(),190,0.0001);
        assertEquals(gracz3.getObecnePunktyZycia(),220,0.0001);
    }

    @Test
    public void zwiekszPunktyZycia() {
        gracz1.zmniejszPunktyZycia(10);
        gracz2.zmniejszPunktyZycia(10);
        gracz3.zmniejszPunktyZycia(10);
        gracz1.zwiekszPunktyZycia(10);
        gracz2.zwiekszPunktyZycia(10);
        gracz3.zwiekszPunktyZycia(10);
        assertEquals(gracz1.getObecnePunktyZycia(),300,0.0001);
        assertEquals(gracz2.getObecnePunktyZycia(),200,0.0001);
        assertEquals(gracz3.getObecnePunktyZycia(),230,0.0001);
    }

    @Test
    public void uzyjPozywienia() {
        Gracz graczTMP = gracz1;
        gracz1.zmniejszPunktyZycia(10);
        gracz1.uzyjPozywienia(0);
        assertEquals(graczTMP.getObecnePunktyZycia(),gracz1.getObecnePunktyZycia(),0.001);
    }

    @Test
    public void testToString() {
        String tmp = "Obecne statystyki gracza:\n" +
                "\t punkty zycia: " + String.format("%1.2f", gracz1.getObecnePunktyZycia()) + "/" + gracz1.getMaksymalnePunktyZycia() + "\n" +
                "\t sila: " + gracz1.getSila() + "\n" +
                "\t inteligencja: " + gracz1.getInteligencja() + "\n" +
                "\t poziom: " + gracz1.getPoziom() + "\n" +
                "\t punkty doswiadczenia: " + String.format("%1.2f", gracz1.getPunktyDoswiadczenia()) + "\n" +
                "\t pieniadze: " + gracz1.getPieniadze() + "\n" +
                "\t wyekwipowana bron: " + gracz1.getEkwipunek().getWyekwipowanaBron() + "\n";

        assertEquals(tmp,gracz1.toString());
    }
}