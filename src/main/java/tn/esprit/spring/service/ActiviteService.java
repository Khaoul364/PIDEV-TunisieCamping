package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.repository.ActiviteRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ActiviteService implements IActiviteService{

    ActiviteRepository activiteRepository;

    @Override
    public List<Activite> getAllActivite() {
        return activiteRepository.findAll();
    }

    @Override
    public Activite addActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    @Override
    public Activite editActivite(Activite activite, int idActivite) {
        Activite act = activiteRepository.findById(idActivite).orElse(null);
        act.setLieuActivite(activite.getLieuActivite());
        act.setNomActivite(activite.getNomActivite());
        act.setImage(activite.getImage());
        act.setDescription(activite.getDescription());
        act.setDate_fin(activite.getDate_fin());
        act.setDate_deb(activite.getDate_deb());
        act.setPosts(activite.getPosts());
        act.setReservations(activite.getReservations());
        act.setUtilisateurs(activite.getUtilisateurs());

        return activiteRepository.save(act);
    }

    @Override
    public void deleteActivite(int idActivite) {
        activiteRepository.deleteById(idActivite);
    }

    @Override
    public Optional<Activite> getActiviteById(int idActivite) {
        return activiteRepository.findById(idActivite);
    }
}
