package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Activite;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {
}
