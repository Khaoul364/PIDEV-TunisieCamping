package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.ForumComment;

public interface ForumCmntRepository extends JpaRepository<ForumComment, Integer> {
}
