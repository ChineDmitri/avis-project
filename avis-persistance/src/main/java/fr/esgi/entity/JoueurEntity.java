package fr.esgi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class JoueurEntity extends UtilisateurEntity {

    private LocalDate dateDeNaissance;

    // Hypothèse de Nicolas : il ne sait pas comment initialiser la liste d'avis
    @Builder.Default
    @OneToMany(mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<AvisEntity> avis = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private AvatarEntity avatar;
}
