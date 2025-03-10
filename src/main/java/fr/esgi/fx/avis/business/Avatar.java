package fr.esgi.fx.avis.business;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avatar {
    private Long id;

    private String nom;

    private Joueur joueur;
}
