package fr.esgi.fx.avis.controller.rest;

import fr.esgi.fx.avis.business.Editeur;
import fr.esgi.fx.avis.dto.EditeurDto;
import fr.esgi.fx.avis.exception.EditeurDejaPresentException;
import fr.esgi.fx.avis.exception.EditeurInexistantException;
import fr.esgi.fx.avis.service.EditeurService;
import fr.esgi.fx.avis.util.ReponseApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/editeurs")
@AllArgsConstructor
@Validated
public class EditeurRestController {

    private EditeurService editeurService;

    @GetMapping("")
    public List<Editeur> getEditeurs() {
        return editeurService.recupererEditeurs();
    }

/*
    @GetMapping("/{id}")
    public Editeur getEditeur(@PathVariable Long id) {
        return editeurService.recupererEditeur(id);
    }
*/
/*
    @GetMapping("/{id}")
    ResponseEntity<Editeur> getEditeurById(@PathVariable Long id){
        Editeur editeur = editeurService.recupererEditeur(id);
        return editeur != null
                ? ResponseEntity.ok(editeur)
                : ResponseEntity.notFound().build();
    }
    */

    @PostMapping("")
    @ResponseStatus(code= HttpStatus.CREATED)
    //https://sonarsource.atlassian.net/browse/RSPEC-4684
    //public Editeur postEditeur(@Valid @RequestBody Editeur editeur, BindingResult bindingResult) {
    public Editeur postEditeur(@Valid @RequestBody EditeurDto editeurDto, BindingResult bindingResult) {
        return editeurService.ajouterEditeur(editeurDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditeur(@PathVariable Long id) {
        editeurService.supprimerEditeur(id);
    }

    @ExceptionHandler(EditeurDejaPresentException.class)
    @ResponseStatus(code=HttpStatus.CONFLICT)
    public ReponseApi<String> traiterEditeurDejaPresentException(EditeurDejaPresentException e) {
        return new ReponseApi<String>("409", e.getMessage(), null);

    }

    @ExceptionHandler(EditeurInexistantException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ReponseApi<String> traiterEditeurInexistantException(EditeurInexistantException e) {
        return new ReponseApi<>("404", e.getMessage(), null);
    }
/*
    @ExceptionHandler({ EditeurInexistantException.class })
    public ResponseEntity<String> traiterEditeurInexistantException(Exception exception) {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        return ResponseEntity.notFound().build();
    }
    */

}
