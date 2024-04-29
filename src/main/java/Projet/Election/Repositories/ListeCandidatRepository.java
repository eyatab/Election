package Projet.Election.Repositories;

import Projet.Election.models.ListeCandidat;
import Projet.Election.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ListeCandidatRepository extends JpaRepository <ListeCandidat,Long> {
}
