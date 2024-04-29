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
public class ListeCandidat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int teteListe;
    private int score;
    private String type;
    private String gouvernorat;


    //Candidat et listeCandidat
    @OneToMany(mappedBy = "listeCandidat")
    private List<Candidat> candidats;

    //Parti et ListeCandidat
    @ManyToOne
    @JoinColumn(name="idParti")
    private Parti parti;

    //ListeCandidat & Administrateur
    @ManyToOne
    @JoinColumn(name="idAdministrateur")
    private Administrateur administrateur;



}
