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
public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String login;
    private String mdp;


    //ListeCandidat & Administrateur
    @OneToMany(mappedBy = "administrateur")
    private List<ListeCandidat> ListesCandidat;

}