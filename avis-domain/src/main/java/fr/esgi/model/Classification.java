package fr.esgi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Classification {

    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String couleurRGB;

    private List<Jeu> jeux;

}
