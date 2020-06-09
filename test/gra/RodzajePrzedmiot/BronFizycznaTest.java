package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class BronFizycznaTest {

    private final BronFizyczna bronFizyczna = new BronFizyczna("bf","bf",100,100,100,100);

    @Test
    public void zadajObrazenia() {
        double tmp = bronFizyczna.zadajObrazenia();
        assertEquals(tmp,bronFizyczna.zadajObrazenia(),20000);
    }

    @Test
    public void zadajMocneObrazenia() {
        double tmp = bronFizyczna.zadajMocneObrazenia();
        assertEquals(tmp,bronFizyczna.zadajMocneObrazenia(),20000);
    }

    @Test
    public void testToString() {
        String tmp = bronFizyczna.toString();
        assertEquals(tmp,bronFizyczna.getNazwa() + " [obrażenia: " + String.format("%1.2f", 100.0) + ", ostrosc: " + "100" + ", moc ud. krytycznego: " +
                String.format("%1.2f", (100.0 + 100)) + "%]");
    }
}