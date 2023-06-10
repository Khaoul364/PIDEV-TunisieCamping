package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
