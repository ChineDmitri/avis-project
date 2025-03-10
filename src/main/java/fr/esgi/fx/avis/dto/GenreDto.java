package fr.esgi.fx.avis.dto;

import fr.esgi.fx.avis.business.Genre;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Genre}
 */
@Value
public class GenreDto implements Serializable {
    Long id;
    String nom;
}