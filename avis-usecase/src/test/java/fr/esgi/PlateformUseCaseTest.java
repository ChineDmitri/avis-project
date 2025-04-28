package fr.esgi;

import fr.esgi.model.Plateforme;
import fr.esgi.port.PlateformeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class PlateformUseCaseTest {
    @Mock
    PlateformeRepository plateformeRepository;
    @InjectMocks
    PlateformUseCase plateformUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRecupererPlateformes() {
        when(plateformeRepository.findAll()).thenReturn(List.of(new Plateforme("nom")));

        List<Plateforme> result = plateformUseCase.recupererPlateformes();
        Assertions.assertEquals(List.of(new Plateforme("nom")), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme