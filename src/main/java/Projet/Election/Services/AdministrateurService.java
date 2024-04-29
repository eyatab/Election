package Projet.Election.Services;

import Projet.Election.Repositories.AdministrateurRepository;
import Projet.Election.models.Administrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministrateurService {

    private AdministrateurRepository administrateurRepos;

    @Autowired
    public AdministrateurService(AdministrateurRepository AdminiRepos) {
        this.administrateurRepos = AdminiRepos;
    }

    public ResponseEntity<?> AddAdmin(Administrateur a1) {
        List<Administrateur> lst1 = getAdminByLoginAndMdp(a1.getLogin(), a1.getMdp());
        if (!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Administrateur admin = administrateurRepos.save(a1);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    private List<Administrateur> getAdminByLoginAndMdp(String login, String mdp) {
        return administrateurRepos.findAll().stream()
                .filter(x -> x.getLogin() == login && x.getMdp().toLowerCase().equals(mdp.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Administrateur>> getAllAdministrateur() {
        List<Administrateur> lst1 = administrateurRepos.findAll();
        if (lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1, HttpStatus.OK);
    }

    public ResponseEntity<Administrateur> findById(long id) {
        Optional<Administrateur> ad = administrateurRepos.findById(id);
        return ad.map(x -> ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Administrateur> updateAdmin(long id, Administrateur newAdmin) {
        Optional<Administrateur> admi = administrateurRepos.findById(id);
        if (admi.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Administrateur> lst1 = getAdminByLoginAndMdp(newAdmin.getLogin(), newAdmin.getMdp());
        if (!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Administrateur ad1 = admi.get();
        ad1.setLogin(newAdmin.getLogin());
        ad1.setMdp(newAdmin.getMdp());
        Administrateur a1 = administrateurRepos.save(ad1);//save has double role 1-add new line if the id doesn't exist
        //2-update an existing line if the id exist
        return new ResponseEntity(ad1, HttpStatus.ACCEPTED);
    }

    public ResponseEntity deleteAdmin(Long id) {
        Optional<Administrateur> admi = administrateurRepos.findById(id);
        if (admi.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        administrateurRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

