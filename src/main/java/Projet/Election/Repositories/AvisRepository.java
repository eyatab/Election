package Projet.Election.Repositories;

import Projet.Election.models.Avis;
import Projet.Election.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AvisRepository extends JpaRepository<Avis,String> {
}
