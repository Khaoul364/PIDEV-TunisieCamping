package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ForumComment;

import java.util.List;

@Repository
public interface ForumCmntRepository extends JpaRepository<ForumComment, Integer> {
    List<ForumComment> findByPostPostId(int postId);
}

