package gra;

import java.util.List;
import java.util.Random;

public class Boss extends Wrog {
    private double szansaNaTrafienieKrytyczne;

    public Boss(String imie, PrzedmiotFabularny przedmiotFabularny) {
        super(imie);
        Random random = new Random();
        szansaNaTrafienieKrytyczne = random.nextInt(50)+10;
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    public void dodajKolejnyPrzedmiotFabularny(PrzedmiotFabularny przedmiotFabularny){
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    PrzedmiotFabularny wygranaBohatera(int indeks){
        PrzedmiotFabularny przedmiot = (PrzedmiotFabularny) this.getEkwipunek().getEkwipunekFabularne().get(indeks);
        System.out.print("Pokonales mnie, na farcie..... i tak ostatniecznie przegrasz byczq!");
        return przedmiot;
    }

    public double getSzansaNaTrafienieKrytyczne() {
        return szansaNaTrafienieKrytyczne;
    }

    public void setSzansaNaTrafienieKrytyczne(double szansaNaTrafienieKrytyczne) {
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        //System.out.println("GENERATOR DLA BOSA"); test czy korzysta z dobrego generatora -> zaliczony
        Ekwipunek ekwipunekTMP = super.generujEkwipunek();
        ekwipunekTMP.setTYP(TYP_POSIADACZA_EKWIPUNKU.BOSS);
        Random rand = new Random();
        List<Para<String, String> > pozywienieTMP = List.of(
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
                    rand.nextDouble()*100,
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
                    rand.nextDouble()*100,
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
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100
            ));
        }
        return ekwipunekTMP;
    }
}
