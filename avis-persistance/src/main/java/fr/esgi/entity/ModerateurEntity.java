package fr.esgi.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModerateurEntity extends UtilisateurEntity {

    private String numeroDeTelephone;

//    public ModerateurEntity(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
//        super(pseudo, motDePasse, mail);
//        this.numeroDeTelephone = numeroDeTelephone;
//    }
}
