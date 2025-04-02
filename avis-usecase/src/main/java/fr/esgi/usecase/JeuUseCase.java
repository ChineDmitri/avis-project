package fr.esgi.usecase;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.port.FileUploader;
import fr.esgi.port.JeuRepository;
import fr.esgi.api.JeuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class JeuUseCase  implements JeuService {

//    public JeuUseCase(FileUploader delegate) {
//        super(delegate);
//    }


    @Autowired
    @Qualifier("fileUploaderDecorator")
    private FileUploader fileUploader;
    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private PageAdapter pageAdapter;

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
    public String ajouterImage(Long id, InputStream image) {
//        try {
            // Find the game
            Jeu jeu = jeuRepository.findById(id);

            // Create directory in avis-web if it doesn't exist
           String fileNameWithPath = this.fileUploader.upload(jeu, image);
           jeu.setImage(fileNameWithPath);
           jeuRepository.save(jeu);
           return fileNameWithPath;
//        return null;
//        } catch (IOException | IllegalArgumentException e) {
//            // Log error
//            System.err.println("Error adding image: " + e.getMessage());
//            return StringUtils.EMPTY;
//        }
    }
}
