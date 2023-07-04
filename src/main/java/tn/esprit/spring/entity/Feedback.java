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
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFeedback;
    @Temporal(TemporalType.DATE)
    private Date created_date;
    private String description;
    private FeedbackType type;
    @ManyToOne
    private Utilisateur utilisateur;
}
