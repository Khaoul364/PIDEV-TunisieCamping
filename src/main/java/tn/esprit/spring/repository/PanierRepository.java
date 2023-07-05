package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entity.Panier;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    @Query("SELECT c FROM Panier c where c.utilisateur.idUser= :userId")
    Panier findByUserId(Long userId);
}
