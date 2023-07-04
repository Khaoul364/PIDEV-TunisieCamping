package tn.esprit.spring.entity;

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
public class ForumComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date datePosted;
    @ManyToOne()
    private Post post;

    @ManyToMany()
    private Set<Utilisateur> users = new HashSet<>();

}
