package Projet.Election.Controllers;

import Projet.Election.Services.AvisService;
import Projet.Election.Services.CandidatService;
import Projet.Election.models.Avis;
import Projet.Election.models.Candidat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Candidat")
public class CandidatController {
    @Autowired
    private CandidatService candidatService;

    private final Logger log= LoggerFactory.getLogger(CandidatController.class);

    @GetMapping("/GetAllCandidats")
    public ResponseEntity<?> getAllCandidats(){
        return candidatService.GetAllCandidat();
    }

    @GetMapping("/GetCandidat/{id}")
    public ResponseEntity<?> getCandidatById(@PathVariable("id") long id) {
        return candidatService.GetCandidatById(id);
    }
    @PostMapping("/CreateCandidat")
    public ResponseEntity<?> createCandidat(@Validated @RequestBody Candidat candidat) {
        log.info("Request for creating candidat {}",candidat);
        return candidatService.AddCandidat(candidat);
    }
    @PutMapping("/UpdateCandidat/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable("id") long id, @Validated @RequestBody Candidat candidat) {
        log.info("Request for updating candidat {}",id);
        return candidatService.UpdateCandidat(id,candidat);
    }
    @DeleteMapping("/DeleteCandidat/{id}")
    public ResponseEntity<Candidat> deleteCandidat(@PathVariable("id") long id){
        log.info("Resquest for deleting candidat {}",id);
        return candidatService.DeleteCandidat(id) ;
    }
}
