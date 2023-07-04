package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
