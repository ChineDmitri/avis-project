package fr.esgi.adapter;

import fr.esgi.model.Jeu;
import fr.esgi.port.FileUploader;
import fr.esgi.port.decorator.FileUploaderDecorator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component("fileUploaderDecorator")
@AllArgsConstructor
public class FileUploaderAdapter implements FileUploader {

    @Override
    public String upload(Jeu jeu, InputStream io) {
        try {
            Path uploadDir = Paths.get("avis-web/src/main/resources/static/images/");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }


            // Generate unique filename
            String filename = jeu.getNom() + "_" + System.currentTimeMillis() + ".jpg";
            Path   filePath = uploadDir.resolve(filename);

            // Save the image
            Files.copy(io, filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update jeu with image path
            jeu.setImage(filePath.toString());
            //            jeuRepository.save(jeu);

            return filename;
        } catch (IOException | IllegalArgumentException e) {
            // Log error
            System.err.println("Error adding image: " + e.getMessage());
            return StringUtils.EMPTY;
        }
    }

}
