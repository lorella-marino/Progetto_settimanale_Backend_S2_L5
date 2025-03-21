package it.epicode.progettosettimanale.elementi;

import lombok.Data;

@Data
public class ElementoBiblioteca {
    private int isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public ElementoBiblioteca(int isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String toString() {
        return "ElementoBiblioteca{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }

}
