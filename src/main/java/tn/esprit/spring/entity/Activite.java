package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idActivite;
    String nomActivite;
    String description;
    String image;
    @Temporal(TemporalType.DATE)
    Date date_deb;
    @Temporal(TemporalType.DATE)
    Date date_fin;

    @OneToMany(mappedBy = "activite")
    Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "activite")
    Set<Post> posts = new HashSet<>();

    @ManyToOne
    LieuDeCamping lieuActivite;

    @ManyToMany(mappedBy = "activites")
    Set<Utilisateur> utilisateurs = new HashSet<>();

}
