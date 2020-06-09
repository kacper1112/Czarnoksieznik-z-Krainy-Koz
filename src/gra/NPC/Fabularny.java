package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fabularny extends NPC {
    private final PrzedmiotFabularny przedmiotFabularny;
    private final boolean czyPosiadaPrzedmiotFabularny;
    private List<? super Przedmiot> podarki;

    public Fabularny(String imie, boolean czyPosiadaPrzedmiotFabularny, PrzedmiotFabularny przedmiotFabularny) {
        super(imie);
        this.przedmiotFabularny = przedmiotFabularny;
        this.czyPosiadaPrzedmiotFabularny = true;
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        podarki = new ArrayList<>();
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.FABULARNY);

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

        Random rand = new Random();
        if (10 < rand.nextInt(100)) {
            int indeks = rand.nextInt(bronFizycznaTMP.size() + bronMagicznaTMP.size());
            if (indeks < bronFizycznaTMP.size()) {
                ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
                        bronFizycznaTMP.get(indeks).getPierwszy(),
                        bronFizycznaTMP.get(indeks).getDrugi(),
                        rand.nextInt(100),
                        rand.nextInt(100),
                        rand.nextDouble() * 100,
                        Math.random()
                ));
                if (ekwipunekTMP.getEkwipunekBronFizyczna().size() > 0)
                    podarki.addAll(ekwipunekTMP.getEkwipunekBronFizyczna());
            } else {
                indeks -= bronFizycznaTMP.size();
                ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
                        bronMagicznaTMP.get(indeks).getPierwszy(),
                        bronMagicznaTMP.get(indeks).getDrugi(),
                        rand.nextInt(100),
                        rand.nextInt(100),
                        rand.nextDouble() * 100,
                        Math.random()
                ));

                if (ekwipunekTMP.getEkwipunekBronMagiczna().size() > 0)
                    podarki.addAll(ekwipunekTMP.getEkwipunekBronMagiczna());
            }
        } else {
            int indeks = rand.nextInt(pozywienieTMP.size());
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    pozywienieTMP.get(indeks).getPierwszy(),
                    pozywienieTMP.get(indeks).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble() * 100
            ));
            if (ekwipunekTMP.getEkwipunekPozywienie().size() > 0)
                podarki.addAll(ekwipunekTMP.getEkwipunekPozywienie());
        }

        return ekwipunekTMP;
    }

    public Przedmiot podarujLosowyPrzedmiotNieFabularny() {
        Random random = new Random();
        int indeks = random.nextInt(podarki.size());
        KolorTekstu.printCyan( "To dla ciebie: " + podarki.get(indeks).toString());
        KolorTekstu.printCyan( "Niby mały podarek ale zawsze cos.");
        return (Przedmiot) podarki.get(indeks);
    }

    public PrzedmiotFabularny podarujPrzedmiotFabularny() {
        KolorTekstu.printCyanBezNL( "Przekazuje Ci ten ważny przedmiot: ");
        KolorTekstu.printCzerwony(przedmiotFabularny.toString());
        return this.przedmiotFabularny;
    }

    public boolean isCzyPosiadaPrzedmiotFabularny() {
        return czyPosiadaPrzedmiotFabularny;
    }
}
