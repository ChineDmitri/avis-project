package fr.esgi.dto;

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
