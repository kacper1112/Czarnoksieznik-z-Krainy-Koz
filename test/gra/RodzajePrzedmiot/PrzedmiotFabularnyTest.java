package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrzedmiotFabularnyTest {

    PrzedmiotFabularny przedmiotFabularny = new PrzedmiotFabularny("pf","pfOpis", 100,100,false,"aaa",100);

    @Test
    public void getWskazowka() {
        assertEquals(przedmiotFabularny.getWskazowka(10000),"aaa");
    }

    @Test
    public void testToString() {
        String tmp = "pf";
        assertEquals(przedmiotFabularny.toString(),tmp);
    }
}