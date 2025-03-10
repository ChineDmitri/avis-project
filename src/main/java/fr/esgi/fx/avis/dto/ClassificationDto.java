package fr.esgi.fx.avis.dto;

import fr.esgi.fx.avis.business.Classification;
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