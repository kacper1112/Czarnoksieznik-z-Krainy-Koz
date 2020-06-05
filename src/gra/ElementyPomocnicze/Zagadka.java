package gra.ElementyPomocnicze;

import java.util.Scanner;

public class Zagadka {
    private final String zagadka;
    private final String odpowiedz;
    Scanner in = new Scanner(System.in);

    public Zagadka(String zagadka, String odpowiedz) {
        this.zagadka = zagadka;
        this.odpowiedz = odpowiedz;
    }

    public void wywolajZagadke() {
        String odpowiedzGracza = "";
        while(!odpowiedzGracza.equals(this.odpowiedz)) {
            System.out.println(this.zagadka);

        }
    }
}
