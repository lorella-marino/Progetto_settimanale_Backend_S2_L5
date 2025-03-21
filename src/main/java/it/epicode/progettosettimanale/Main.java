package it.epicode.progettosettimanale;

import it.epicode.progettosettimanale.elementi.Catalogo;
import it.epicode.progettosettimanale.elementi.ElementoBiblioteca;
import it.epicode.progettosettimanale.elementi.Libro;
import it.epicode.progettosettimanale.elementi.Rivista;
import it.epicode.progettosettimanale.exception_enum.Periodicita;
import it.epicode.progettosettimanale.exception_enum.RicercaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            System.out.println();
            System.out.println("💠 Scegli un numero 💠");
            System.out.println("1. Aggiungi un nuovo libro");
            System.out.println("2. Aggiungi una nuova rivista");
            System.out.println("3. Mostra archivio aggiornato");
            System.out.println("4. Cerca elemento per ISBN");
            System.out.println("5. Rimuovi elemento per ISBN");
            System.out.println("6. Cerca elemento per anno di pubblicazione");
            System.out.println("7. Cerca libri per autore");
            System.out.println("8. Aggiorna un elemento esistente");
            System.out.println("9. Visualizza statistiche del catalogo");
            System.out.println("10. Esci");

            System.out.println();
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci ISBN: ");
                    int isbnLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Titolo: ");
                    String titoloLibro = scanner.nextLine();
                    System.out.print("Inserisci Anno di Pubblicazione: ");
                    int annoLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Numero di Pagine: ");
                    int pagineLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Autore: ");
                    String autore = scanner.nextLine();
                    System.out.print("Inserisci Genere: ");
                    String genere = scanner.nextLine();

                    Libro nuovoLibro = new Libro(isbnLibro, titoloLibro, annoLibro, pagineLibro, autore, genere);
                    Archivio.aggiungiElemento(nuovoLibro);
                    break;

                case 2:
                    System.out.print("Inserisci ISBN: ");
                    int isbnRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Titolo: ");
                    String titoloRivista = scanner.nextLine();
                    System.out.print("Inserisci Anno di Pubblicazione: ");
                    int annoRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Numero di Pagine: ");
                    int pagineRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                    String periodicitaInput = scanner.nextLine();
                    Periodicita periodicita = Periodicita.valueOf(periodicitaInput.toUpperCase());

                    Rivista nuovaRivista = new Rivista(isbnRivista, titoloRivista, annoRivista, pagineRivista, periodicita);
                    Archivio.aggiungiElemento(nuovaRivista);
                    break;

                case 3:
                    System.out.println("Archivio:");
                    Catalogo.listaLibri.forEach(System.out::println);
                    Catalogo.listaRiviste.forEach(System.out::println);
                    break;

                case 4:
                    try {
                        System.out.print("Inserisci ISBN da cercare: ");
                        int isbn = scanner.nextInt();
                        System.out.println("Elemento trovato: " + archivio.cercaElementoPerIsbn(isbn));
                    } catch (RicercaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Inserisci ISBN da rimuovere: ");
                    int isbnDaRimuovere = scanner.nextInt();
                    archivio.rimuoviElemento(isbnDaRimuovere);
                    break;

                case 6:
                    System.out.print("Inserisci anno di pubblicazione da cercare: ");
                    int anno = scanner.nextInt();
                    archivio.cercaElementoPerAnno(anno);
                    break;

                case 7:
                    System.out.print("Inserisci autore da cercare: ");
                    String autoreDaCercare = scanner.nextLine();
                    archivio.cercaElementoPerAutore(autoreDaCercare);
                    break;

                case 8:
                    System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
                    int isbnAggiorna = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        ElementoBiblioteca elemento = archivio.cercaElementoPerIsbn(isbnAggiorna);
                        System.out.print("Inserisci nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();
                        System.out.print("Inserisci nuovo anno di pubblicazione: ");
                        int nuovoAnno = scanner.nextInt();
                        System.out.print("Inserisci nuovo numero di pagine: ");
                        int nuovePagine = scanner.nextInt();
                        scanner.nextLine();

                        if (elemento instanceof Libro) {
                            System.out.print("Inserisci nuovo autore: ");
                            String nuovoAutore = scanner.nextLine();
                            System.out.print("Inserisci nuovo genere: ");
                            String nuovoGenere = scanner.nextLine();
                            ((Libro) elemento).setAutore(nuovoAutore);
                            ((Libro) elemento).setGenere(nuovoGenere);
                        } else if (elemento instanceof Rivista) {
                            System.out.print("Inserisci nuova periodicita (SETTIMANALE, MENSILE, SEMESTRALE): ");
                            String nuovaPeriodicitaInput = scanner.nextLine();
                            Periodicita nuovaPeriodicita = Periodicita.valueOf(nuovaPeriodicitaInput.toUpperCase());
                            ((Rivista) elemento).setPeriodicita(nuovaPeriodicita);
                        }

                        elemento.setTitolo(nuovoTitolo);
                        elemento.setAnnoPubblicazione(nuovoAnno);
                        elemento.setNumeroPagine(nuovePagine);

                        archivio.aggiornaElemento(elemento);
                        System.out.println("✅ Elemento aggiornato correttamente.");
                    } catch (RicercaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    archivio.numeroTotale();
                    archivio.elementoConPiuPagine();
                    archivio.mediaPagine();
                    break;

                case 10:
                    scanner.close();
                    System.exit(0);

                default:
                    logger.error("❌ Scelta non valida. Riprova.");
            }
        }
    }
}
