package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idReservation;
    int nbrPersonne;
    @Temporal(TemporalType.DATE)
    Date date_deb;
    @Temporal(TemporalType.DATE)
    Date date_fin;
    Double prixTotal;
    String nom;
    int telephone;
    String email;

    @Enumerated(EnumType.STRING)
    Transport transport;

    @ManyToOne
    Activite activite;
    @ManyToOne
    Utilisateur utilisateur;
}
