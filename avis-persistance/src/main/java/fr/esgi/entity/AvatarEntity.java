package fr.esgi.entity;

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
public class AvatarEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nom;

    @OneToOne(mappedBy="avatar", fetch= FetchType.LAZY)
    @Fetch(FetchMode.JOIN)

    // Non applicable pour un objet unique
    // @Fetch(FetchMode.SUBSELECT)
    private JoueurEntity joueur;

}
