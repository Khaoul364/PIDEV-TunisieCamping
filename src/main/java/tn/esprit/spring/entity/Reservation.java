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
    private int idReservation;
    private int nbrPersonne;
    @Temporal(TemporalType.DATE)
    private Date date_deb;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    private Double prixTotal;
    private String nom;
    private int telephone;

    @Enumerated(EnumType.STRING)
    private Transport transport;

    @ManyToOne
    private Activite activite;
    @ManyToOne
    private Utilisateur utilisateur;
}
