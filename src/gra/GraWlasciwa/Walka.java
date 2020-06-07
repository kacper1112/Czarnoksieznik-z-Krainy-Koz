package gra.GraWlasciwa;

import gra.NPC.Boss;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Walka {

    static Random random = new Random();

    static List<String > komentarze(){
        return new ArrayList<>();
    }

    private static int whoBegin(){
        return random.nextInt(2);
    }

    static boolean walkaZWrogiem(Gracz gracz, Wrog wrog){
        if(whoBegin() == 0){
            while (gracz.getObecnePunktyZycia() > 0 && wrog.getObecnePunktyZycia()>0){
                gracz.otrzymajObrazenia(wrog.zadajObrazenia());
                wrog.otrzymajObrazenia(gracz.zadajObrazenia());
            }
        }else {
            while (gracz.getObecnePunktyZycia() > 0 && wrog.getObecnePunktyZycia()>0){
                wrog.otrzymajObrazenia(gracz.zadajObrazenia());
                gracz.otrzymajObrazenia(wrog.zadajObrazenia());
            }
        }

        if(gracz.getObecnePunktyZycia() > 0) {
            gracz.getEkwipunek().dodajEkwipunek(wrog.getEkwipunek());
        } else {
            System.out.println("Zostales zabity");
        }

        return gracz.getObecnePunktyZycia() > 0;
    }

    static boolean walkaZBossem(Gracz gracz, Boss boss){
        if(whoBegin() == 1){
            while (gracz.getObecnePunktyZycia() > 0 && boss.getObecnePunktyZycia()>0){
                gracz.otrzymajObrazenia(boss.zadajObrazenia());
                boss.otrzymajObrazenia(gracz.zadajObrazenia());
            }
        }else {
            while (gracz.getObecnePunktyZycia() > 0 && boss.getObecnePunktyZycia()>0){
                boss.otrzymajObrazenia(gracz.zadajObrazenia());
                gracz.otrzymajObrazenia(boss.zadajObrazenia());
            }
        }

        if(gracz.getObecnePunktyZycia() > 0) {
            gracz.getEkwipunek().dodajEkwipunek(boss.getEkwipunek());
        } else {
            System.out.println("Zostales zabity");
        }

        return gracz.getObecnePunktyZycia() > 0;
    }
}
