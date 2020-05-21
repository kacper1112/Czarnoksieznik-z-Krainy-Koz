package gra;

public abstract class NPC {
    String imie;
    Ekwipunek ekwipunek;

    public NPC(String imie) {
        this.imie = imie;
        this.ekwipunek = generujEkwipunek();
    }

    public abstract Ekwipunek generujEkwipunek();

    public void wlozBronFizycznaDoEkwipunku(BronFizyczna bronFizyczna) {
        this.ekwipunek.wlozBron(bronFizyczna);
    }

    public void wlozBronMagicznaDoEwkipunku(BronMagiczna bronMagiczna) {
        this.ekwipunek.wlozBron(bronMagiczna);
    }

    public void wlozPozywienieDoEkwipunku(Pozywienie pozywienie) {
        this.ekwipunek.wlozPozywienie(pozywienie);
    }

    public void wlozPrzedmiotFabularnyDoEkwipunku(PrzedmiotFabularny przedmiotFabularny) {
        this.ekwipunek.wlozFabularne(przedmiotFabularny);
    }

    //WYCIAGANIE Z EQ DODAC

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Ekwipunek getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(Ekwipunek ekwipunek) {
        this.ekwipunek = ekwipunek;
    }
}
