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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    private byte[] mediaContent;



    @ManyToOne()
    private Activite activite;

    @OneToMany(mappedBy = "post")
    private Set<ForumComment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Utilisateur> users = new HashSet<>();



}
