package gra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Handlarz extends NPC {
    public int pieniadze;
    List<Przedmiot> oferta;

    public Handlarz(String imie) {
        super(imie);
        Random random = new Random();
        int bonus =  random.nextInt(100);
        pieniadze = 300 + bonus;
        oferta = new ArrayList<>();
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunek = new Ekwipunek();
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
        ekwipunek.wlozBron(new BronFizyczna(
                bronFizycznaTMP.get(pairIndex).getPierwszy(),
                bronFizycznaTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100,
                rand.nextDouble()*100
        ));

        pairIndex = rand.nextInt(bronFizycznaTMP.size());
        ekwipunek.wlozBron( new BronMagiczna(
                bronMagicznaTMP.get(pairIndex).getPierwszy(),
                bronMagicznaTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100,
                rand.nextDouble()*100
        ));

        pairIndex = rand.nextInt(pozywienieTMP.size());
        ekwipunek.wlozPozywienie(new PrzedmiotPozywienie(
                pozywienieTMP.get(pairIndex).getPierwszy(),
                pozywienieTMP.get(pairIndex).getDrugi(),
                rand.nextDouble()*100,
                rand.nextInt(100),
                rand.nextDouble()*100
        ));

        ekwipunek.getEkwipunekBron().forEach(x->{
            oferta.add((Przedmiot) x);
        });

        ekwipunek.getEkwipunekPozywienie().forEach(x->{
            oferta.add((Przedmiot) x);
        });

        return ekwipunek;
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
        } else if(oferta.get(indeksOferty) instanceof PrzedmiotPozywienie){
            gracz.getEkwipunek().wlozPozywienie((PrzedmiotPozywienie) oferta.get(indeksOferty));
            oferta.stream()
                    .filter(x-> !(x.getNazwa().equals(oferta.get(indeksOferty).getNazwa())))
                    .collect(Collectors.toList());
        } else if(oferta.get(indeksOferty) instanceof  BronFizyczna || oferta.get(indeksOferty) instanceof  BronMagiczna){
            oferta.stream()
                    .filter(x-> !(x.getNazwa().equals(oferta.get(indeksOferty).getNazwa())))
                    .collect(Collectors.toList());
        }
    }
}
