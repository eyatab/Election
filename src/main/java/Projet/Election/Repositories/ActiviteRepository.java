package Projet.Election.Repositories;

import Projet.Election.models.Activite;
import Projet.Election.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActiviteRepository extends JpaRepository<Activite,Long>  {
}
