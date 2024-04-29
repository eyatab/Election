package Projet.Election.Services;

import Projet.Election.Repositories.AvisRepository;
import Projet.Election.Repositories.CandidatRepository;
import Projet.Election.models.Administrateur;
import Projet.Election.models.Avis;
import Projet.Election.models.Candidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;

    //ou bien mettre @Autowired ici

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public ResponseEntity<Candidat> AddCandidat(Candidat candidat) {

        List<Candidat> list = getCandidatByNomPrenomAndNomParti(candidat.getNom(), candidat.getPrenom(),candidat.getNomParti());
        if (!list.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Candidat _candidat = candidatRepository.save(candidat);
        return new ResponseEntity<>(_candidat, HttpStatus.CREATED);

    }


    private List<Candidat> getCandidatByNomPrenomAndNomParti(String nom, String prenom,String nomParti) {
        return candidatRepository.findAll().stream()
                .filter(x -> x.getNom() == nom && x.getPrenom()==prenom && x.getNomParti().toLowerCase().equals(nomParti.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Candidat>> GetAllCandidat(){
        List<Candidat> list=candidatRepository.findAll();
        if(list.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return  new ResponseEntity(list,HttpStatus.OK);

    }
    public ResponseEntity<Avis> GetCandidatById(long id){
        Optional<Candidat> candidat=candidatRepository.findById(id);
       /* if(player.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        return  new ResponseEntity(player.get(),HttpStatus.OK);*/

        return candidat.map(x->ResponseEntity.ok().body(x)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Candidat> UpdateCandidat(long id,Candidat newCandidat){

        Optional<Candidat> oldCandidat=candidatRepository.findById(id);
        if(oldCandidat.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Candidat> list=getCandidatByNomPrenomAndNomParti(newCandidat.getNom(), newCandidat.getPrenom(),newCandidat.getNomParti());
        if(!list.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Candidat candidat=oldCandidat.get();
        candidat.setNom(newCandidat.getNom());
        candidat.setPrenom(newCandidat.getPrenom());
        candidat.setDateNaiss(newCandidat.getDateNaiss());
        candidat.setPhoto(newCandidat.getPhoto());
        candidat.setFacebookLien(newCandidat.getFacebookLien());
        candidat.setTwitterLien(newCandidat.getTwitterLien());
        candidat.setScore(newCandidat.getScore());

        Candidat _candidat=candidatRepository.save(candidat);
        return new ResponseEntity(_candidat,HttpStatus.ACCEPTED);

    }
    public ResponseEntity<Candidat> DeleteCandidat(long id){
        Optional<Candidat> candidat=candidatRepository.findById(id);
        if(candidat.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        candidatRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);

    }
}
