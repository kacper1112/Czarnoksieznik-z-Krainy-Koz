package gra;

import java.util.Random;

public class Boss extends Wrog{
    private double szansaNaTrafienieKrytyczne;

    public Boss(String imie) {
        super(imie);
        Random random = new Random();
        szansaNaTrafienieKrytyczne = random.nextInt(50)+10;
        //DODANIE PRZEDMIOTU FABULANEGO i MOZE JAKIS BRONI I POT
    }

    public Boss(String imie, double szansaNaTrafienieKrytyczne) {
        super(imie);
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
    }

    public void ulepszEkwipunekBossa(PrzedmiotFabularny przedmiotFabularny){
        this.generujEkwipunek().wlozFabularne(przedmiotFabularny);
        //MOZE JAKIS BRONI I POT
    }

    PrzedmiotFabularny wygranaBohatera(int indeks){
        PrzedmiotFabularny przedmiot = (PrzedmiotFabularny) this.generujEkwipunek().getEkwipunekFabularne().get(indeks);
        System.out.print("Pokonales mnie, na farcie..... i tak ostatniecznie przegrasz byczq!");
        return przedmiot;
    }



}
