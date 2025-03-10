package fr.esgi.fx.avis.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="genre")
    private List<Jeu> jeux;

}
