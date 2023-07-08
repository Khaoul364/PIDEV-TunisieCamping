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
public class Post implements Serializable {

    public Post(int postId, String title, String description) {
        this.postId = postId;
        this.title = title;
        this.description = description;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    private byte[] mediaContent;



    @ManyToOne()
    private Activite activite;
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<ForumComment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Utilisateur> users = new HashSet<>();



}
