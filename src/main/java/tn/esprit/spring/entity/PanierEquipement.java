package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString

public class PanierEquipement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Equipement equipement;

    private int quantity;


    public PanierEquipement(Long idEquipment, Long idCart, int quantity) {
    }
}
