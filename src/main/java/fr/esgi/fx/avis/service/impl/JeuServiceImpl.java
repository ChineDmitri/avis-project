package fr.esgi.fx.avis.service.impl;

import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.dto.JeuDto;
import fr.esgi.fx.avis.mapper.JeuMapper;
import fr.esgi.fx.avis.repository.JeuRepository;
import fr.esgi.fx.avis.service.JeuService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@AllArgsConstructor
public class JeuServiceImpl implements JeuService {

    private JeuRepository jeuRepository;
    private JeuMapper jeuMapper;

    @Override
    public Page<Jeu> recupererJeux(Pageable pageable) {
        return jeuRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Jeu ajouterJeu(JeuDto jeuDto) {
        return jeuRepository.save(jeuMapper.toEntity(jeuDto));
    }

    @Override
    public Jeu recupererJeu(Long idJeu) {
        return jeuRepository.findById(idJeu).orElse(null);
    }

    @Override
    public Jeu enregistrerJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    @Transactional
    public boolean ajouterImage(Long id, MultipartFile image) {
        // L'image téléversée doit s'écrire dans le dossier src/main/resources/static
        Path chemin = Paths.get("src/main/resources/static/images/");
        Jeu jeu = recupererJeu(id);

        if (jeu!=null) {
            try (InputStream inputStream = image.getInputStream()) {
                Path cheminFichier = chemin.resolve(id + ".jpg");
                Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                return false;
            }
            jeu.setImage(id + ".jpg");
            enregistrerJeu(jeu);
            return true;
        }
        else {
            return false;
        }
    }
}
