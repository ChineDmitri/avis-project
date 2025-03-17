package fr.esgi.usecase;

import fr.esgi.entity.JeuEntity;
import fr.esgi.model.Jeu;
import fr.esgi.port.JeuRepository;
import fr.esgi.service.JeuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@AllArgsConstructor
public class JeuUseCase implements JeuService {

    private JeuRepository jeuRepository;

    @Override
    public Jeu ajouterJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    public Jeu recupererJeu(Long idJeu) {
        return jeuRepository.findById(idJeu);
    }

    @Override
    public Jeu enregistrerJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    public boolean ajouterImage(Long id, FileInputStream image) {
        try {
            // Find the game
            Jeu jeu = jeuRepository.findById(id);

            // Create directory in avis-web if it doesn't exist
            Path uploadDir = Paths.get("avis-web/src/main/resources/static/images/");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Generate unique filename
            String filename = id + "_" + System.currentTimeMillis() + ".jpg";
            Path filePath = uploadDir.resolve(filename);

            // Save the image
            Files.copy(image, filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update jeu with image path
            jeu.setImage(filePath.toString());
            jeuRepository.save(jeu);

            return true;
        } catch (IOException | IllegalArgumentException e) {
            // Log error
            System.err.println("Error adding image: " + e.getMessage());
            return false;
        }
    }
}
