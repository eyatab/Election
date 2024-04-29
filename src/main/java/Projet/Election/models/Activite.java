package Projet.Election.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;
    private String duree;
    @Lob
    private byte[] support;

    //Relation cv et activites
    @ManyToOne
    @JoinColumn(name="idCv")
    private Cv cv;


}
