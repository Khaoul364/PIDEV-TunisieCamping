package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.LieuDeCamping;
import tn.esprit.spring.repository.ActiviteRepository;
import tn.esprit.spring.repository.LieuCampingRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ActiviteService implements IActiviteService{
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    LieuCampingRepository lieuCampingRepository;

    @Override
    public List<Activite> getAllActivite() {
        return activiteRepository.findAll();
    }

    @Override
    public Activite addActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    @Override
    public void editActivite(Activite activite) {
        activiteRepository.save(activite);
    }

    // @Override
    //public Activite editActivite(Activite activite, int idActivite) {
       // Activite act = activiteRepository.findById(idActivite).orElse(null);
       // act.setLieuActivite(activite.getLieuActivite());
       // act.setNomActivite(activite.getNomActivite());
        //act.setImage(activite.getImage());
        //act.setDescription(activite.getDescription());
       // act.setDate_fin(activite.getDate_fin());
       // act.setDate_deb(activite.getDate_deb());
        //act.setPosts(activite.getPosts());
        //act.setReservations(activite.getReservations());

        //return activiteRepository.save(act);
    //}

    @Override
    public void deleteActivite(int idActivite) {
        activiteRepository.deleteById(idActivite);
    }

    @Override
    public Activite getActiviteById(int idActivite) {
        return activiteRepository.getById(idActivite);
    }

    @Override
    public void assignLieuToActivite(Activite activite, int idLieu) {
        LieuDeCamping lieu = lieuCampingRepository.findById(idLieu).orElse(null);
        activite.setLieuActivite(lieu);
        activiteRepository.save(activite);
    }
}
