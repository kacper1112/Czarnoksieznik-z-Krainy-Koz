package gra;

public class Lokacja {
    final String nazwa;
    String[] opisy;
    int[] sasiednieLokacje;
    Wydarzenie wydarzenieFabularne;
    Wydarzenie[] wydarzeniaPoboczne;

    public Lokacja(String nazwa) {
        this.nazwa = nazwa;
    }

    public Lokacja(String nazwa,
                   String[] opisy,
                   Wydarzenie fabularne,
                   Wydarzenie[] poboczne,
                   int[] sasiednie) {
        this.nazwa = nazwa;
        this.opisy = opisy;
        this.wydarzenieFabularne = fabularne;
        this.wydarzeniaPoboczne = poboczne;
        this.sasiednieLokacje = sasiednie;
    }
}
