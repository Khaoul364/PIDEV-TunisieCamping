package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
