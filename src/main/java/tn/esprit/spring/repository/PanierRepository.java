package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Panier;

public interface PanierRepository extends JpaRepository<Panier, Long> {
}
