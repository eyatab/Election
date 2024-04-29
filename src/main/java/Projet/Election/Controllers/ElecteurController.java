package Projet.Election.Controllers;

import Projet.Election.Services.ElecteurService;
import Projet.Election.models.Electeur;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Electeur")
public class ElecteurController {
    private ElecteurService electeurService ;
    @Autowired
    private final Logger log = LoggerFactory.getLogger(ElecteurController.class);
    @GetMapping("/all")
    public ResponseEntity<List<Electeur>> getAllElecteurs()
    {
        return electeurService.getAllElecteurs();
    }
    @GetMapping("/electeur/{id}")
    public ResponseEntity<?> getElecteurById(@PathVariable int id)
    {
        return electeurService.getElecteurById(id);
    }

    @PostMapping("/newelecteur")
    public ResponseEntity<Electeur> addNewnElecteur(@Validated @RequestBody Electeur e1)
    {

        return electeurService.addElecteur(e1);
    }
    @PutMapping("/competition/{id}")
    public ResponseEntity<Electeur> modifyElecteur(@PathVariable int id, @Validated @RequestBody Electeur e1) {
        return electeurService.updateElecteur(id, e1);
    }

}
