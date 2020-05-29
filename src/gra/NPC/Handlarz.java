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
import java.util.stream.Collectors;

public class Handlarz extends NPC {
    private int pieniadze;
    private List<Para<? extends Przedmiot, Integer>> oferta;

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

        ekwipunekTMP.getEkwipunekBronFizyczna().forEach(x-> oferta.add(new Para<>((Przedmiot) x, 214)));
        ekwipunekTMP.getEkwipunekBronMagiczna().forEach(x-> oferta.add(new Para<>((Przedmiot) x, 213)));
        ekwipunekTMP.getEkwipunekPozywienie().forEach(x-> oferta.add(new Para<>((Przedmiot) x, 50)));

        return ekwipunekTMP;
    }

    public void przedstawOferte(){
        if (oferta.size()<1){
            System.out.println("Brak przedmiotow do kupienia.");
        }else {
            System.out.println("Moja oferta dla ciebie byczq: \n" + this.oferta);
        }
    }

    public void resetujOferte(){
        setEkwipunek(generujEkwipunek());
    }

    void sprzedajGraczowi(Gracz gracz, int indeksOferty){
        if (oferta.size()<1){
            System.out.println("Brak przedmiotow do kupienia.");
        } else if(oferta.get(indeksOferty).getPierwszy() instanceof PrzedmiotPozywienie){
            gracz.getEkwipunek().wlozPozywienie((PrzedmiotPozywienie) oferta.get(indeksOferty).getPierwszy());
            gracz.setPieniadze(gracz.getPieniadze() - oferta.get(indeksOferty).getDrugi());
            this.setPieniadze(this.getPieniadze() + oferta.get(indeksOferty).getDrugi());
            oferta.stream()
                    .filter(x-> !(x.getPierwszy().getNazwa().equals(oferta.get(indeksOferty).getPierwszy().getNazwa())))
                    .collect(Collectors.toList());

        } else if(oferta.get(indeksOferty).getPierwszy() instanceof  BronFizyczna ){
            gracz.getEkwipunek().wlozBronFizyczna((BronFizyczna) oferta.get(indeksOferty).getPierwszy());
            gracz.setPieniadze(gracz.getPieniadze() - oferta.get(indeksOferty).getDrugi());
            this.setPieniadze(this.getPieniadze() + oferta.get(indeksOferty).getDrugi());
            oferta.stream()
                    .filter(x-> !(x.getPierwszy().getNazwa().equals(oferta.get(indeksOferty).getPierwszy().getNazwa())))
                    .collect(Collectors.toList());
        }else if( oferta.get(indeksOferty).getPierwszy() instanceof  BronMagiczna){
            gracz.getEkwipunek().wlozBronMagiczna((BronMagiczna) oferta.get(indeksOferty).getPierwszy());
            gracz.setPieniadze(gracz.getPieniadze() - oferta.get(indeksOferty).getDrugi());
            this.setPieniadze(this.getPieniadze() + oferta.get(indeksOferty).getDrugi());
            oferta.stream()
                    .filter(x-> !(x.getPierwszy().getNazwa().equals(oferta.get(indeksOferty).getPierwszy().getNazwa())))
                    .collect(Collectors.toList());
        }
        System.out.println("Właśnie kupiłeś: " + oferta.get(indeksOferty).getPierwszy());
    }

    public int getPieniadze() {
        return pieniadze;
    }

    public void setPieniadze(int pieniadze) {
        this.pieniadze = pieniadze;
    }

    public List<Para<? extends Przedmiot, Integer>> getOferta() {
        return oferta;
    }

    public void setOferta(List<Para<? extends Przedmiot,Integer> > oferta) {
        this.oferta = oferta;
    }

}
