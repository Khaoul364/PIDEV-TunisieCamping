package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entity.PanierEquipement;

import java.util.List;

public interface PanierEquipementRepository extends JpaRepository<PanierEquipement,Long> {
    @Query("SELECT a FROM PanierEquipement a where a.equipement.idEquipement= :idEquip and a.panier.idPanier=:idPanier")
    PanierEquipement findCartEquipmentByCartAndEquipment(Long idEquip,Long idPanier);
    @Query("SELECT a FROM PanierEquipement a where a.panier.idPanier=:idPanier")
    List<PanierEquipement> findPanierEquipmentByIdPanier(Long idPanier);
}
