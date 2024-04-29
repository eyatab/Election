package Projet.Election.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    @Lob
    private byte[] photo;
    private String nomParti;
    private URL facebookLien;
    private URL twitterLien;
    private int score;

    //Cv et candidat
    @OneToOne
    private Cv cv;

    //Candidat et Reclamation
    @OneToMany(mappedBy = "candidat")
    private List<Reclamation> listeReclamation;

    //Candidat et listeCandidat
    @ManyToOne
    @JoinColumn(name="idListeCandidat")
    private ListeCandidat listeCandidat;

}