package fr.esgi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    private Long id;

    private String nom;

    private Joueur joueur;
}
