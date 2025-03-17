package fr.esgi.model;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Editeur {

    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    private List<Jeu> jeux;
}
