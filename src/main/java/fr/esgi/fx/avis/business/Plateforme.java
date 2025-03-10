package fr.esgi.fx.avis.business;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Plateforme {

    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    private List<Jeu> jeux;

    private LocalDate dateDeSortie;
}
