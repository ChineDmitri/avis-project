package fr.esgi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class PlateformeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @ManyToMany(mappedBy = "plateformes")
    private List<JeuEntity> jeux;

    private LocalDate dateDeSortie;
}
