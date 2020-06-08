package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.List;
import java.util.Random;

public class Boss extends Wrog {
    private double szansaNaTrafienieKrytyczne;

    public Boss(String imie, int maksymalnePunktyZycia, int bazowyAtak , PrzedmiotFabularny przedmiotFabularny){
        super(imie,maksymalnePunktyZycia,bazowyAtak,przedmiotFabularny);
        szansaNaTrafienieKrytyczne = Math.random() * 50;
        //this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    public void dodajKolejnyPrzedmiotFabularny(PrzedmiotFabularny przedmiotFabularny){
        this.getEkwipunek().wlozPrzedmiotFabularny(przedmiotFabularny);
    }

    PrzedmiotFabularny wygranaBohatera(int indeks){
        PrzedmiotFabularny przedmiot = this.getEkwipunek().getEkwipunekFabularne().get(indeks);
        System.out.print("Pokonales mnie, na farcie..... i tak ostatniecznie przegrasz byczq!");
        return przedmiot;
    }

    public double getSzansaNaTrafienieKrytyczne() {
        return szansaNaTrafienieKrytyczne;
    }

    public void setSzansaNaTrafienieKrytyczne(double szansaNaTrafienieKrytyczne) {
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
    }

    /**
     * Boss na początku dostaje:
     * to samo co Wrog
     * dodatkowe dostaje drugą bron, magiczna lub fizyczna, inną niz dostal przez generator Wroga
     * jedzenie lub potion *2 - szansa 25 na kazda kombiancje:  JJ JP PJ PP
     */
    @Override
    public Ekwipunek generujEkwipunek() {
        //System.out.println("GENERATOR DLA BOSA"); test czy korzysta z dobrego generatora -> zaliczony
        Ekwipunek ekwipunekTMP = super.generujEkwipunek();
        ekwipunekTMP.setTYP(TYP_POSIADACZA_EKWIPUNKU.BOSS);
        Random rand = new Random();
        List<Para<String, String>> pozywienieTMP = List.of(
                new Para<>("Mikstura Lowcy", "opis"),
                new Para<>("Elikis Gniewu", "opis"),
                new Para<>("Eliksir Zdrowia", "opis"),
                new Para<>("Jablko", "opis"),
                new Para<>("Chleb", "opis"),
                new Para<>("Mieso", "opis")
        );
        List<Para<String, String>> bronFizycznaTMP = List.of(
                new Para<>("Rteciowy Bula", "opis"),
                new Para<>("Wlucznia Shojin", "opis"),
                new Para<>("Plonoce Ostrze", "opis"),
                new Para<>("Czarny Tasak", "opis")
        );
        List<Para<String, String>> bronMagicznaTMP = List.of(
                new Para<>("Rozdzka Wiekow", "opis"),
                new Para<>("Kostur Pustki", "opis"),
                new Para<>("Echo Luden", "opis"),
                new Para<>("Klinga Burzy", "opis")
        );
        if(ekwipunekTMP.getEkwipunekBronMagiczna().size()<1) {
            int pairIndex = rand.nextInt(bronFizycznaTMP.size());
            ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                    bronFizycznaTMP.get(pairIndex).getPierwszy(),
                    bronFizycznaTMP.get(pairIndex).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }
        if(ekwipunekTMP.getEkwipunekBronMagiczna().size()<1){
            int pairIndex = rand.nextInt(bronFizycznaTMP.size());
            ekwipunekTMP.wlozBronMagiczna(new BronMagiczna(
                    bronMagicznaTMP.get(pairIndex).getPierwszy(),
                    bronMagicznaTMP.get(pairIndex).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }

        for (int i = 0; i < 2; i++) {
            int pairIndex = rand.nextInt(pozywienieTMP.size());
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    pozywienieTMP.get(pairIndex).getPierwszy(),
                    pozywienieTMP.get(pairIndex).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100
            ));
        }
        return ekwipunekTMP;
    }
}
