package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
