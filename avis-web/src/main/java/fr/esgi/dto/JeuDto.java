package fr.esgi.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Jeu}
 */
@Value
public class JeuDto implements Serializable {
    Long id;
    String nom;
    EditeurDto editeur;
    GenreDto genre;
    ClassificationDto classification;
    String description;
    LocalDate dateSortie;
    List<PlateformeDto> plateformes;

}
