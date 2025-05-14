package fr.esgi.mapper;

import fr.esgi.entity.AvisEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.entity.JoueurEntity;
import fr.esgi.entity.ModerateurEntity;
import fr.esgi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AvisMapperTest {

    private AvisMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(AvisMapper.class);

        try {
            final Field joueurMapperField = mapper.getClass().getDeclaredField("joueurMapper");
            joueurMapperField.setAccessible(true);
            joueurMapperField.set(mapper, Mappers.getMapper(JoueurMapper.class));
        } catch (final Exception e){
            throw new RuntimeException("Error while initializing AvisMapper", e);
        }
    }

    @Test
    void testEntityToDomain() {
        final JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(1L);
        jeuEntity.setNom("Joueur");
        final JoueurEntity joueurEntity = new JoueurEntity();
        joueurEntity.setId(1L);
        joueurEntity.setPseudo("Joueur");
        joueurEntity.setEmail("email@email.fr");
        joueurEntity.setMotDePasse("password");
        final ModerateurEntity moderateurEntity = new ModerateurEntity();
        moderateurEntity.setId(1L);
        moderateurEntity.setPseudo("Moderateur");
        moderateurEntity.setEmail("mod@email.com");
        moderateurEntity.setMotDePasse("password");

        final AvisEntity entity = new AvisEntity("Very fun", jeuEntity, joueurEntity);
        entity.setId(1L);
        entity.setNote(4.5f);
        entity.setDateDEnvoi(LocalDateTime.now());
        entity.setModerateur(moderateurEntity);
        entity.setJoueur(joueurEntity);
        entity.setJeu(jeuEntity);

        final Avis domain = mapper.entityToDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getDescription(), domain.getDescription());
    }

    @Test
    void testDomainToEntity() {
        final Jeu jeu = new Jeu();
        final Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Editeur");
        jeu.setId(1L);
        jeu.setNom("Jeu");
        jeu.setEditeur(editeur);
        final Joueur joueur = new Joueur();
        joueur.setId(1L);
        joueur.setPseudo("Joueur");
        joueur.setEmail("email@email.fr");
        joueur.setMotDePasse("password");
        final Moderateur moderateur = new Moderateur(); moderateur.setId(1L);
        moderateur.setPseudo("Moderateur");
        moderateur.setEmail("mod@email.com");
        moderateur.setMotDePasse("password");
        final Avis domain = new Avis(1L, "Addictive game", jeu, joueur, 5.0f, LocalDateTime.now(), moderateur);

        final AvisEntity entity = mapper.domainToEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getDescription(), entity.getDescription());
    }
}
