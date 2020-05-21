package gra;

public interface bron {
    double zadajObrazenia();

    // dla bronii fizycznej mocniejszy atak, w przypadku niepowodzenia 0 obrazenia
    // dla bronii magicznej mozliwosc podwojnego ataku, w przypadku niepowodzenia
    // leczy przeciwnika zamiast ranic
    double zadajObrazeniaSpecjalne();
}
