package gra;

import java.util.List;
import java.util.Random;

public class Handlarz extends NPC {
    public int pieniadze;
    List<Przedmiot> oferta;

    public Handlarz(String imie) {
        super(imie);
        Random random = new Random();
        int bonus =  random.nextInt(100);
        pieniadze = 300 + bonus;
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        return null;
    }

    public void przedstawOferte(){
        System.out.println("Moja oferta dla ciebie byczq: " + this.oferta);
    }

    public void resetujOferte(){

    }

    void sprzedajGraczowi(Gracz g){
        //update ekwipunek gracza
    }

}
