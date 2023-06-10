package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.UtilisateurRepository;

import java.util.List;

@Service

public class FactureService implements IFactureService{
    @Autowired
    private FactureRepository IR;
    @Autowired
    private UtilisateurRepository UR;
    @Autowired
    private PanierService CS;
    @Override
    public Facture updateFacture(Long idFacture, Long idUser) {
        return null;
    }

    @Override
    public void deleteFacture(Long IdFacture) {
        Facture facture = IR.findById(IdFacture).get();
        IR.delete(facture);

    }

    @Override
    public List<Facture> getAllFactures() {
        return IR.findAll();
    }

    @Override
    public Facture findFactureById(Long IdFacture) {
        Facture facture = IR.findById(IdFacture).get();
        return facture;    }
}
