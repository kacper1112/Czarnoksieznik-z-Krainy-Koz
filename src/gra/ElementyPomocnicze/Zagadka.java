package gra.ElementyPomocnicze;

import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;

import java.util.Scanner;

public class Zagadka {
    private final String zagadka;
    private final String odpowiedz;
    private final PrzedmiotFabularny przedmiotFabularny;
    Scanner in = new Scanner(System.in);

    public Zagadka(String zagadka, String odpowiedz, PrzedmiotFabularny przedmiotFabularny) {
        this.zagadka = zagadka;
        this.odpowiedz = odpowiedz;
        this.przedmiotFabularny = przedmiotFabularny;
    }

    public void wywolajZagadke(Gracz gracz) {
        String odpowiedzGracza = "";
        while(!odpowiedzGracza.equals(this.odpowiedz)) {
            System.out.println(this.zagadka);
            odpowiedzGracza = in.nextLine().trim();
        }
        gracz.getEkwipunek().wlozPrzedmiotFabularny(przedmiotFabularny);

    }


}
