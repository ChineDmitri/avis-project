package fr.esgi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//@SuperBuilder
public class Genre {

    private Long id;

    private String nom;

    @ToString.Exclude
    private List<Jeu> jeux;

}
