package gra;

public class Atrybut {
    double bonusDoSily;
    double bonusDoInteligencji;
    double szansaNaKrytyczne;
    double szansaNaNatychmiastoweZabicie;

    Atrybut() {
        bonusDoSily = 0;
        bonusDoInteligencji = 0;
        szansaNaKrytyczne = 0;
        szansaNaNatychmiastoweZabicie = 0;
    }

    void losujAtrybuty(int szansa) {
        // szansa okresla liczbe losowan na wystapienie bonusu

        for(int i = 0; i < szansa; i++) {
            bonusDoSily += Math.random() < 0.1 ? 5 : 0;
            bonusDoInteligencji += Math.random() < 0.1 ? 5 : 0;
        }

        // maksymalnie 50% szans na uderzenia krytyczne
        szansaNaKrytyczne = Math.random() / 2;

        // maksymalnie 5% szans na natychmiastowe zabicie
        szansaNaNatychmiastoweZabicie = Math.random() / 20;
    }
}
