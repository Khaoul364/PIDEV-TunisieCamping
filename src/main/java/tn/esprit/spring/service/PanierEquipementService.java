package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.PanierEquipement;
import tn.esprit.spring.repository.PanierEquipementRepository;

import java.util.List;
@Service
public class PanierEquipementService implements IPanierEquipementService {
    @Autowired
    private PanierEquipementRepository CER;
    @Override
    public List<PanierEquipement> findPanierEquipByIdPanier(Long idPanier) {
        return CER.findPanierEquipmentByIdPanier(idPanier);
    }
}
