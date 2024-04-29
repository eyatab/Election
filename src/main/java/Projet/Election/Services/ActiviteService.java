package Projet.Election.Services;

import Projet.Election.Repositories.ActiviteRepository;
import Projet.Election.models.Activite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiviteService {
    private ActiviteRepository activiteRepos;

    @Autowired
    public ActiviteService(ActiviteRepository ActiviteRepos) {
        this.activiteRepos = ActiviteRepos;
    }
    public ResponseEntity<?> AddActivite (Activite a1)
    {
        List<Activite > lst1=getActiviteByTypeAndDuree(a1.getType(),a1.getDuree());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Activite activite =activiteRepos.save(a1);
        return new ResponseEntity<>(activite,HttpStatus.CREATED);
    }

    private List<Activite> getActiviteByTypeAndDuree (String type, String duree) {
        return activiteRepos.findAll().stream()
                .filter(x->x.getType()==type&&x.getDuree().equals(duree))
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Activite>> GetAllActivite()
    {
        List<Activite> lst1=activiteRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<Activite> GetActiviteById(Long id)
    {
        Optional<Activite> act=activiteRepos.findById(id);
        return act.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Activite> UpdateActivite (Long id,Activite newActivite)
    {
        Optional<Activite>act=activiteRepos.findById(id);
        if(act.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Activite > lst1=getActiviteByTypeAndDuree(newActivite.getType(),newActivite.getDuree());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Activite act1=act.get();
        act1.setType(newActivite.getType());
        act1.setDuree(newActivite.getDuree());
        act1.setSupport(newActivite.getSupport());
        Activite a1=activiteRepos.save(act1);//save has double role 1-add new line if the id doesn't exist
        //2-update an existing line if the id exist
        return new ResponseEntity(act1,HttpStatus.ACCEPTED);
    }
    public ResponseEntity DeleteActivite(long id)
    {
        Optional<Activite> act=activiteRepos.findById(id);
        if(act.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        activiteRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
