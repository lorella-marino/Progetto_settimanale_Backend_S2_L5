package it.epicode.progettosettimanale.elementi;

import lombok.Data;

@Data
public class Libro extends ElementoBiblioteca {
    private String autore;
    private String genere;

    public Libro(int isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + getIsbn() +
                ", titolo='" + getTitolo() + '\'' +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                '}';
    }


}
