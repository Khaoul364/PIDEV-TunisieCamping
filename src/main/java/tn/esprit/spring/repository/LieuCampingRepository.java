package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.LieuDeCamping;

public interface LieuCampingRepository extends JpaRepository<LieuDeCamping, Integer> {
}
