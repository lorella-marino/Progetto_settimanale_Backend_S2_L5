package it.epicode.progettosettimanale;

import it.epicode.progettosettimanale.elementi.Catalogo;
import it.epicode.progettosettimanale.elementi.ElementoBiblioteca;
import it.epicode.progettosettimanale.elementi.Libro;
import it.epicode.progettosettimanale.elementi.Rivista;
import it.epicode.progettosettimanale.exception_enum.Periodicita;
import it.epicode.progettosettimanale.exception_enum.RicercaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class Archivio {
    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);

    // aggiungere un elemento, con isbn inesistente
    public static void aggiungiElemento(ElementoBiblioteca elemento) {
        if (Catalogo.listaLibri.stream().anyMatch(l -> l.getIsbn() == elemento.getIsbn())
                || Catalogo.listaRiviste.stream().anyMatch(r -> r.getIsbn() == elemento.getIsbn())) {
            logger.error("❌ L'elemento con isbn {} è già nella lista.", elemento.getIsbn());
        } else {
            if (elemento instanceof Libro) {
                Catalogo.listaLibri.add((Libro) elemento);
                logger.info("✅ Libro aggiunto correttamente.");
            } else if (elemento instanceof Rivista) {
                Catalogo.listaRiviste.add((Rivista) elemento);
                logger.info("✅ Rivista aggiunta correttamente.");
            }
        }
    }


    // ricerca di un elemento tramite isbn, con eccezione
    public ElementoBiblioteca cercaElementoPerIsbn(int isbn) throws RicercaException {
        ElementoBiblioteca elemento = Catalogo.listaLibri.stream().filter(l -> l.getIsbn() == isbn).findFirst().orElse(null);
        if (elemento == null) {
            elemento = Catalogo.listaRiviste.stream().filter(r -> r.getIsbn() == isbn).findFirst().orElse(null);
        }
        if (elemento == null) {
            throw new RicercaException("❌ L'elemento con isbn " + isbn + " non è presente nella lista.");
        }
        return elemento;
    }


    // rimuovi un elemento tramite isbn
    public void rimuoviElemento(int isbn) {
        boolean rimuovi = Catalogo.listaLibri.removeIf(l -> l.getIsbn() == isbn)
                || Catalogo.listaRiviste.removeIf(r -> r.getIsbn() == isbn);
        if (!rimuovi) {
            logger.error("❌ Nessun elemento trovato con ISBN {}.", isbn);
        } else {
            logger.info("✅ Elemento rimosso correttamente.");
        }
    }


    // ricerca di un elemento per anno di pubblicazione
    public void cercaElementoPerAnno(int annoPubblicazione) {
        boolean esistente = Catalogo.listaLibri.stream().filter(l -> l.getAnnoPubblicazione() == annoPubblicazione).peek(System.out::println).count() > 0;
        if (Catalogo.listaRiviste.stream().filter(r -> r.getAnnoPubblicazione() == annoPubblicazione).peek(System.out::println).count() > 0) {
            esistente = true;
        }
        if (!esistente) {
            logger.error("❌ Nessun elemento esistente per l'anno {}.", annoPubblicazione);
        }
    }


    // ricerca dei libri per autore
    public void cercaElementoPerAutore(String autore) {
        boolean esistente = Catalogo.listaLibri.stream()
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .peek(System.out::println)
                .count() > 0;
        if (!esistente) {
            logger.error("❌ Nessun libro trovato per l'autore {}.", autore);
        }
    }



    // aggiornamento di un elemento esistente, tramite isbn
    public void aggiornaElemento(ElementoBiblioteca elemento) {
        Catalogo.listaLibri.stream().filter(l -> l.getIsbn() == elemento.getIsbn()).forEach(l -> {
            l.setTitolo(elemento.getTitolo());
            l.setAnnoPubblicazione(elemento.getAnnoPubblicazione());
            l.setNumeroPagine(elemento.getNumeroPagine());
            if (elemento instanceof Libro) {
                l.setAutore(((Libro) elemento).getAutore());
                l.setGenere(((Libro) elemento).getGenere());
            }
        });

        Catalogo.listaRiviste.stream().filter(r -> r.getIsbn() == elemento.getIsbn()).forEach(r -> {
            r.setTitolo(elemento.getTitolo());
            r.setAnnoPubblicazione(elemento.getAnnoPubblicazione());
            r.setNumeroPagine(elemento.getNumeroPagine());
            if (elemento instanceof Rivista) {
                r.setPeriodicita(Periodicita.valueOf(((Rivista) elemento).getPeriodicita().toString()));
            }
        });
    }



    // STATISTICHE

    // stampa numero totale di libri e riviste
    public void numeroTotale() {
        System.out.println("Numero di libri: " + Catalogo.listaLibri.size());
        System.out.println("Numero di riviste: " + Catalogo.listaRiviste.size());
    }


    // stampa elemento con maggior numero di pagine
    public void elementoConPiuPagine() {
        ElementoBiblioteca libroPiuPagine = Catalogo.listaLibri.stream().max(Comparator.comparingInt(ElementoBiblioteca::getNumeroPagine)).orElse(null);
        ElementoBiblioteca rivistaPiuPagine = Catalogo.listaRiviste.stream().max(Comparator.comparingInt(ElementoBiblioteca::getNumeroPagine)).orElse(null);
        if (libroPiuPagine != null && rivistaPiuPagine != null) {
            if (libroPiuPagine.getNumeroPagine() > rivistaPiuPagine.getNumeroPagine()) {
                System.out.println("Elemento con più pagine: " + libroPiuPagine);
            } else {
                System.out.println("Elemento con più pagine: " + rivistaPiuPagine);
            }
        } else {
            logger.error("❌ Nessun elemento trovato.");
        }
    }


    // stampa media delle pagine di tutti gli elementi
    public void mediaPagine() {
        double mediaLibri = Catalogo.listaLibri.stream().mapToInt(ElementoBiblioteca::getNumeroPagine).average().orElse(0);
        double mediaRiviste = Catalogo.listaRiviste.stream().mapToInt(ElementoBiblioteca::getNumeroPagine).average().orElse(0);
        double mediaTotale = (mediaLibri + mediaRiviste) / 2;
        System.out.println("Media totale delle pagine: " + mediaTotale);
    }

}
