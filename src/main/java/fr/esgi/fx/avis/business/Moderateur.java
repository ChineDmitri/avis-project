package fr.esgi.fx.avis.business;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Moderateur extends Utilisateur {

    private String numeroDeTelephone;

    public Moderateur(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
        super(pseudo, motDePasse, mail);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}
