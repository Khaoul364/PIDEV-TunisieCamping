package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Color;
import tn.esprit.spring.entity.Equipement;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
    @Query("SELECT e FROM Equipement e" +
            " JOIN e.categorie c" +
            " WHERE(:color IS NULL OR e.color = :color)" +
            " AND(:categorieName IS NULL OR c.categorieName= :categorieName )" +

            " AND(:marque IS NULL OR e.marque = :marque )")
    List<Equipement> searchEquipment(

            @Param("color") Color color,
            @Param("categorieName") String categorieName,
            @Param("marque") String marque
    );

    @Query("SELECT e FROM Equipement e WHERE e.featured = :featured ")
    List<Equipement> findEquipmentByFeatured(boolean featured);
    @Query("SELECT e FROM Equipement e WHERE e.availabilty = :available")
    List<Equipement> findEquipmentByAvailable(boolean available);
    @Query("SELECT e FROM Equipement e WHERE e.promotion = :promotion")
    List<Equipement> findEquipmentByPromotion(boolean promotion);
    @Query("select e from  Equipement e where " +
            "(:featured is null or e.featured = :featured)")

    List<Equipement> findByFiltersByFeatured(@Param("featured") boolean featured);

    @Query("select e from  Equipement e where " +
            "(:marque is null or e.marque = :marque)")

    List<Equipement> findByFilters(@Param("marque") String marque);

    @Query("select e from  Equipement e where " +
            "(:promotion is null or e.promotion = :promotion)")

    List<Equipement> findByFiltersByPromotion(@Param("promotion") boolean promotion);
}
