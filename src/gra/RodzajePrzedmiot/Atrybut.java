package gra.RodzajePrzedmiot;

public class Atrybut {
    private double bonusDoSily;
    private double bonusDoInteligencji;
    private double szansaNaKrytyczne;
    private double szansaNaNatychmiastoweZabicie;

    Atrybut() {
        bonusDoSily = 0;
        bonusDoInteligencji = 0;
        szansaNaKrytyczne = 0;
        szansaNaNatychmiastoweZabicie = 0;
    }

    Atrybut(int szansa) {
        bonusDoSily = 0;
        bonusDoInteligencji = 0;
        szansaNaKrytyczne = 0;
        szansaNaNatychmiastoweZabicie = 0;

        losujAtrybuty(szansa);
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

    public double getBonusDoSily() {
        return bonusDoSily;
    }

    public double getBonusDoInteligencji() {
        return bonusDoInteligencji;
    }

    public double getSzansaNaKrytyczne() {
        return szansaNaKrytyczne;
    }

    public double getSzansaNaNatychmiastoweZabicie() {
        return szansaNaNatychmiastoweZabicie;
    }
}
