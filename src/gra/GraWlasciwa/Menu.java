package gra.GraWlasciwa;

import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.BronMagiczna;

import java.util.List;
import java.util.Scanner;

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

    static public boolean menuGlowne(){
        System.out.println("0. Pokaż Moje Statystyki\n" +
                "1. Pokaż ekwipunek\n" + "2. Zmień broń\n" + "3. Użyj pożywienia\n" + "4. Użyj przedmiotu fabularnego\n"
                + "5. Pokaż listę dostępnych lokalizacji\n" + "6. Przejdź do innej lokalizacji\n");
        int wybor = in.nextInt();
        int jakIndex;
        switch (wybor){
            case 0:
                gracz.wlozBronMagicznaDoEwkipunku(new BronMagiczna("jakas bron", "super bronka to jest", 10, 10, 10, 10));
                System.out.println(gracz);
                return true;
            case 1:
                gracz.getEkwipunek().wyswietlEkwipunek();
                return true;
            case 2:
                System.out.println("Wybierz bron na ktora chcesz zmienic - podaj typ(fizyczna lub magiczna): ");
                String jakaBron0 = in.nextLine();
                String jakaBron1 = in.nextLine();
                String jakaBron2 = jakaBron0 + jakaBron1;
                jakaBron2.trim();
                System.out.println("->"+jakaBron2 + "<-");
                System.out.println("Wybierz bron na ktora chcesz zmienic - podaj indeks: ");
                jakIndex = in.nextInt();
                if(jakaBron2.equals("Fizyczna") || jakaBron2.equals("fizyczna") ){
                    gracz.getEkwipunek().zmienWyekwipowanaBronNaFiczyna(jakIndex);
                }else if(jakaBron2.equals("Magiczna") || jakaBron2.equals("magiczna")){
                    gracz.getEkwipunek().zmienWyekwipowanaBronNaMagiczna(jakIndex);
                }else {
                    System.out.println("Zły typ broni");
                }
                return true;
            case 3:
                System.out.println("Wybierz pozywienie ktorego chcesz uzyc - podaj indeks: ");
                jakIndex = in.nextInt();
                gracz.zwiekszPunktyZycia(gracz.getEkwipunek().getEkwipunekPozywienie().get(jakIndex).getPrzywracaneZycie());
                return true;
            case 4:
                System.out.println("Wybierz przedmiot fabularny ktorego chcesz uzyc - podaj indeks: ");
                jakIndex = in.nextInt();
                System.out.println(gracz.getEkwipunek().getEkwipunekFabularne().get(jakIndex).getWskazowka(
                        gracz.getInteligencja()
                ));
                return true;
            case 5:
                System.out.println("Sasiednie lokacje:");
                lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getSasiednieLokacje().forEach(index -> {
                    System.out.println(index + ": " + lokacje.get(index).getNazwa());
                    //System.out.println(this.lokacje.get(index).getNazwa());
                    //System.out.println(this.lokacje.get(index).getOpis());
                });
                return true;
            case 6:
                System.out.println("Wybierz lokalizacje: ");
                int wyborLokacji = in.nextInt();
                if(lokacje.get(Gra.getInstance().getLokalizacjaGracza()).getSasiednieLokacje().contains(wyborLokacji)) {
                    Gra.getInstance().setLokalizacjaGracza(wyborLokacji);
                    return false;
                } else {
                    System.out.println("Niepoprawna lokacja");
                    return true;
                }
            default:
                System.out.println("Cos poszlo nie tak, sprobuj jeszcze raz");
                return true;
        }
    }
}
