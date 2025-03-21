package it.epicode.progettosettimanale.elementi;

import it.epicode.progettosettimanale.exception_enum.Periodicita;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    public static List<Libro> listaLibri = new ArrayList<>(List.of(
            new Libro(12345, "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy"),
            new Libro(67899, "1984", 1949, 328, "George Orwell", "Distopia"),
            new Libro(87654, "Il Nome della Rosa", 1980, 504, "Umberto Eco", "Romanzo"),
            new Libro(32122, "Il Codice da Vinci", 2003, 689, "Dan Brown", "Thriller"),
            new Libro(82648, "Harry Potter e la Pietra Filosofale", 1997, 309, "J.K. Rowling", "Fantasy")
    ));

    public static List<Rivista> listaRiviste = new ArrayList<>(List.of(
            new Rivista(98765, "National Geographic", 1888, 150, Periodicita.SEMESTRALE),
            new Rivista(54321, "Time", 1923, 100, Periodicita.SETTIMANALE),
            new Rivista(13579, "Vanity Fair", 1924, 80, Periodicita.MENSILE),
            new Rivista(24680, "Forbes", 1939, 120, Periodicita.SETTIMANALE),
            new Rivista(97531, "Scientific American", 1845, 200, Periodicita.MENSILE)
    ));
}
