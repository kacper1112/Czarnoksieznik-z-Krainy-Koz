package gra.ElementyPomocnicze;

import java.util.Objects;

public class Para<A, B> {
    private A pierwszy;
    private B drugi;

    public Para(A pierwszy, B second) {
        this.pierwszy = pierwszy;
        this.drugi = second;
    }

    public A getPierwszy() {
        return pierwszy;
    }

    public void setPierwszy(A pierwszy) {
        this.pierwszy = pierwszy;
    }

    public B getDrugi() {
        return drugi;
    }

    public void setDrugi(B second) {
        this.drugi = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Para<?, ?> para = (Para<?, ?>) o;
        return Objects.equals(pierwszy, para.pierwszy) &&
                Objects.equals(drugi, para.drugi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pierwszy, drugi);
    }

    @Override
    public String toString() {
        return "[" + pierwszy + ", " + drugi + ']';
    }
}
