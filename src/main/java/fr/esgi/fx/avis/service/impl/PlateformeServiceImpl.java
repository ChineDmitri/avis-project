package fr.esgi.fx.avis.service.impl;

import fr.esgi.fx.avis.business.Plateforme;
import fr.esgi.fx.avis.repository.PlateformeRepository;
import fr.esgi.fx.avis.service.PlateformeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlateformeServiceImpl implements PlateformeService {

    private PlateformeRepository plateformeRepository;

    @Override
    public List<Plateforme> recupererPlateformes() {
        return plateformeRepository.findAll();
    }
}
