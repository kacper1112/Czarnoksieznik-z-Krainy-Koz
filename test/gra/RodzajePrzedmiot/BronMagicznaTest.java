package gra.RodzajePrzedmiot;

import org.junit.Test;

import static org.junit.Assert.*;

public class BronMagicznaTest {

    private final BronMagiczna bronMagiczna = new BronMagiczna("magiczna","ops",100,100,100,100);

    @Test
    public void zadajObrazenia() {
        double tmp = bronMagiczna.zadajObrazenia();
        assertEquals(tmp,bronMagiczna.zadajObrazenia(),20000);
    }

    @Test
    public void zadajMocneObrazenia() {
        double tmp = bronMagiczna.zadajMocneObrazenia();
        assertEquals(tmp,bronMagiczna.zadajMocneObrazenia(),20000);
    }

    @Test
    public void testToString() {
        String tmp = bronMagiczna.toString();
        assertEquals(tmp,bronMagiczna.getNazwa() + " [obrazenia: " + String.format("%1.2f", 100.0) + ", pozostala moc magiczna: " + 100 +
                ", moc ud. krytycznego: " + String.format("%1.2f", (100.0 + 100)) + "%]");
    }
}