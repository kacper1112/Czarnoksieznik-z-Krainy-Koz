package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.bron;

public class BronFizyczna extends Przedmiot implements bron {

    private final double obrazeniaBazowe;
    private final double mocUderzeniaKrytycznego;
    // ostrosc okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private int ostrosc;

    public BronFizyczna(String nazwa,
                        String opis,
                        int wartosc,
                        int szansaAtrybutu,
                        double obrazeniaBazowe,
                        double mocUderzeniaKrytycznego) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.obrazeniaBazowe = obrazeniaBazowe;
        this.mocUderzeniaKrytycznego = mocUderzeniaKrytycznego;
        ostrosc = 100;
    }

    @Override
    public double zadajObrazenia() {
        double obrazenia = obrazeniaBazowe * ostrosc / 90;
        boolean czyKrytyczne = (Math.random() < this.getAtrybut().getSzansaNaKrytyczne());
        ostrosc -= 1;

        if (czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            KolorTekstu.printCzerwony("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajMocneObrazenia() {
        // 40% szans na powodzenie uderzenia
        if (Math.random() < 0.4) {
            KolorTekstu.printCzerwony("Nie trafiles wroga!");
            return 0;
        }
        // mocne uderzenie jest o 30% mocniejsze od zwyklego
        return zadajObrazenia() * 1.3;
    }


    @Override
    public String toString() {
        return getNazwa() + " [obrażenia: " + String.format("%1.2f", obrazeniaBazowe) + ", ostrosc: " + ostrosc + ", moc ud. krytycznego: " +
                String.format("%1.2f", (mocUderzeniaKrytycznego + 100)) + "%]";
    }
}
