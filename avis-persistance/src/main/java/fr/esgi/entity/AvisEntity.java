package fr.esgi.entity;

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
public class AvisEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NonNull
    private String description;

    @ManyToOne
    @NonNull
    private JeuEntity jeu;

    @ManyToOne
    @NonNull
    private JoueurEntity joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;

    @ManyToOne
    private ModerateurEntity moderateur;
}
