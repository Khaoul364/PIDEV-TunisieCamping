package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Utilisateur;
import tn.esprit.spring.repository.PanierEquipementRepository;
import tn.esprit.spring.repository.PanierRepository;
import tn.esprit.spring.repository.UtilisateurRepository;

import java.util.List;

@Service

public class PanierService implements IPanierService{
    @Autowired
    PanierRepository CR;
    @Autowired
    EquipementService ES;
    @Autowired
    UtilisateurRepository UR;
    @Autowired
    PanierEquipementRepository CER;
    @Override
    public List<Panier> fetchPanierList() {
        return null;
    }

    @Override
    public Panier createPanier(Long IdUser) {
        Utilisateur user = UR.findById(IdUser).get();
        Panier panier=new Panier();
        panier.setUtilisateur(user);
        return CR.save(panier);    }

    @Override
    public void deletePanier(Long id) {
        Panier panier = CR.findById(id).orElseThrow();

        panier.setUtilisateur(null);
        CR.delete(panier);
        CR.deleteById(id);

    }

    @Override
    public Panier findByIdPanier(Long idc) {
        Panier panier= CR.findById(idc).get();
        return  panier;    }
}
