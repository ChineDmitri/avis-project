package fr.esgi.fx.avis.business;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nom;

    @OneToOne(mappedBy="avatar", fetch= FetchType.LAZY)
   // @Fetch(FetchMode.SELECT)
   // Hibernate: select a1_0.id,a1_0.nom from avatar a1_0 where a1_0.id=?
   // Hibernate: select j1_0.id,j1_1.email,j1_1.mot_de_passe,j1_1.pseudo,a1_0.id,a1_0.nom,j1_0.date_de_naissance,a2_0.joueur_id,a2_0.id,a2_0.datedenvoi,a2_0.description,j2_0.id,c1_0.id,c1_0.couleurrgb,c1_0.nom,j2_0.date_de_sortie,j2_0.description,e1_0.id,e1_0.nom,g1_0.id,g1_0.nom,j2_0.image,j2_0.nom,m1_0.id,m1_1.email,m1_1.mot_de_passe,m1_1.pseudo,m1_0.numero_de_telephone,a2_0.note from joueur j1_0 join utilisateur j1_1 on j1_0.id=j1_1.id left join avatar a1_0 on a1_0.id=j1_0.avatar_id left join avis a2_0 on j1_0.id=a2_0.joueur_id left join jeu j2_0 on j2_0.id=a2_0.jeu_id left join classification c1_0 on c1_0.id=j2_0.classification_id left join editeur e1_0 on e1_0.id=j2_0.editeur_id left join genre g1_0 on g1_0.id=j2_0.genre_id left join (moderateur m1_0 join utilisateur m1_1 on m1_0.id=m1_1.id) on m1_0.id=a2_0.moderateur_id where j1_0.avatar_id=?
    @Fetch(FetchMode.JOIN)
    // Hibernate: select a1_0.id,j1_0.id,j1_1.email,j1_1.mot_de_passe,j1_1.pseudo,j1_0.date_de_naissance,a2_0.joueur_id,a2_0.id,a2_0.datedenvoi,a2_0.description,j2_0.id,c1_0.id,c1_0.couleurrgb,c1_0.nom,j2_0.date_de_sortie,j2_0.description,e1_0.id,e1_0.nom,g1_0.id,g1_0.nom,j2_0.image,j2_0.nom,m1_0.id,m1_1.email,m1_1.mot_de_passe,m1_1.pseudo,m1_0.numero_de_telephone,a2_0.note,a1_0.nom from avatar a1_0 left join (joueur j1_0 join utilisateur j1_1 on j1_0.id=j1_1.id) on a1_0.id=j1_0.avatar_id left join avis a2_0 on j1_0.id=a2_0.joueur_id left join jeu j2_0 on j2_0.id=a2_0.jeu_id left join classification c1_0 on c1_0.id=j2_0.classification_id left join editeur e1_0 on e1_0.id=j2_0.editeur_id left join genre g1_0 on g1_0.id=j2_0.genre_id left join (moderateur m1_0 join utilisateur m1_1 on m1_0.id=m1_1.id) on m1_0.id=a2_0.moderateur_id where a1_0.id=?

    // Non applicable pour un objet unique
    // @Fetch(FetchMode.SUBSELECT)
    private Joueur joueur;

   // private String description;
}
