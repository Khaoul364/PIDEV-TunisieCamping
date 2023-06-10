package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}
