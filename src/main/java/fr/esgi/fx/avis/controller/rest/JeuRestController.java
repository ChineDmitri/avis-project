package fr.esgi.fx.avis.controller.rest;

import fr.esgi.fx.avis.business.Jeu;
import fr.esgi.fx.avis.dto.JeuDto;
import fr.esgi.fx.avis.service.JeuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/jeux")
@AllArgsConstructor
@Validated
public class JeuRestController {

    private JeuService jeuService;

    @GetMapping("")
    public Page<Jeu> getJeux(@PageableDefault(size = 10, sort = "nom", direction = Sort.Direction.DESC) Pageable pageable) {
        return jeuService.recupererJeux(pageable);
    }

    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public Jeu postJeu(@Valid @RequestBody JeuDto jeuDto, BindingResult bindingResult) {
        return jeuService.ajouterJeu(jeuDto);
    }

    @PostMapping("/{idJeu}/image")
    public boolean postImage(@PathVariable Long idJeu, @RequestParam("file") MultipartFile image) {
      return jeuService.ajouterImage(idJeu, image);
    }
}
