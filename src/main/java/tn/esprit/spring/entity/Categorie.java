package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long idCategorie;
    private String categorieName;
    private String categorieDescription;

    @OneToMany(cascade = CascadeType. ALL ,mappedBy = "categorie")
    @JsonIgnore
    private Set<Equipement> equipements = new HashSet<>();
}
