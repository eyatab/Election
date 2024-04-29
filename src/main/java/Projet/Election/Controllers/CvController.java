package Projet.Election.Controllers;

import Projet.Election.Services.CvService;
import Projet.Election.models.Cv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Cv")
public class CvController {
    @Autowired
    private CvService cvServ;

    private final Logger log= LoggerFactory.getLogger(CvController.class);

    @GetMapping("/all")
    public ResponseEntity<?> getAllCvs()
    {
        return cvServ.getAllCvs();
    }
    @GetMapping("/cv/{id}")
    public ResponseEntity<?> getCvById(@PathVariable("id") int id) {
        return cvServ.getCvById(id);
    }

    @PostMapping("/player")
    public ResponseEntity<?> createCv(@Validated @RequestBody Cv cv) {

        log.info("Request for creating Cv {}",cv);
        return cvServ.addCv(cv);
    }

    @PutMapping("/cv/{id}")
    public ResponseEntity<Cv> updateCv(@PathVariable("id") int id,@Validated @RequestBody Cv cv) {
        log.info("Request for updating Cv {}",id);
        return cvServ.updateCv(id,cv);
    }

    @DeleteMapping("/cv/{id}")
    public ResponseEntity<HttpStatus> deleteCv(@PathVariable("id") int id) {

        log.info("Request for removing Cv {}",id);
        return cvServ.deleteCv(id);

    }

}

