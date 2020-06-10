package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.GraWlasciwa.Gra;
import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Handlarz extends NPC {
    private int pieniadze;
    private List<Para<? extends Przedmiot, Para<Integer, Integer>>> oferta;

    public Handlarz(String imie) {
        super(imie);
        Random random = new Random();
        int bonus = random.nextInt(100);
        pieniadze = 300 + bonus;
    }

    //METODY WLASCIWE
    @Override
    public Ekwipunek generujEkwipunek() {
        oferta = new ArrayList<>();
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.HANDLARZ);
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
                new Para<>("Wlocznia Shojin", "Prastara wlocznia, pamietajaca jeszcze tutejszych mnichow"),
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

        int pairIndex = rand.nextInt(bronFizycznaTMP.size());
        ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
                bronFizycznaTMP.get(pairIndex).getPierwszy(),
                bronFizycznaTMP.get(pairIndex).getDrugi(),
                rand.nextInt(100),
                rand.nextInt(100),
                rand.nextDouble() * 100,
                Math.random()
        ));

        pairIndex = rand.nextInt(bronFizycznaTMP.size());
        ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
                bronMagicznaTMP.get(pairIndex).getPierwszy(),
                bronMagicznaTMP.get(pairIndex).getDrugi(),
                rand.nextInt(100),
                rand.nextInt(100),
                rand.nextDouble() * 100,
                Math.random()
        ));

        pairIndex = rand.nextInt(pozywienieTMP.size());
        ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                pozywienieTMP.get(pairIndex).getPierwszy(),
                pozywienieTMP.get(pairIndex).getDrugi(),
                rand.nextInt(100),
                rand.nextInt(100),
                rand.nextDouble() * 100
        ));
        AtomicInteger licznik = new AtomicInteger(1);
        ekwipunekTMP.getEkwipunekBronFizyczna()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() + rand.nextInt(x.getWartosc() / 2 + 1))))));
        ekwipunekTMP.getEkwipunekBronMagiczna()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() + rand.nextInt(x.getWartosc() / 2 + 1))))));
        ekwipunekTMP.getEkwipunekPozywienie()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() + rand.nextInt(x.getWartosc() / 4 + 1))))));

        return ekwipunekTMP;
    }

    private void resetujOferte(){
        oferta = new ArrayList<>();
        Random rand = new Random();
        AtomicInteger licznik = new AtomicInteger(1);
        getEkwipunek().getEkwipunekBronFizyczna()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() - rand.nextInt(x.getWartosc() / 2))))));
        getEkwipunek().getEkwipunekBronMagiczna()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() - rand.nextInt(x.getWartosc() / 2))))));
        getEkwipunek().getEkwipunekPozywienie()
                .forEach(x -> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(), (x.getWartosc() - rand.nextInt(x.getWartosc() - 1))))));
    }

    public void przedstawOferte() {
        if (oferta.size() < 1) {
            System.out.println("Brak przedmiotow do kupienia.");
            return;
        }
        KolorTekstu.printFioletowy("    Oto moja oferta dla ciebie przybyszu:");

        oferta.forEach(x -> KolorTekstu.printFioletowy("    " + x.getDrugi().getPierwszy() + ". " + x.getPierwszy() + " - cena: " + x.getDrugi().getDrugi()));
    }

    public void sprzedajGraczowi(Gracz gracz, int indeksOferty) {
        if (oferta.size() < 1) {
            System.out.println("Brak przedmiotow do kupienia.");
        } else if (oferta.get(indeksOferty).getDrugi().getDrugi() > gracz.getPieniadze()) {
            System.out.println("Nie masz wystarczajacej ilosci pieniedzy aby kupic ten przedmiot");
            KolorTekstu.printZielony("Twoj zlote monety: " + gracz.getPieniadze());
            return;
        } else if (oferta.get(indeksOferty).getPierwszy() instanceof PrzedmiotPozywienie) {
            gracz.getEkwipunek().wlozDoEkwipunku(oferta.get(indeksOferty).getPierwszy());
        } else if (oferta.get(indeksOferty).getPierwszy() instanceof BronFizyczna) {
            gracz.getEkwipunek().wlozDoEkwipunku(oferta.get(indeksOferty).getPierwszy());
        } else if (oferta.get(indeksOferty).getPierwszy() instanceof BronMagiczna) {
            gracz.getEkwipunek().wlozDoEkwipunku(oferta.get(indeksOferty).getPierwszy());
        }
        System.out.println("Właśnie kupiłeś: " + oferta.get(indeksOferty).getPierwszy());
        gracz.setPieniadze(gracz.getPieniadze() - oferta.get(indeksOferty).getDrugi().getDrugi());
        this.setPieniadze(this.getPieniadze() + oferta.get(indeksOferty).getDrugi().getDrugi());

        oferta = oferta.stream()
                .filter(x -> !(x.getPierwszy().getNazwa().equals(oferta.get(indeksOferty).getPierwszy().getNazwa())))
                .collect(Collectors.toList());
        System.out.println(oferta);
        resetujOferte();

    }

    private int zgodnaNaOferte() {
        Scanner in = new Scanner(System.in);
        int wybor;
        while (true) {
            System.out.println("Wybierz 1 jesli sprzedajesz");
            System.out.println("Wybierz 2 jesli NIE sprzedajesz");
            wybor = in.nextInt();
            if (wybor == 1 || wybor == 2) {
                break;
            } else {
                System.out.println("Zly wybor decyzji");
            }
        }
        return wybor;
    }

    public void kupOdGracza(Gracz gracz, Przedmiot przedmiot) {
        Random rand = new Random();
        int pieniadzeOferta;

        if (przedmiot instanceof BronMagiczna) {
            if(przedmiot.getNazwa().equals(((BronMagiczna)Gra.getInstance().getGracz().getEkwipunek().getWyekwipowanaBron()).getNazwa())){
                KolorTekstu.printZielony("Nie moge kupic twojej glownej bronii!");
                return;
            }
            KolorTekstu.printZielony("Twoje zlote monety: " + Gra.getInstance().getGracz().getPieniadze());
            pieniadzeOferta = (przedmiot.getWartosc() - rand.nextInt(przedmiot.getWartosc() / 2));
            System.out.println("Za tę bron magiczna moge Ci zaoferowac: " + pieniadzeOferta);
            if (zgodnaNaOferte() == 1) {
                int indeks = (int) gracz.getEkwipunek().getEkwipunekBronMagiczna().stream().filter(x -> x.getNazwa().equals(przedmiot.getNazwa())).count() - 1;
                gracz.getEkwipunek().wyciagnijBronMagiczna(indeks);
                this.getEkwipunek().wlozDoEkwipunku(przedmiot);
                gracz.setPieniadze(gracz.getPieniadze() + pieniadzeOferta);
            }
        } else if (przedmiot instanceof BronFizyczna) {
            if(przedmiot.getNazwa().equals(((BronFizyczna)Gra.getInstance().getGracz().getEkwipunek().getWyekwipowanaBron()).getNazwa())){
                KolorTekstu.printZielony("Nie moge kupic twojej glownej bronii!");
                return;
            }
            KolorTekstu.printZielony("Twoje zlote monety: " + Gra.getInstance().getGracz().getPieniadze());
            pieniadzeOferta = (przedmiot.getWartosc() - rand.nextInt(przedmiot.getWartosc() / 2));
            System.out.println("Za tę bron fizyczna moge Ci zaoferowac: " + pieniadzeOferta);
            if (zgodnaNaOferte() == 1) {
                int indeks = (int) gracz.getEkwipunek().getEkwipunekBronFizyczna().stream().filter(x -> x.getNazwa().equals(przedmiot.getNazwa())).count() - 1;
                gracz.getEkwipunek().wyciagnijBronFizyczna(indeks);
                this.getEkwipunek().wlozDoEkwipunku(przedmiot);
                gracz.setPieniadze(gracz.getPieniadze() + pieniadzeOferta);
            }
        } else if (przedmiot instanceof PrzedmiotPozywienie) {
            KolorTekstu.printZielony("Twoje zlote monety: " + Gra.getInstance().getGracz().getPieniadze());
            pieniadzeOferta = (przedmiot.getWartosc() - rand.nextInt(przedmiot.getWartosc() - 1));
            System.out.println("Za to pozywienie moge Ci zaoferowac: " + pieniadzeOferta);
            if (zgodnaNaOferte() == 1) {
                int indeks = (int) gracz.getEkwipunek().getEkwipunekPozywienie().stream().filter(x -> x.getNazwa().equals(przedmiot.getNazwa())).count() - 1;
                gracz.getEkwipunek().wyciagnijPozywienie(indeks);
                this.getEkwipunek().wlozDoEkwipunku(przedmiot);
                gracz.setPieniadze(gracz.getPieniadze() + pieniadzeOferta);
            }
        } else if (przedmiot instanceof PrzedmiotFabularny) {
            System.out.println("Nie moge tego od Ciebie kupic przybyszu, to zrujnuje twoje dalsze losy!");
        }

    }

    public int getPieniadze() {
        return pieniadze;
    }

    public void setPieniadze(int pieniadze) {
        this.pieniadze = pieniadze;
    }

    public List<Para<? extends Przedmiot, Para<Integer, Integer>>> getOferta() {
        return oferta;
    }
}
