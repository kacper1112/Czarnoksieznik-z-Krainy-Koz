package gra.ElementyPomocnicze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class KolorTekstuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void printZielonyBezNL() {
        KolorTekstu.printZielonyBezNL("a");
        String tmp = KolorTekstu.GREEN + "a" + KolorTekstu.RESET;
        assertEquals(tmp, outContent.toString());
    }

    @Test
    public void printZielonyItaliki() {
        KolorTekstu.printZielonyItaliki("a");
        String tmp = KolorTekstu.ITALIC + KolorTekstu.GREEN + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void printZielony() {
        KolorTekstu.printZielony("a");
        String tmp = KolorTekstu.GREEN + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void printCzerwony() {
        KolorTekstu.printCzerwony("a");
        String tmp = KolorTekstu.RED + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void printZolty() {
        KolorTekstu.printZolty("a");
        String tmp = KolorTekstu.YELLOW + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void printFioletowy() {
        KolorTekstu.printFioletowy("a");
        String tmp = KolorTekstu.PURPLE + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }

    @Test
    public void printCyan() {
        KolorTekstu.printCyan("a");
        String tmp = KolorTekstu.CYAN + "a" + KolorTekstu.RESET;
        assertEquals(tmp.trim(), outContent.toString().trim());
    }
}