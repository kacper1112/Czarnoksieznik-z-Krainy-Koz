package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Para;
import gra.NPC.Handlarz;
import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Menu {
    private static final Scanner in = new Scanner(System.in);
    private static Gracz gracz;
    private static List<Lokacja> lokacje;

    public static void setGracz(Gracz gracz) {
        Menu.gracz = gracz;
    }

    public static void setLokacje(List<Lokacja> lokacje) {
        Menu.lokacje = lokacje;
    }

    public static boolean menuLokacji() {
        // nie da sie jakos ladniej tego potwora linijke nizej?
        System.out.println("Sasiednie lokacje:");
                lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getSasiednieLokacje().forEach(index -> {
                    System.out.println(index + ": " + lokacje.get(index).getNazwa());
                    //System.out.println(this.lokacje.get(index).getNazwa());
                    //System.out.println(this.lokacje.get(index).getOpis());
                });
        System.out.println("Wybierz numer lokacji do ktorej chcialbys przejsc (0 zeby wyjsc do poprzedniego menu)");
        int wyborLokacji = in.nextInt();

        if(wyborLokacji == 11) {
            if(gracz.getEkwipunek().getEkwipunekFabularne().stream().filter(e -> e.getNazwa().equals("Przepustka 1")).collect(Collectors.toList()).size() != 0) {
                System.out.println("Masz przepustke");
            } else {
                System.out.println("Nie masz przepustki");
            }
        } else if(wyborLokacji == 12) {

        } else if(wyborLokacji == 13) {

        }
        if(wyborLokacji == 0) {
            return false;
        } else if(lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getSasiednieLokacje().contains(wyborLokacji)) {
            Gra.getInstance().setLokalizacjaGracza(wyborLokacji);
            System.out.println("Przechodzisz do: " + lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getNazwa());
            return false;
        } else  {
            System.out.println("Niepoprawna lokacja");
            return false;
        }
    }

    public static void menuEkwipunku(){
        int rozmiarEq;

        if(gracz.getEkwipunek().isEmpty()) {
            System.out.println("Nie masz w ekwipunku wiecej przedmiotow!");
            return;
        }

        if(gracz.getEkwipunek().getWyekwipowanaBron() instanceof BronMagiczna){
            System.out.println("Wyekwipowana bron magiczna:");
            System.out.println(((BronMagiczna)gracz.getEkwipunek().getWyekwipowanaBron()).getNazwa());
        }else if(gracz.getEkwipunek().getWyekwipowanaBron() instanceof BronFizyczna){
            System.out.println("Wyekwipowana bron fizyczna:");
            System.out.println(((BronFizyczna)gracz.getEkwipunek().getWyekwipowanaBron()).getNazwa());
        }

        rozmiarEq = gracz.getEkwipunek().pokazEkwipunek();
        System.out.println("Wybierz przedmiot do uzycia lub bron do wyekwipowania (0 zeby wyjsc do poprzedniego menu)");
        int wyborGracza = Gra.wczytajWyborGracza(rozmiarEq, true);

        // wybor spoza ekwipunku albo chec powrotu
        if(wyborGracza <= 0 || wyborGracza > rozmiarEq) {
            return;
        }

        // obliczamy z ktorej kategorii chcemy wyciagnac przedmiot
        rozmiarEq -= gracz.getEkwipunek().getIloscFabularne();
        if(wyborGracza > rozmiarEq) {
            System.out.println("Uzyto przedmiotu fabularnego");
            gracz.uzyjPrzedmiotuFabularnego(wyborGracza - rozmiarEq - 1);
            return;
        }

        rozmiarEq -= gracz.getEkwipunek().getIloscBronMagiczna();
        if(wyborGracza > rozmiarEq) {
            System.out.println("Wyekwipowano bron magiczna");
            gracz.zmienBronNaMagiczna(wyborGracza - rozmiarEq - 1);
            return;
        }

        rozmiarEq -= gracz.getEkwipunek().getIloscBronFizyczna();
        if(wyborGracza > rozmiarEq) {
            System.out.println("Wyekwipowano bron fizyczna");
            gracz.zmienBronNaFizyczna(wyborGracza - rozmiarEq - 1);
            return;
        }

        rozmiarEq -= gracz.getEkwipunek().getIloscPozywienie();
        if(wyborGracza > rozmiarEq) {
            System.out.println("Uzyto pozywienia");
            gracz.uzyjPozywienia(wyborGracza - rozmiarEq - 1);
            return;
        }

        System.out.println("Cos poszlo nie tak podczas wyboru przedmiotu");
    }

    public static void menuHandlu(){
        System.out.println("MENU HANDLU");
        List<Handlarz> handlarzeTMP = new ArrayList<>();
        lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzeniaPoboczne().forEach(x->{
            if(x.getHandlarz()!=null){
                handlarzeTMP.add(x.getHandlarz());
            }
        });

        List<Para<Integer,Handlarz> > handlarze = new ArrayList<>();
        AtomicInteger nrHandlarza = new AtomicInteger(1);
        handlarzeTMP.forEach(x->handlarze.add(new Para<Integer, Handlarz>(nrHandlarza.getAndIncrement(),x)));
        handlarze.forEach(x->{
            System.out.println(x.getPierwszy() +". Handlarz: " + "Witaj jestem "+ x.getDrugi().getImie() +": ");
            x.getDrugi().przedstawOferte();
        });
        System.out.println("Jeśli chcesz kupic przedmiot wybierz numer Handlarza a następnie numer przedmiotu z oferty:");
        System.out.print("Wybieram Handlarza nr: ");
        int wyborHandlarza = in.nextInt();
        System.out.print("Wybieram Przedmiot nr: ");
        int wyborPrzedmiotu = in.nextInt();
        while (true){
            if (wyborHandlarza > 0 && wyborHandlarza <= handlarze.size()){
                if(wyborPrzedmiotu > 0 && handlarze.get(wyborHandlarza-1).getDrugi().getOferta().size() <= wyborPrzedmiotu){
                    handlarze.get(wyborHandlarza-1)
                            .getDrugi().sprzedajGraczowi(gracz,wyborPrzedmiotu-1);
                    break;
                }else {
                    System.out.println("Niepoprawny wybor przedmiotu podaj, dane handlu jeszcze raz: ");
                    System.out.print("Wybieram Handlarza nr: ");
                    wyborHandlarza = in.nextInt();
                    System.out.print("Wybieram Przedmiot nr: ");
                    wyborPrzedmiotu = in.nextInt();
                }
            }else{
                System.out.println("Niepoprawny wybor handlarza podaj, dane handlu jeszcze raz: ");
                System.out.print("Wybieram Handlarza nr: ");
                wyborHandlarza = in.nextInt();
                System.out.print("Wybieram Przedmiot nr: ");
                wyborPrzedmiotu = in.nextInt();
            }
        }
    }

    public static boolean menuGlowne(){
        System.out.println("1. Pokaż Moje Statystyki\n" +
                "2. Pokaż ekwipunek\n" + "3. Przejdź do innej lokalizacji");

        if(lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzenieFabularne().getHandlarz()!=null ||
                (lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzeniaPoboczne()!=null &&
                        lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzeniaPoboczne().stream()
                        .anyMatch(x-> x.getHandlarz()!=null))){
            System.out.println("4. Handluj z handlarzem");
        }

        int wybor = in.nextInt(); //Gra.wczytajWyborGracza(3);
        switch (wybor){
            case 1:
                gracz.wlozBronMagicznaDoEwkipunku(new BronMagiczna("jakas bron", "super bronka to jest",
                        10, 10, 10, 10));
                System.out.println(gracz);
                return true;
            case 2:
                menuEkwipunku();
                return true;
            case 4:
                menuHandlu();
                return true;
            case 3:
                return menuLokacji();
            default:
                System.out.println("Cos poszlo nie tak, sprobuj jeszcze raz");
                return true;
        }
    }
}
