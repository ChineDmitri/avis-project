package fr.esgi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Data
public class Joueur extends Utilisateur {

    private LocalDate dateDeNaissance;

    @Builder.Default
    private List<Avis> avis = new ArrayList<>();

    private Avatar avatar;
}
