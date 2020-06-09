package gra.NPC;

import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import org.junit.Test;

import static org.junit.Assert.*;

public class BossTest {

    private  final  Boss boss = new Boss("imieBOss",1000,1000,
            new PrzedmiotFabularny("pf","pfO",123,123,false,"wska",123.123));

    @Test
    public void dodajKolejnyPrzedmiotFabularny() {
        boss.getEkwipunek().getEkwipunekFabularne().add(
                new PrzedmiotFabularny("pf2","pfO2",312,321,false,"wska",321.123));
        assertEquals(boss.getEkwipunek().getEkwipunekFabularne().size(),2);
    }

    @Test
    public void generujEkwipunek() {
        assertEquals(boss.getEkwipunek().pokazEkwipunek(),6);
        assertEquals(boss.getEkwipunek().getEkwipunekPozywienie().size(),3);
        assertEquals(boss.getEkwipunek().getEkwipunekBronMagiczna().size(),2,1);
        assertEquals(boss.getEkwipunek().getEkwipunekBronFizyczna().size(),2,1);
        assertEquals(boss.getEkwipunek().getEkwipunekFabularne().size(),1);
    }
}