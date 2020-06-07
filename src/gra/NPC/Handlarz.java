package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.Przedmiot;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Handlarz extends NPC {
    private int pieniadze;
    private List<Para<? extends Przedmiot, Para<Integer,Integer>>> oferta;

    public Handlarz(String imie) {
        super(imie);
        Random random = new Random();
        int bonus =  random.nextInt(100);
        pieniadze = 300 + bonus;
    }

    //METODY WLASCIWE
    @Override
    public Ekwipunek generujEkwipunek() {
        oferta = new ArrayList<>();
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.HANDLARZ);
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

        Random rand = new Random();

        int pairIndex = rand.nextInt(bronFizycznaTMP.size());
        ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                bronFizycznaTMP.get(pairIndex).getPierwszy(),
                bronFizycznaTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100,
                rand.nextDouble()*100
        ));

        pairIndex = rand.nextInt(bronFizycznaTMP.size());
        ekwipunekTMP.wlozBronMagiczna( new BronMagiczna(
                bronMagicznaTMP.get(pairIndex).getPierwszy(),
                bronMagicznaTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100,
                rand.nextDouble()*100
        ));

        pairIndex = rand.nextInt(pozywienieTMP.size());
        ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                pozywienieTMP.get(pairIndex).getPierwszy(),
                pozywienieTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100
        ));
        AtomicInteger licznik = new AtomicInteger(1);
        ekwipunekTMP.getEkwipunekBronFizyczna()
                .forEach(x-> oferta.add(new Para<>((Przedmiot) x, new Para<>(licznik.getAndIncrement(),(80+rand.nextInt(40)-20)))));
        ekwipunekTMP.getEkwipunekBronMagiczna()
                .forEach(x-> oferta.add(new Para<>((Przedmiot) x,new Para<>(licznik.getAndIncrement(),(80+rand.nextInt(40)-20) ))));
        ekwipunekTMP.getEkwipunekPozywienie()
                .forEach(x-> oferta.add(new Para<>((Przedmiot) x,new Para<>(licznik.getAndIncrement(),(25+rand.nextInt(40)-20) ))));

        return ekwipunekTMP;
    }

    public void przedstawOferte(){
        if (oferta.size()<1) {
            System.out.println("Brak przedmiotow do kupienia.");
            return;
        }
        System.out.println("    Oto moja oferta dla ciebie przybyszu:");

        oferta.forEach(x->{
                System.out.println("    "+x.getDrugi().getPierwszy() + ". " + x.getPierwszy().getNazwa() + " - cena: " + x.getDrugi().getDrugi());
        });
    }

    public void resetujOferte(){
        setEkwipunek(generujEkwipunek());
    }

    public void sprzedajGraczowi(Gracz gracz, int indeksOferty){
        if (oferta.size()<1){
            System.out.println("Brak przedmiotow do kupienia.");
        } else if(oferta.get(indeksOferty).getDrugi().getDrugi() > gracz.getPieniadze()){
            System.out.println("Nie masz wystarczajacej ilosci pieniedzy aby kupic ten przedmiot");
            System.out.println("Twoj stan konta wynosi: " + gracz.getPieniadze());
            return;
        } else if(oferta.get(indeksOferty).getPierwszy() instanceof PrzedmiotPozywienie){
            gracz.getEkwipunek().wlozPozywienie((PrzedmiotPozywienie) oferta.get(indeksOferty).getPierwszy());
        } else if(oferta.get(indeksOferty).getPierwszy() instanceof  BronFizyczna ){
            gracz.getEkwipunek().wlozBronFizyczna((BronFizyczna) oferta.get(indeksOferty).getPierwszy());
        }else if( oferta.get(indeksOferty).getPierwszy() instanceof  BronMagiczna){
            gracz.getEkwipunek().wlozBronMagiczna((BronMagiczna) oferta.get(indeksOferty).getPierwszy());
        }
        System.out.println("Właśnie kupiłeś: " + oferta.get(indeksOferty).getPierwszy());
        gracz.setPieniadze(gracz.getPieniadze() - oferta.get(indeksOferty).getDrugi().getDrugi());
        this.setPieniadze(this.getPieniadze() + oferta.get(indeksOferty).getDrugi().getDrugi());
        oferta.stream()
                .filter(x-> !(x.getPierwszy().getNazwa().equals(oferta.get(indeksOferty).getPierwszy().getNazwa())))
                .collect(Collectors.toList());
    }

    public void kupOdGracza(Gracz gracz, Przedmiot przedmiot){
        if(przedmiot instanceof BronMagiczna){
            int indeks = 0;
            gracz.getEkwipunek().getEkwipunekBronMagiczna()
            gracz.getEkwipunek().wyciagnijBronFizyczna();
        }else if(przedmiot instanceof BronFizyczna){

        } else if(przedmiot instanceof PrzedmiotPozywienie ){

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
