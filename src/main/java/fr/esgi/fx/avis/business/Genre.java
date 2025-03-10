package fr.esgi.fx.avis.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Genre {

    private Long id;

    private String nom;

    @ToString.Exclude
    @JsonIgnore
    private List<Jeu> jeux;

}
