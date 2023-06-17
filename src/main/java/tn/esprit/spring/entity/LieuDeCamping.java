package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LieuDeCamping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLieu;
    private String nomLieu;
    private String descriptionLieu;
    private String imageLieu;
    private TypeLieu typLieu;
    private int capaciteLieu;

    @OneToMany(mappedBy = "lieuActivite")
    @JsonIgnore
    private Set<Activite> activites;


}
