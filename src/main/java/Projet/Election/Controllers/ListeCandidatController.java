package Projet.Election.Controllers;

import Projet.Election.Services.ListeCandidatService;
import Projet.Election.models.ListeCandidat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ListeCandidat")
public class ListeCandidatController {
    @Autowired
    private ListeCandidatService listeCandidatService;
    private final Logger log= LoggerFactory.getLogger(ListeCandidatController.class);


    @PostMapping("/CreateListe")
    public ResponseEntity<?> createListe(@Validated @RequestBody ListeCandidat listeC) {

        log.info("Request for creating Liste {}",listeC);
        return listeCandidatService.addListe(listeC);
    }

    @GetMapping("/GetAllLists")
    public ResponseEntity<?> getAllLists()
    {
        return listeCandidatService.getAllLists();
    }
    @GetMapping("/GetListe/{id}")
    public ResponseEntity<?> getListeById(@PathVariable("id") long id) {
        return listeCandidatService.getListeById(id);
    }

    @PutMapping("/UpdateListe/{id}")
    public ResponseEntity<?> updateListe(@PathVariable("id") long id,@Validated @RequestBody ListeCandidat listeC) {
        log.info("Request for updating ListeCandidat{}",id);
        return listeCandidatService.updateListe(id,listeC);
    }


    @DeleteMapping(value="/DeleteListe/{id}")
    public ResponseEntity<?> deleteListe(@PathVariable("id") long id) {

        log.info("Request for removing Player {}",id);
        return listeCandidatService.deleteListe(id);

    }

}
