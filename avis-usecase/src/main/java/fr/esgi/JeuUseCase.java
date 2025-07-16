package fr.esgi;

import fr.esgi.api.JeuService;
import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.port.JeuRepository;
import fr.esgi.port.decorator.FileUploaderDecorator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
@Log4j2
public class JeuUseCase implements JeuService {

    private final JeuRepository         jeuRepository;
    @Qualifier("fileUploaderWeb")
    private final FileUploaderDecorator fileAdapter;

    @Override
    public Jeu ajouterJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    public Jeu recupererJeu(Long idJeu) {
        return jeuRepository.findById(idJeu);
    }

    @Override
    public CustomPagedResult<Jeu> recupererJeux(PaginationParams paginationParams) {
        return jeuRepository.findAll(paginationParams);
    }

    @Override
    public Jeu enregistrerJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    public String ajouterImage(Long id, InputStream is) {
        Jeu jeu = jeuRepository.findById(id);
        if (jeu == null) {
            throw new IllegalArgumentException("Jeu non trouvé avec l'id: " + id);
        }

        String fileNameWithPath = fileAdapter.upload(is);
        log.info("Image ajoutée pour le jeu {} avec le nom de fichier: {}", jeu.getNom(), fileNameWithPath);

        Jeu jeuAvecImage = Jeu.builder()
                              .id(jeu.getId())
                              .nom(jeu.getNom())
                              .description(jeu.getDescription())
                              .dateDeSortie(jeu.getDateDeSortie())
                              .prix(jeu.getPrix())
                              .editeur(jeu.getEditeur())
                              .genre(jeu.getGenre())
                              .plateformes(jeu.getPlateformes())
                              .image(fileNameWithPath)
                              .build();

        jeuRepository.save(jeuAvecImage);
        return fileNameWithPath;
    }

    @Override
    public String ajouterImage(Long id, MultipartFile multipartFile) throws
                                                                     TechnicalException {
        Jeu jeu = jeuRepository.findById(id);
        if (jeu == null) {
            throw new IllegalArgumentException("Jeu non trouvé avec l'id: " + id);
        }

        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("Le fichier ne peut pas être vide");
        }

        // Cast to FileUploaderWebDecorator to access the MultipartFile upload method
        fr.esgi.decorator.FileUploaderWebDecorator webDecorator =
                (fr.esgi.decorator.FileUploaderWebDecorator) fileAdapter;

        String fileNameWithPath = webDecorator.upload(multipartFile);
        log.info("Image ajoutée pour le jeu {} avec le nom de fichier: {}", jeu.getNom(), fileNameWithPath);

        Jeu jeuAvecImage = Jeu.builder()
                              .id(jeu.getId())
                              .nom(jeu.getNom())
                              .description(jeu.getDescription())
                              .dateDeSortie(jeu.getDateDeSortie())
                              .prix(jeu.getPrix())
                              .editeur(jeu.getEditeur())
                              .genre(jeu.getGenre())
                              .plateformes(jeu.getPlateformes())
                              .image(fileNameWithPath)
                              .build();

        jeuRepository.save(jeuAvecImage);
        return fileNameWithPath;
    }
}
