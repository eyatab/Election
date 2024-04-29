package Projet.Election.Services;

import Projet.Election.Repositories.ListeCandidatRepository;
import Projet.Election.models.ListeCandidat;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListeCandidatService {

    private ListeCandidatRepository listeCRepos;

    @Autowired
    public ListeCandidatService(ListeCandidatRepository listeCRepos) {
        this.listeCRepos = listeCRepos;
    }
    public ResponseEntity<?> addListe(ListeCandidat listeC)
    {
        List<ListeCandidat> lst1=getListeCandidatByTeteListe(listeC.getTeteListe());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        listeC=listeCRepos.save(listeC);
        return new ResponseEntity<>(listeC,HttpStatus.CREATED);
    }
    // lfonction eli bsh nabi beha elista bl partis
    private List<ListeCandidat> getListeCandidatByTeteListe(int teteliste) {
        return listeCRepos.findAll().stream()
                .filter(x->x.getTeteListe()==teteliste)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<ListeCandidat>> getAllLists()
    {
        List<ListeCandidat> lst1=listeCRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<ListeCandidat> getListeById(long id)
    {
        Optional<ListeCandidat> liste=listeCRepos.findById(id);
        return liste.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<ListeCandidat> updateListe(Long id,ListeCandidat newListe)
    {
        Optional<ListeCandidat> listeC=listeCRepos.findById(id);
        if(listeC.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<ListeCandidat> lst1=getListeCandidatByTeteListe(newListe.getTeteListe());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ListeCandidat liste1=listeC.get();
        liste1.setTeteListe(newListe.getTeteListe());
        ListeCandidat l1=listeCRepos.save(liste1);
        return new ResponseEntity(l1,HttpStatus.ACCEPTED);
    }
    public ResponseEntity deleteListe(Long id)
    {
        Optional<ListeCandidat> listeC=listeCRepos.findById(id);
        if(listeC.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        listeCRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

