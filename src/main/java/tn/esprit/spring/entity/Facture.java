package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facture implements Serializable {
    public enum PaimentMode {
        CashOnDelivery, Stripe;
    }

    public enum PaimentStatus {
        PAID, NOT_PAID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFacture;
    private LocalDate date;
    private int totalNumberEquipPurchased;
    private float deleviryPrice;
    private float totale;
    private String adresse;
    PaimentMode modeOfPaiment;
    PaimentStatus paimentStatus;

    @ManyToOne()
    @JsonIgnore

    private Utilisateur utilisateur;

}
