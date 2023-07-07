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
    private int idActivite;
    private String nomActivite;
    private String description;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date date_deb;
    @Temporal(TemporalType.DATE)
    private Date date_fin;

    @OneToMany(mappedBy = "activite")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "activite")
    private Set<Post> posts = new HashSet<>();

    @ManyToOne
    private LieuDeCamping lieuActivite;

/*    @ManyToMany(mappedBy = "activites")
    private Set<User> utilisateurs = new HashSet<>();*/

}
