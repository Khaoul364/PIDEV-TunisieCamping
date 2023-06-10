package tn.esprit.spring.service;
import tn.esprit.spring.entity.Equipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {
    public List<Equipement> getAllEquipement();

    public Equipement addEquipement(Equipement e,Long idCategorie);

    public Equipement updateEquipment(Equipement equipment, long idEquipement, Long IdCategorie);

    public void deleteEquipement(long idEquipement);

    public Equipement findByIdEquipment(long idEquipement);

    public List<Equipement> retrieveEquipmentById(long idEquipement);
}
