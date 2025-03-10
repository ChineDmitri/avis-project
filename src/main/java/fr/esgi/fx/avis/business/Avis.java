package fr.esgi.fx.avis.business;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avis {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NonNull
    private String description;

    @ManyToOne
    @NonNull
    private Jeu jeu;

    @ManyToOne
    @NonNull
    private Joueur joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;

    @ManyToOne
    private Moderateur moderateur;
}
