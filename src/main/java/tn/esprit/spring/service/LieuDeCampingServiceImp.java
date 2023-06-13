package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.LieuDeCamping;
import tn.esprit.spring.repository.LieuDeCampingRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LieuDeCampingServiceImp  implements ILieuDeCampingService{
    @Autowired
    LieuDeCampingRepository lr;

    @Override
    public LieuDeCamping addLieu(LieuDeCamping lieu) {
        if (lieu.getNomLieu() == null || lieu.getNomLieu().isEmpty()) {
            throw new IllegalArgumentException("Nom du lieu obligatoire");
        }
        try {
            return lr.save(lieu);
        } catch (Exception e) {
            throw new RuntimeException("erreur d ajouter ce lieu", e);
        }
    }

    @Override
    public LieuDeCamping editLieu(LieuDeCamping lieu, int idLieu) {
        LieuDeCamping l = lr.findById(idLieu).orElse(null);
        l.setCapaciteLieu(lieu.getCapaciteLieu());
        l.setDescriptionLieu(lieu.getDescriptionLieu());
        l.setNomLieu(lieu.getNomLieu());
        l.setImageLieu(lieu.getImageLieu());
        l.setTypLieu(lieu.getTypLieu());
        l.setActivites(lieu.getActivites());

return lr.save(l);
    }

    @Override
    public void deleteLieu(int idlieu) {
        lr.deleteById(idlieu);
    }

    @Override
    public List<LieuDeCamping> getAllLieu() {
        return lr.findAll();
    }

    @Override
    public Optional<LieuDeCamping> getLieuById(int idLieu) {
        return lr.findById(idLieu);
    }
}
