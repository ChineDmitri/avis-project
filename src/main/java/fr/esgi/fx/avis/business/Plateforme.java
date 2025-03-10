package fr.esgi.fx.avis.business;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Plateforme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @ManyToMany(mappedBy = "plateformes")
    private List<Jeu> jeux;

    private LocalDate dateDeSortie;
}
