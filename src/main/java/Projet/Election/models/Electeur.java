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
public class Electeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Avis et Electeur
    @OneToOne
    private Avis idAvis;

    //Reclamation et electeur
    @OneToMany(mappedBy = "electeur")
    private List<Reclamation> listeReclamations;

}
