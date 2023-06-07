package tn.esprit.spring.service;

import tn.esprit.spring.entity.Activite;

import java.util.List;
import java.util.Optional;

public interface IActiviteService {

    public List<Activite> getAllActivite();

    public Activite addActivite(Activite activite);

    public Activite editActivite(Activite activite, int idActivite);

    public void deleteActivite(int idActivite);

    public Optional<Activite> getActiviteById(int idActivite);

}
