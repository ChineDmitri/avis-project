package fr.esgi.mapper;

import fr.esgi.entity.*;
import fr.esgi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class JoueurMapperTest {

    @Spy
    private JoueurMapper joueurMapper = Mappers.getMapper(JoueurMapper.class);

    private JoueurEntity joueurEntity;

    private Joueur joueur;

    private JeuEntity jeuEntity;

    private Jeu jeu;

    private static final LocalDate dateDeNaissance = LocalDate.of(1990, 1, 1);


    @BeforeEach
    void setUp() {
        final AvatarEntity avatarEntity = mock(AvatarEntity.class);
        joueurEntity = JoueurEntity.builder()
                .dateDeNaissance(dateDeNaissance)
                .avatar(avatarEntity)
                .pseudo("pseudo")
                .motDePasse("motDePasse")
                .email("email@email.fr")
                .build();
        final Avatar avatar = new Avatar();
        avatar.setId(1L);
        avatar.setNom("avatar.png");
        joueur = Joueur.builder()
                .dateDeNaissance(dateDeNaissance)
                .avatar(avatar)
                .pseudo("pseudo")
                .motDePasse("motDePasse")
                .email("email@email.fr")
                .build();

        final EditeurEntity editeurEntity = new EditeurEntity();
        editeurEntity.setId(1L);
        editeurEntity.setNom("Editeur Test");
        jeuEntity = JeuEntity.builder()
                .nom("Jeu Test")
                .description("description")
                .dateDeSortie(LocalDate.of(2023, 1, 1))
                .prix(10.0f)
                .editeur(editeurEntity)
                .build();

        final Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Editeur Test");
        jeu = Jeu.builder()
                .nom("Jeu Test")
                .description("description")
                .dateDeSortie(LocalDate.of(2023, 1, 1))
                .prix(10.0f)
                .editeur(editeur)
                .build();
    }

    @Test
    void should_map_entity_to_domain() {
        // Given
        final AvisEntity avisEntity = new AvisEntity();
        avisEntity.setId(1L);
        avisEntity.setDescription("description");
        avisEntity.setJeu(jeuEntity);
        avisEntity.setJoueur(joueurEntity);
        final List<AvisEntity> avisEntities = new ArrayList<>();
        avisEntities.add(avisEntity);

        // When
        final Joueur result = joueurMapper.entityToDomain(joueurEntity);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDateDeNaissance()).isEqualTo(dateDeNaissance);
        assertThat(result.getAvatar()).isNotNull();
        assertThat(result.getAvis()).isNotNull();
    }

    @Test
    void should_map_domain_to_entity() {
        // Given
        final Avis avis = new Avis();
        avis.setId(1L);
        avis.setDescription("description");
        avis.setJeu(jeu);
        avis.setJoueur(joueur);
        final List<Avis> avisList = new ArrayList<>();
        avisList.add(avis);

        // When
        final JoueurEntity result = joueurMapper.domainToEntity(joueur);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDateDeNaissance()).isEqualTo(dateDeNaissance);
        assertThat(result.getAvatar()).isNotNull();
        assertThat(result.getAvis()).isNotNull();

    }

    @Test
    void should_handle_null_values() {
        // Given
        final JoueurEntity joueurEntity = JoueurEntity.builder()
                .dateDeNaissance(null)
                .avatar(null)
                .avis(null)
                .pseudo("pseudo")
                .email("email@email.fr")
                .motDePasse("motDePasse")
                .build();

        // When
        final Joueur result = joueurMapper.entityToDomain(joueurEntity);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDateDeNaissance()).isNull();
        assertThat(result.getAvatar()).isNull();
        assertThat(result.getAvis()).isNull();
    }

    @Test
    void should_handle_empty_avis_list() {
        // Given
        final JoueurEntity joueurEntity = JoueurEntity.builder()
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .pseudo("pseudo")
                .avatar(mock(AvatarEntity.class))
                .avis(new ArrayList<>())
                .motDePasse("motDePasse")
                .email("email@email.fr")
                .build();

        // When
        final Joueur result = joueurMapper.entityToDomain(joueurEntity);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getAvis()).isNotNull();
        assertThat(result.getAvis()).isEmpty();
    }
}
