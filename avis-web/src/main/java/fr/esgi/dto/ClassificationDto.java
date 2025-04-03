package fr.esgi.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Classification}
 */
@Value
public class ClassificationDto implements Serializable {
    Long id;
    String nom;
    String couleurRGB;
}
