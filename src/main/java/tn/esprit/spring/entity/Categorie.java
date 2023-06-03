package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;
    private String categorieName;
    private String categorieDescription;

    @OneToMany(mappedBy = "categorie")
    private Set<Equipement> equipements = new HashSet<>();
}
