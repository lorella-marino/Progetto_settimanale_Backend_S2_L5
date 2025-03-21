package it.epicode.progettosettimanale.elementi;

import it.epicode.progettosettimanale.exception_enum.Periodicita;
import lombok.Data;

@Data
public class Rivista extends ElementoBiblioteca {
    private Periodicita periodicita;

    public Rivista(int isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "isbn=" + getIsbn() +
                ", titolo='" + getTitolo() + '\'' +
                ", periodicita=" + periodicita +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                '}';
    }
}
