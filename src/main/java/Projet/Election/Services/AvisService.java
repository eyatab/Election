package Projet.Election.Services;

import Projet.Election.Repositories.AvisRepository;
import Projet.Election.models.Administrateur;
import Projet.Election.models.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvisService {
    @Autowired
    private AvisRepository avisRepository;

    //ou bien mettre @Autowired ici


    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    public ResponseEntity<Avis> AddAvis(Avis avis) {

        List<Avis> avis1=getAvisById(avis);
        if (!avis1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Avis _avis = avisRepository.save(avis);
        return new ResponseEntity<>(_avis, HttpStatus.CREATED);

    }

    private List<Avis> getAvisById(Avis avis) {
        return avisRepository.findAll().stream()
                .filter(x -> x.getIdAvis() .equals(avis.getIdAvis()))
                .collect(Collectors.toList());
    }



    public ResponseEntity<List<Avis>> GetAllAvis(){
        List<Avis> list=avisRepository.findAll();
        if(list.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return  new ResponseEntity(list,HttpStatus.OK);

    }
    public ResponseEntity<Avis> GetAvisById(String id){
        Optional<Avis> avis=avisRepository.findById(id);
       /* if(player.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        return  new ResponseEntity(player.get(),HttpStatus.OK);*/

        return avis.map(x->ResponseEntity.ok().body(x)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Avis> UpdateAvis(String id,Avis newAvis){

        Optional<Avis> oldAvis=avisRepository.findById(id);
        if(oldAvis.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        Avis avis=oldAvis.get();
        avis.setNoteCandidat(newAvis.getNoteCandidat());
        Avis _avis=avisRepository.save(avis);
        return new ResponseEntity(_avis,HttpStatus.ACCEPTED);

    }
    public ResponseEntity<Avis> DeleteAvis(String id){
        Optional<Avis> avis=avisRepository.findById(id);
        if(avis.isEmpty())
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        avisRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);

    }
}
