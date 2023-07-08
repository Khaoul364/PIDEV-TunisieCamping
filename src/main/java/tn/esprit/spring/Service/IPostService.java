package tn.esprit.spring.Service;

import org.w3c.dom.html.HTMLInputElement;
import tn.esprit.spring.entity.Post;

import java.util.List;
import java.util.NoSuchElementException;

public interface IPostService {

    public Post addNewPost(Post post);
//    public Post findPostByActivityName (Post post, String name);
//    List<Post> listPostParActivite(int idActivite);


    public Post updatePost(Post post);

    public void deletePost(int idPost);

    public List<Post> retrieveAll();

    public Post getPostById(int postId);


}
