package fr.esgi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avis {

    private Long id;

    @NonNull
    private String description;

    @NonNull
    private Jeu jeu;

    @NonNull
    private Joueur joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;

    private Moderateur moderateur;
}
