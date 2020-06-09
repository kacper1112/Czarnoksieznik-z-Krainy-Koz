package gra.ElementyPomocnicze;

import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZagadkaTest {
    private final Gracz gracz = new Wojownik();
    private final Zagadka zagadka = new Zagadka("a","b",new PrzedmiotFabularny("pf",null,0,0,false,null,0));

    @Test
    public void wywolajZagadke() {
    }
}