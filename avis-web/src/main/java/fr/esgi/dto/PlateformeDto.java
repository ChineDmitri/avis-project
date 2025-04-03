package fr.esgi.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Plateforme}
 */
@Value
public class PlateformeDto implements Serializable {
    String nom;
    String dateDeSortie;
    List<JeuDto> jeux;
}
