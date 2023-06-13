package tn.esprit.spring.service;

import tn.esprit.spring.entity.LieuDeCamping;

import java.util.List;
import java.util.Optional;

public interface ILieuDeCampingService {
    public LieuDeCamping addLieu (LieuDeCamping lieu);
    public LieuDeCamping editLieu (LieuDeCamping lieu, int idLieu);
    public void deleteLieu (int idlieu);
    public List<LieuDeCamping> getAllLieu();
    public Optional<LieuDeCamping> getLieuById (int idLieu);

}
