package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fabularny extends NPC {
    private boolean czyPosiadaPrzedmiotFabularny;
    private final Przedmiot przedmiotFabularny;
    private List<? super Przedmiot> podarki;

    public Fabularny(String imie) {
        super(imie);
        //podarki = new ArrayList<>();
        przedmiotFabularny = null;
        czyPosiadaPrzedmiotFabularny = false;
    }

    public Fabularny(String imie, boolean czyPosiadaPrzedmiotFabularny, Przedmiot przedmiotFabularny) {
        super(imie);
        //podarki = new ArrayList<>();
        this.przedmiotFabularny = przedmiotFabularny;
        this.czyPosiadaPrzedmiotFabularny = true;
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        podarki = new ArrayList<>();
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.FABULARNY);

        List<Para<String, String>> pozywienieTMP = List.of(
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
        if(10<rand.nextInt(100)){
            int indeks = rand.nextInt(bronFizycznaTMP.size() + bronMagicznaTMP.size());
            System.out.println("indeks ->" + indeks);
            if(indeks < bronFizycznaTMP.size()){
                ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                        bronFizycznaTMP.get(indeks).getPierwszy(),
                        bronFizycznaTMP.get(indeks).getDrugi(),
                        rand.nextDouble()*100,
                        rand.nextInt(100),
                        rand.nextDouble()*100,
                        rand.nextDouble()*100
                ));
                if(ekwipunekTMP.getEkwipunekBronFizyczna().size()>0)
                ekwipunekTMP.getEkwipunekBronFizyczna().forEach(x->podarki.add((Przedmiot) x));
            }else{
                indeks-=bronFizycznaTMP.size();
                ekwipunekTMP.wlozBronMagiczna( new BronMagiczna(
                        bronMagicznaTMP.get(indeks).getPierwszy(),
                        bronMagicznaTMP.get(indeks).getDrugi(),
                        rand.nextDouble()*100,
                        rand.nextInt(100),
                        rand.nextDouble()*100,
                        rand.nextDouble()*100
                ));

                if(ekwipunekTMP.getEkwipunekBronMagiczna().size()>0)
                ekwipunekTMP.getEkwipunekBronMagiczna().forEach(x->podarki.add((Przedmiot) x));
            }
        }else{
            int indeks = rand.nextInt(pozywienieTMP.size());
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    pozywienieTMP.get(indeks).getPierwszy(),
                    pozywienieTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100
            ));
            if(ekwipunekTMP.getEkwipunekPozywienie().size()>0)
            ekwipunekTMP.getEkwipunekPozywienie().forEach(x->podarki.add((Przedmiot) x));
        }

        return ekwipunekTMP;
    }

    public Przedmiot podarujLosowyPrzedmiotNieFabularny(){
        Random random = new Random();
        int indeks = random.nextInt(podarki.size());
        return (Przedmiot) podarki.get(indeks);
    }

    public PrzedmiotFabularny podarujPrzedmiotFabularny(){
        return (PrzedmiotFabularny) this.getEkwipunek().getEkwipunekFabularne().get(0);
    }

    public void wlozPrzedmiotFabularny(PrzedmiotFabularny przedmiotFabularny){
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    public boolean isCzyPosiadaPrzedmiotFabularny() {
        return czyPosiadaPrzedmiotFabularny;
    }

    public void setCzyPosiadaPrzedmiotFabularny(boolean czyPosiadaPrzedmiotFabularny) {
        this.czyPosiadaPrzedmiotFabularny = czyPosiadaPrzedmiotFabularny;
    }
}
