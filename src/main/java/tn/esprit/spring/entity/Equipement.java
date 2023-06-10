package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Equipement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEquipement;
    private String equipementName;
    private String description;
    private float equipementPrice;
    private boolean availabilty;
    private int quantityInStock;
    private String marque;
    private boolean promotion;
    private float discount;
    private String equipementImage;
    private boolean featured;
    private int purchaseQuantity;
    @Temporal(TemporalType.DATE)
    private Date dateModification;
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne()
    private Categorie categorie;

    @ManyToMany(mappedBy = "equipements")
    private Set<Panier> paniers = new HashSet<>();

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PanierEquipement> panierEquipements;



}
