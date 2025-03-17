package fr.esgi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Moderateur extends Utilisateur {

    private String numeroDeTelephone;

//    public Moderateur(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
//        super(pseudo, motDePasse, mail);
//        this.numeroDeTelephone = numeroDeTelephone;
//    }
}
