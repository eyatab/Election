package Projet.Election.Controllers;

import Projet.Election.Services.PartiService;
import Projet.Election.models.Parti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Parti")
public class PartiController {
    @Autowired
    private PartiService partiService;
    private final Logger log= LoggerFactory.getLogger(PartiController.class);


    @PostMapping("/CreateParti")
    public ResponseEntity<?> createParti(@Validated @RequestBody Parti parti) {

        log.info("Request for creating Parti {}",parti);
        return partiService.addParti(parti);
    }

    @GetMapping("/GetAllPartis")
    public ResponseEntity<?> getAllPartis()
    {
        return partiService.getAllParti();
    }
    @GetMapping("/GetParti/{id}")
    public ResponseEntity<?> getPartiById(@PathVariable("id") long id) {
        return partiService.getPartiById(id);
    }

    @PutMapping("/UpdateParti/{id}")
    public ResponseEntity<Parti> updateParti(@PathVariable("id") long id,@Validated @RequestBody Parti parti) {
        log.info("Request for updating Parti{}",id);
        return partiService.updateParti(id,parti);
    }


    @DeleteMapping(value="/DeleteParti/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") long id) {

        log.info("Request for removing Player {}",id);
        return partiService.deleteParti(id);

    }

}
