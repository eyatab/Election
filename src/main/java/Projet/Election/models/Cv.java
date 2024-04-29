package Projet.Election.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String niveauScientifique;
    private String expPolitique;
    private String expProf;



    //Activite et Cv
    @OneToMany(mappedBy="cv")
    private List<Activite> Activites;

    //Cv et candidat
    @OneToOne(mappedBy = "cv")
    private Candidat candidat;


}
