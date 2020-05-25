package gra;

import java.util.Random;

public class Boss extends Wrog {
    private double szansaNaTrafienieKrytyczne;

    public Boss(String imie, PrzedmiotFabularny przedmiotFabularny) {
        super(imie);
        Random random = new Random();
        szansaNaTrafienieKrytyczne = random.nextInt(50)+10;
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    public void dodajKolejnyPrzedmiotFabularny(PrzedmiotFabularny przedmiotFabularny){
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    PrzedmiotFabularny wygranaBohatera(int indeks){
        PrzedmiotFabularny przedmiot = (PrzedmiotFabularny) this.getEkwipunek().getEkwipunekFabularne().get(indeks);
        System.out.print("Pokonales mnie, na farcie..... i tak ostatniecznie przegrasz byczq!");
        return przedmiot;
    }

    public double getSzansaNaTrafienieKrytyczne() {
        return szansaNaTrafienieKrytyczne;
    }

    public void setSzansaNaTrafienieKrytyczne(double szansaNaTrafienieKrytyczne) {
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        //System.out.println("GENERATOR DLA BOSA"); test czy korzysta z dobrego generatora -> zaliczony
        Ekwipunek ekwipunek = super.generujEkwipunek();

        return null;
    }
}
