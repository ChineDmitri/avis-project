package fr.esgi.utils;

import fr.esgi.dto.*;
import fr.esgi.model.Classification;
import fr.esgi.model.Editeur;
import fr.esgi.model.Genre;
import fr.esgi.model.Jeu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class avisWebUtils {

    public static JeuDto createJeuDto() {
        final EditeurDto editeur = new EditeurDto(1L, "Editeur");
        final GenreDto genre = new GenreDto(1L, "Action");
        final ClassificationDto classification = new ClassificationDto(1L, "PEGI", "#FFFFF");

        final PlateformeDto plateforme1 = new PlateformeDto("PC", "12/12/2001", new ArrayList<>());
        final PlateformeDto plateforme2 = new PlateformeDto("Xbox", "11/11/2001", new ArrayList<>());
        return new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2025, 5, 13), Arrays.asList(plateforme1, plateforme2));
    }

    public static Jeu createJeu() {
        final Editeur editeur = new Editeur();
        editeur.setNom("Editeur");
        editeur.setJeux(new ArrayList<>());
        editeur.setId(1L);
        final Genre genre = new Genre();
        genre.setNom("Action");
        genre.setJeux(new ArrayList<>());
        genre.setId(1L);
        final Classification classification = new Classification();
        classification.setNom("PEGI");
        classification.setCouleurRGB("#FFFFFF");
        classification.setJeux(new ArrayList<>());
        classification.setId(1L);

        return new Jeu(1L, "Test Jeu", editeur, genre, classification, "Description", LocalDate.now(), List.of(), "image", 0f);
    }

    public static JeuDto createJeuDtoSeul(){
        return new JeuDto(1L, "Test Jeu", null, null, null, "Description", LocalDate.now(), List.of());
    }
}
