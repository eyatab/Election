package Projet.Election.Services;

import Projet.Election.Repositories.PartiRepository;
import Projet.Election.models.Parti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartiService {

    private PartiRepository partiRepos;

    @Autowired
    public PartiService(PartiRepository partiRepos) {
        this.partiRepos = partiRepos;
    }
    //nlawej al parti ken mosh mawjouda nhotha
    public ResponseEntity<?> addParti(Parti parti)
    {
        List<Parti> lst1=getPartiBynom(parti.getNom());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        parti=partiRepos.save(parti);
        return new ResponseEntity<>(parti,HttpStatus.CREATED);
    }
    // lfonction eli bsh nabi beha elista bl partis
    private List<Parti> getPartiBynom(String nom) {
        return partiRepos.findAll().stream()
                .filter(x->x.getNom()==nom)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Parti>> getAllParti()
    {
        List<Parti> lst1=partiRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<Parti> getPartiById(Long id)
    {
        Optional<Parti> parti=partiRepos.findById(id);
        return parti.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Parti> updateParti(Long id,Parti newParti)
    {
        Optional<Parti> parti=partiRepos.findById(id);
        if(parti.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Parti> lst1=getPartiBynom(newParti.getNom());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Parti parti1=parti.get();
        parti1.setNom(newParti.getNom());
        Parti p1=partiRepos.save(parti1);
        return new ResponseEntity(p1,HttpStatus.ACCEPTED);
    }
    public ResponseEntity deleteParti(Long id)
    {
        Optional<Parti> player=partiRepos.findById(id);
        if(player.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        partiRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
