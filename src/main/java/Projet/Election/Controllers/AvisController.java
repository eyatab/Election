package Projet.Election.Controllers;

import Projet.Election.Services.AvisService;
import Projet.Election.models.Avis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Avis")
public class AvisController {
    @Autowired
    private AvisService avisService;

    private final Logger log= LoggerFactory.getLogger(AvisController.class);

    @GetMapping("/GetAllAvis")
    public ResponseEntity<?> getAllAviss(){
        return avisService.GetAllAvis();
    }

    @GetMapping("/getAvis/{id}")
    public ResponseEntity<?> getAvisById(@PathVariable("id") String id) {
        return avisService.GetAvisById(id);
    }
    @PostMapping("/CreateAvis")
    public ResponseEntity<?> createAvis(@Validated @RequestBody Avis avis) {
        log.info("Request for creating avis {}",avis);
        return avisService.AddAvis(avis);
    }
    @PutMapping("/UpdateAvis/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable("id") String id, @Validated @RequestBody Avis avis) {
        log.info("Request for updating avis {}",id);
        return avisService.UpdateAvis(id,avis);
    }
    @DeleteMapping("/DeleteAvis/{id}")
    public ResponseEntity<Avis> deletePlayer(@PathVariable("id") String id){
        log.info("Resquest for deleting player {}",id);
        return avisService.DeleteAvis(id) ;
    }
}
