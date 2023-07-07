package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;

import java.util.Optional;

@Repository

    public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findByName(ERole name);
    }

