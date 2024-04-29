package Projet.Election.Repositories;

import Projet.Election.models.Administrateur;
import Projet.Election.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {
}
