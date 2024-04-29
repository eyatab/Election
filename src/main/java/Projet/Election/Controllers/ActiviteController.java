package Projet.Election.Controllers;

import Projet.Election.Services.ActiviteService;
import Projet.Election.models.Activite;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Activite")
public class ActiviteController {
    @Autowired
    private ActiviteService activiteService;
    private final Logger log= LoggerFactory.getLogger(ActiviteController.class);

    @GetMapping("/all")
    public ResponseEntity<?> getAllActivite()
    {

        return activiteService.GetAllActivite();
    }

    @GetMapping("/activite/{id}")
    public ResponseEntity<?> getActiviteByID(@PathVariable ("id") Long id){

        return activiteService.GetActiviteById(id);
    }
    @PostMapping("/createactivite")
    public ResponseEntity<?> createActivite(@Validated @RequestBody Activite act) {

        log.info("Request for creating activity {}",act);
        return activiteService.AddActivite(act);
    }
    @PutMapping("/activite/{id}")
    public ResponseEntity<Activite> updateactivite(@PathVariable("id") long id, @Validated @RequestBody Activite act) {
        log.info("Request for updating Activite {}",id);
        return activiteService.UpdateActivite(id,act);
    }
    @DeleteMapping(value = "/deleteactivite/{id}")
    public ResponseEntity<HttpStatus> deleteActivite(@PathVariable("id") long id) {

        log.info("Request for removing activity {}",id);
        return activiteService.DeleteActivite(id);

    }


}
