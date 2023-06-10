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

public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPanier;
    float totalPrice;
    private int numberOfEquipPurchased;
    float discountedAmount;
    private float finalPrice;

    @ManyToMany()
    @JsonIgnore
    private Set<Equipement> equipements = new HashSet<>();

    @OneToOne
    @JsonIgnore
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PanierEquipement> panierEquipements;
}
