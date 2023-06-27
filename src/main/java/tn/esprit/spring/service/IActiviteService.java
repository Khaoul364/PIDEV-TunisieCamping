package tn.esprit.spring.service;

import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IActiviteService {

    public List<Activite> getAllActivite();

    public Activite addActivite(Activite activite);

    //public Activite editActivite(Activite activite, int idActivite);

    public void editActivite(Activite activite);

    public void deleteActivite(int idActivite);

    public Activite getActiviteById(int idActivite);

    public void assignLieuToActivite(Activite activite, int idLieu);

}
