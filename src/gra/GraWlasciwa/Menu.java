package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Para;
import gra.NPC.Handlarz;
import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.Przedmiot;

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
        Gra.wyczyscTerminal();
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
                return true;
            }
        } else if(wyborLokacji == 12) {
            if(gracz.getEkwipunek().getEkwipunekFabularne().stream().filter(e -> e.getNazwa().equals("Przepustka 2")).collect(Collectors.toList()).size() != 0) {
                System.out.println("Masz przepustke");
            } else {
                System.out.println("Nie masz przepustki");
                return true;
            }
        } else if(wyborLokacji == 13) {
            if(gracz.getEkwipunek().getEkwipunekFabularne().stream().filter(e -> e.getNazwa().equals("Przepustka 3")).collect(Collectors.toList()).size() != 0) {
                System.out.println("Masz przepustke");
            } else {
                System.out.println("Nie masz przepustki");
                return true;
            }
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

        Gra.wyczyscTerminal();

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
        Gra.wyczyscTerminal();
        System.out.println("Wybierz co chcesz zrobic: ");
        System.out.println("1. Kupic od Handlarza");
        System.out.println("2. Sprzedac dla Handlarza");
        int wyborCzynnosci = in.nextInt();
        Handlarz handlarzTMP = lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getHandlarz();
        if(wyborCzynnosci==1) {
            System.out.println("Twoje zlote monety: "+ gracz.getPieniadze());
            handlarzTMP.przedstawOferte();
            System.out.println("Jesli chcesz kupic przedmiot wybierz wybierz jego numer z oferty Handlarza(0 zeby wyjsc do poprzedniego menu):");
            int wyborPrzedmiotu = in.nextInt();
            if(wyborPrzedmiotu==0) return;
            while (true) {
                if(wyborPrzedmiotu > 0 && wyborPrzedmiotu <= handlarzTMP.getOferta().size()){
                    handlarzTMP.sprzedajGraczowi(gracz,wyborPrzedmiotu-1);
                    break;
                }else {
                    System.out.println("Niepoprawny wybor przedmiotu podaj, dane handlu jeszcze raz" +
                            "(0 zeby wyjsc do poprzedniego menu): ");
                    System.out.print("Wybieram Handlarza nr: ");
                    wyborPrzedmiotu = in.nextInt();
                    if(wyborPrzedmiotu==0) return;
                }
            }
        } else if(wyborCzynnosci==2) {
            Przedmiot p = getItemZEkwiupnuku();
            System.out.println("Twoje zlote monety: "+ gracz.getPieniadze());
            handlarzTMP.kupOdGracza(gracz,p);
            System.out.println("Twoje zlote monety po sprzedazy: "+ gracz.getPieniadze());
        } else{
            System.out.println("Niepoprawny wybor czynnosci");
        }
    }

    public static boolean menuGlowne(){
        Gra.wyczyscTerminal();
        System.out.println("1. Pokaż Moje Statystyki\n" +
                "2. Pokaż ekwipunek\n" + "3. Przejdź do innej lokalizacji");

        /*
        if((lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzeniaPoboczne()!=null &&
                        lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getWydarzeniaPoboczne().stream()
                        .anyMatch(x-> x.getHandlarz()!=null)))
        */
        if(lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getHandlarz()!=null){
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

    //METODY POMOCNICZE
    public static Przedmiot getItemZEkwiupnuku(){
        int rozmiarEq;

        if(gracz.getEkwipunek().isEmpty()) {
            System.out.println("Nie masz w ekwipunku wiecej przedmiotow!");
        }else{
            rozmiarEq = gracz.getEkwipunek().pokazEkwipunek();
            System.out.println("Wybierz przedmiot który chcesz sprzedac");
            int wyborGracza = Gra.wczytajWyborGracza(rozmiarEq, true);

            // wybor spoza ekwipunku albo chec powrotu
            if(wyborGracza <= 0 || wyborGracza > rozmiarEq) {
                System.out.println("wybor spoza ekwipunku");
                System.out.println("Cos poszlo nie tak podczas wyboru przedmiotu");
            } else {

                rozmiarEq -= gracz.getEkwipunek().getIloscFabularne();
                if(wyborGracza > rozmiarEq) {
                    return gracz.getEkwipunek().getEkwipunekFabularne().get(wyborGracza - rozmiarEq - 1);
                }


                rozmiarEq -= gracz.getEkwipunek().getIloscBronMagiczna();
                if(wyborGracza > rozmiarEq) {
                    return gracz.getEkwipunek().getEkwipunekBronMagiczna().get(wyborGracza - rozmiarEq - 1);
                }

                rozmiarEq -= gracz.getEkwipunek().getIloscBronFizyczna();
                if(wyborGracza > rozmiarEq) {
                    return gracz.getEkwipunek().getEkwipunekBronFizyczna().get(wyborGracza - rozmiarEq - 1);
                }

                rozmiarEq -= gracz.getEkwipunek().getIloscPozywienie();
                if(wyborGracza > rozmiarEq) {
                    return gracz.getEkwipunek().getEkwipunekPozywienie().get(wyborGracza - rozmiarEq - 1);
                }
            }
        }
        return null;
    }
}
