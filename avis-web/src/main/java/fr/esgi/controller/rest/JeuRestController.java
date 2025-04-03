package fr.esgi.controller.rest;

import fr.esgi.api.JeuService;
import fr.esgi.dto.JeuDto;
import fr.esgi.mapper.JeuDtoMapper;
import fr.esgi.model.Jeu;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jeux")
@AllArgsConstructor
@Validated
public class JeuRestController {

    @Autowired
    private JeuService   jeuService;
    @Autowired
    private JeuDtoMapper jeuMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Jeu postJeu(@Valid @RequestBody JeuDto jeuDto, BindingResult bindingResult) {
        return jeuService.ajouterJeu(jeuMapper.toModel(jeuDto));
    }

}
