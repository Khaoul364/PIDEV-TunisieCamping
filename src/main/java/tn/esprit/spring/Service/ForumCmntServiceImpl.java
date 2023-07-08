package tn.esprit.spring.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ForumComment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.ForumCmntRepository;
import tn.esprit.spring.repository.PostRepository;

import java.util.List;

@Service
@Configuration
@AllArgsConstructor
@Slf4j
public class ForumCmntServiceImpl implements IForumCmntService {

    ForumCmntRepository forumCmntRepository;
    PostRepository postRepository;

    @Override
    public ForumComment addNewComment(ForumComment comment) {
        return forumCmntRepository.save(comment);
    }

    @Override
    public ForumComment editComment(ForumComment comment) {

        ForumComment comment1 = forumCmntRepository.findById(comment.getIdComment()).orElse(null);
        comment1.setPost(comment.getPost());
        comment1.setContent(comment.getContent());
        comment1.setDatePosted(comment.getDatePosted());
        comment1.setUsers(comment.getUsers());
        return forumCmntRepository.save(comment);
    }

    @Override
    public void deleteComment(int idComment) {
        forumCmntRepository.deleteById(idComment);
    }

    @Override
    public List<ForumComment> retrieveAll() {
        return forumCmntRepository.findAll();
    }

    @Override
    public ForumComment assignPostToComment(ForumComment comment, int idPost) {
        Post post = postRepository.findById(idPost).orElse(null);
        if (post != null) {
            comment.setPost(post); // Set the post in the comment
            post.getComments().add(comment); // Add the comment to the post's comments
            return forumCmntRepository.save(comment);
        }
        return null;
    }
    @Override
    public List<ForumComment> getPostComments(int postId) {
        return forumCmntRepository.findByPostPostId(postId);
    }

}
