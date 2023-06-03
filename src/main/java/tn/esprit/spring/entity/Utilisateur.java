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
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users")
    private Set<ForumComment> comments = new HashSet<>();

    @ManyToOne()
    private Post post;

    @ManyToMany
    private Set<Activite> activites = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Feedback> feedbacks = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    private Set<Facture> Facture = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Reservation> reservations = new HashSet<>();
}
