package Projet.Election.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String justificatif;

    //Candidat et Reclamation
    @ManyToOne
    @JoinColumn(name="idCandidat")
    private Candidat candidat;

    //Electeur et Reclamation
    @ManyToOne
    @JoinColumn(name="idElecteur")
    private Electeur electeur;


}
