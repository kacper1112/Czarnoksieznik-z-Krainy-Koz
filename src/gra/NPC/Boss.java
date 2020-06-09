package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.List;
import java.util.Random;

public class Boss extends Wrog {
    private final double szansaNaTrafienieKrytyczne;

    public Boss(String imie, int maksymalnePunktyZycia, int bazowyAtak , PrzedmiotFabularny przedmiotFabularny){
        super(imie,maksymalnePunktyZycia,bazowyAtak,przedmiotFabularny);
        szansaNaTrafienieKrytyczne = Math.random() * 50;
    }

    @Override
    public double zadajObrazenia() {
        if(Math.random() < szansaNaTrafienieKrytyczne) {
            KolorTekstu.printCzerwony("Uderzenie krytyczne!");
            return super.zadajObrazenia() * 1.5;
        }
        return super.zadajObrazenia();
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
                new Para<>("Mikstura Lowcy", "Ta miksutra odnawia twoje punkty zycia"),
                new Para<>("Elikis Gniewu", "Eliksir uzdrawiajacy twoje punkty zycia"),
                new Para<>("Eliksir Zdrowia", "Eliksir uzdrawiajacy twoje punkty zycia"),
                new Para<>("Jablko", "Pyszne jabluszko, ktore zawiera zdrowe witaminki"),
                new Para<>("Chleb", "Swiezo wypieczony chleb, z tutejszej piekarni"),
                new Para<>("Mieso", "Mieso zdobyte na tutejszej farmie zwierzat")
        );
        List<Para<String, String>> bronFizycznaTMP = List.of(
                new Para<>("Rteciowy Bula", "Straszliwy bulat, lamiacy kosci przeciwnikow"),
                new Para<>("Wlucznia Shojin", "Prastara wlocznia, pamietajaca jeszcze tutejszych mnichow"),
                new Para<>("Plonoce Ostrze", "Ta bron plonie zywym ogniem, badz ostrozny"),
                new Para<>("Czarny Tasak", "Tasak niegdys uzywany do skorowania smokow")
        );
        List<Para<String, String>> bronMagicznaTMP = List.of(
                new Para<>("Rozdzka Wiekow", "Prastara rozdzka, ktora byla uzywana przez poteznego czarodzieja"),
                new Para<>("Kostur Pustki", "Kostur uzywany do wywolywania niesamowitej magii"),
                new Para<>("Echo Luden", "Rozowa rozdzka mocy"),
                new Para<>("Klinga Burzy", "Klinga zrodzona podczas jednej z najstraszniejszych burz jakie widzialo niebo")
        );
        if(ekwipunekTMP.getEkwipunekBronMagiczna().size()<1) {
            int pairIndex = rand.nextInt(bronFizycznaTMP.size());
            ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
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
            ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
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
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    pozywienieTMP.get(pairIndex).getPierwszy(),
                    pozywienieTMP.get(pairIndex).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100
            ));
        }

        if(rand.nextInt(10)>=5){
            ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0, true);
        } else {
            ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0, true);
        }
        return ekwipunekTMP;
    }
}
