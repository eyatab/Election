package Projet.Election.Services;

import Projet.Election.Repositories.CvRepository;
import Projet.Election.models.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CvService {
    private CvRepository cvRepos;

    @Autowired
    public CvService(CvRepository cvRepos) {
        this.cvRepos = cvRepos;
    }
    public ResponseEntity<?> addCv(Cv c1)
    {
        List<Cv> lst1=getCvByID(c1.getId());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Cv cv=cvRepos.save(c1);
        return new ResponseEntity<>(cv,HttpStatus.CREATED);
    }



    private List<Cv> getCvByID(long id) {
        return cvRepos.findAll().stream()
                .filter(x->x.getId()==id)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Cv>> getAllCvs()
    {
        List<Cv> lst1=cvRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<Cv> getCvById(long id)
    {
        Optional<Cv> cv=cvRepos.findById(id);
//        if(player.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(player.get(),HttpStatus.OK);
        return cv.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Cv> updateCv(long id,Cv newCv)
    {
        Optional<Cv> cv=cvRepos.findById(id);
        if(cv.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Cv> lst1=getCvByID(newCv.getId());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Cv cv1=cv.get();
        //cv.setniveauScientifique(newCv.getniveauScientifique());
        //cv.setexpPolitique(newCv.getexpPolitique());
        //cv.setexpProf(newCv.getexpProf());
        //cv.getcandidat().setid(newCv.getcandidat().getid());
        Cv c1=cvRepos.save(cv1);//save has double role 1-add new line if the id doesn't exist
        //2-update an existing line if the id exist
        return new ResponseEntity(c1,HttpStatus.ACCEPTED);
    }
    public ResponseEntity deleteCv(long id)
    {
        Optional<Cv> player=cvRepos.findById(id);
        if(player.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        cvRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
