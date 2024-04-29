package Projet.Election.Controllers;

import Projet.Election.Services.AdministrateurService;
import Projet.Election.models.Administrateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Administrateur")
public class AdministrateurController {
    @Autowired
    private AdministrateurService administrateurService;
    private final Logger log = LoggerFactory.getLogger(AdministrateurController.class);

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdministrateur() {
        return administrateurService.getAllAdministrateur();
    }

    @GetMapping("/administrateur/{id}")
    public ResponseEntity<?> getAdministrateur(@PathVariable Long id) {
        return administrateurService.findById(id);
    }


    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdministrateur(@Validated @RequestBody Administrateur admin ) {
        log.info("Request for creating Admin {}", admin);
        return administrateurService.AddAdmin(admin);
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable("id") long id, @Validated @RequestBody Administrateur admin) {
        log.info("Request for updating Admin {}",id);
        return administrateurService.updateAdmin(id,admin);
    }




    @DeleteMapping(value = "/deleteAdmin/{id}")
    public ResponseEntity<HttpStatus> deleteAdministrateur(@PathVariable("id") Long id) {
        log.info("Request for removing administrateur {}", id);
        return administrateurService.deleteAdmin(id);
    }
}

