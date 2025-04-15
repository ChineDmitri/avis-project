package fr.esgi;

import fr.esgi.api.JeuService;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.port.JeuRepository;
import fr.esgi.port.decorator.FileUploaderDecorator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
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
        Jeu    jeu              = jeuRepository.findById(id);
        String fileNameWithPath = fileAdapter.upload(is);
        jeu.setImage(fileNameWithPath);
        jeuRepository.save(jeu);
        return fileNameWithPath;

    }
}
