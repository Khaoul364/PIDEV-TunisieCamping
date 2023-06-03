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
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFacture;
    @Temporal(TemporalType.DATE)
    private Date date;
    private float deleviryPrice;
    private float totale;
    private String adresse;

    @ManyToOne()
    private Utilisateur utilisateur;
    @ManyToOne()
    private Equipement equipement;
}
