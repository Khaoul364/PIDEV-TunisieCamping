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

public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPanier;
    private int counter;
    private float totalPrice;
    private boolean delivery;

    @ManyToMany()
    private Set<Equipement> equipements = new HashSet<>();
}
