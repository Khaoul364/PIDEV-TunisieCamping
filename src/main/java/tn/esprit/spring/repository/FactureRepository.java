package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entity.Facture;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    @Query("select MONTHNAME (i.date) as month, YEAR(i.date) as year, sum (i.totale) as total, sum(i.totalNumberEquipPurchased) as TotalOfPurchasedEquipment from Facture i group by month(i.date),YEAR (i.date)")
    List<Object[]> getTotalPriceAndNumberOfEquipementByMonthAndYear();

    @Query("SELECT d FROM Facture d where d.utilisateur.idUser= :userId")
    List<Facture> findByUserId(Long userId);
}
