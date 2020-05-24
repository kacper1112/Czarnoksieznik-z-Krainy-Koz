package gra;

public class Lokacja {
    private final String nazwa;
    private final String[] opisy;
    private final int[] sasiednieLokacje;
    private final Wydarzenie wydarzenieFabularne;
    private final Wydarzenie[] wydarzeniaPoboczne;

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
