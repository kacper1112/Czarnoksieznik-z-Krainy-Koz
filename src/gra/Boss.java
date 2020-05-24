package gra;

import java.util.Random;

public class Boss extends Wrog{
    private double szansaNaTrafienieKrytyczne;

    public Boss(String imie, PrzedmiotFabularny przedmiotFabularny) {
        super(imie);
        Random random = new Random();
        szansaNaTrafienieKrytyczne = random.nextInt(50)+10;
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
    }

    public void ulepszEkwipunekBossa(PrzedmiotFabularny przedmiotFabularny){
        this.getEkwipunek().wlozFabularne(przedmiotFabularny);
        //MOZE JAKIS BRONI I POT
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
}
