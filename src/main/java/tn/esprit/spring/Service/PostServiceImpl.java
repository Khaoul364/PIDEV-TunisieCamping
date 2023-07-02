package tn.esprit.spring.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.PostRepository;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post addNewPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        Post post1 = postRepository.findById(post.getIdPost()).orElse(null);
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        post1.setDate(post.getDate());
        post1.setComments(post.getComments());
        post1.setUsers(post.getUsers());
        post1.setMediaContent(post.getMediaContent());
        post1.setActivite(post.getActivite());
        return postRepository.save(post);
    }


/*    @Override
    public List<Post> listPostParActivite(int idActivite) {
        return null;
    }*/


    @Override
    public void deletePost(int idPost) {
        postRepository.deleteById(idPost);
    }

    @Override
    public List<Post> retrieveAll() {
        return postRepository.findAll();
    }


}
