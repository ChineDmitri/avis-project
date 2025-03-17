package fr.esgi.usecase;

import fr.esgi.adapter.PlateformeRepositoryAdapter;
import fr.esgi.model.Plateforme;
import fr.esgi.port.PlateformeRepository;
import fr.esgi.service.PlateformeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PlateformUseCase implements PlateformeService {

    private PlateformeRepository plateformeRepository;

    @Override
    public List<Plateforme> recupererPlateformes() {
        return plateformeRepository.findAll();
    }
}
