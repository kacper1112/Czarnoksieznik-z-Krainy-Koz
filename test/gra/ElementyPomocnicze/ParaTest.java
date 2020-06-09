package gra.ElementyPomocnicze;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParaTest {

    private final Para<Integer,String> para = new Para<>(123,"hejbyczq");

    @Test
    public void getPierwszy() {
        int tmp = para.getPierwszy();
        assertEquals(123, tmp);
    }

    @Test
    public void getDrugi() {
        String tmp = para.getDrugi();
        assertEquals("hejbyczq", tmp);
    }

    @Test
    public void setPierwszy() {
        para.setPierwszy(987);
        int tmp = para.getPierwszy();
        assertEquals(987,tmp);
    }

    @Test
    public void setDrugi() {
        para.setDrugi("word");
        String tmp = para.getDrugi();
        assertEquals("word",tmp);
    }

    @Test
    public void testEquals() {
        Para<Integer,String> TMP = new Para<>(123,"hejbyczq");
        assertEquals(TMP,para);
    }

    @Test
    public void testHashCode() {
        Para<Integer,String> TMP = new Para<>(123,"hejbyczq");
        int hashcodeTMP = -858856740;
        assertEquals(para.hashCode(),hashcodeTMP);
        assertEquals(TMP.hashCode(),para.hashCode());
    }

    @Test
    public void testToString() {
        String tmp = "[" + "123" + ", " + "hejbyczq" + "]";
        assertEquals(para.toString(),tmp);
    }
}