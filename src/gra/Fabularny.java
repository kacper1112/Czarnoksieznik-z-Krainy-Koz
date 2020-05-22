package gra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fabularny extends NPC {
    private boolean czyPosiadaPrzedmiotFabularny;
    private List<Przedmiot> podarki;
    public Fabularny(String imie) {
        super(imie);
        podarki = new ArrayList<>();
        czyPosiadaPrzedmiotFabularny = false;
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
        if(10<rand.nextInt(100)){
            int indeks = rand.nextInt(bronFizycznaTMP.size() + bronMagicznaTMP.size() +1);
            if(indeks < bronFizycznaTMP.size()){
                this.getEkwipunek().wlozBron(new BronFizyczna(
                        bronFizycznaTMP.get(indeks).getPierwszy(),
                        bronFizycznaTMP.get(indeks).getDrugi(),
                        rand.nextDouble()*100,
                        rand.nextInt(100),
                        rand.nextDouble()*100,
                        rand.nextDouble()*100
                ));
                podarki.add((Przedmiot) ekwipunek.getEkwipunekBron().get(0));
            }else{
                indeks-=bronFizycznaTMP.size();
                ekwipunek.wlozBron( new BronMagiczna(
                        bronMagicznaTMP.get(indeks).getPierwszy(),
                        bronMagicznaTMP.get(indeks).getDrugi(),
                        rand.nextDouble()*100,
                        rand.nextInt(100),
                        rand.nextDouble()*100,
                        rand.nextDouble()*100
                ));
                podarki.add((Przedmiot) ekwipunek.getEkwipunekBron().get(0));
            }
        }else{
            int indeks = rand.nextInt(pozywienieTMP.size());
            ekwipunek.wlozPozywienie(new PrzedmiotPozywienie(
                    pozywienieTMP.get(indeks).getPierwszy(),
                    pozywienieTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100
            ));
            podarki.add((Przedmiot) ekwipunek.getEkwipunekPozywienie().get(0));
        }
        return ekwipunek;
    }

    public Przedmiot podarujLosowyPrzedmiotNieFabularny(){
        Random random = new Random();
        int indeks = random.nextInt(podarki.size());
        return podarki.get(indeks);
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
