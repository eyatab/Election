package Projet.Election.Services;


import Projet.Election.Repositories.ElecteurRepository;
import Projet.Election.models.Electeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElecteurService {
    private static ElecteurRepository eleRepos;

    @Autowired
    public ElecteurService(ElecteurRepository eleRepos) {
        this.eleRepos = eleRepos;
    }

    public static ResponseEntity<Electeur> addElecteur(Electeur e1)
    {
        List<Electeur> lst1=findByID(e1.getId());
        if(!lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        Electeur electeur=eleRepos.save(e1);
        return new ResponseEntity(electeur,HttpStatus.CREATED);
    }

    private static List<Electeur> findByID(long id) {
        return eleRepos.findAll().stream()
                .filter(x->x.getId()==(id))
                .collect(Collectors.toList());
    }


    public static ResponseEntity<List<Electeur>> getAllElecteurs()
    {
        List<Electeur> lst1=eleRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public static ResponseEntity<Electeur> getElecteurById(long id)
    {
        Optional<Electeur> result=eleRepos.findById(id);
//        if(result.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(result.get(),HttpStatus.OK);
        //or
        return result.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    public static ResponseEntity<Electeur> updateElecteur(long id, Electeur newElecteur)
    {
        Optional<Electeur> result=eleRepos.findById(id);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Electeur> lst1=findByID(newElecteur.getId());
        if(!lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        //save has double role : 1- add a new line if the id is a new one
        //2- update an existing line if the id is already affected
        Electeur electeur=result.get();
        electeur.setIdAvis(newElecteur.getIdAvis());
        Electeur electeur1=eleRepos.save(electeur);
        return new ResponseEntity(electeur1,HttpStatus.ACCEPTED);
    }
}