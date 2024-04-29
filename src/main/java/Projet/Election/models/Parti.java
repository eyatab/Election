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
public class Parti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idParti;
    private String nom;


    //Parti et ListeCandidat
    @OneToMany(mappedBy = "parti")
    private List<ListeCandidat> ListesCandidats;


}
