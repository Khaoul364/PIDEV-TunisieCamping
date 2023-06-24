package tn.esprit.spring.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tn.esprit.spring.entity.Activite;
import tn.esprit.spring.entity.ForumComment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.ForumCmntRepository;
import tn.esprit.spring.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@AllArgsConstructor
@Slf4j
public class ForumCmntServiceImpl implements IForumCmntService{

    ForumCmntRepository forumCmntRepository;
    PostRepository postRepository;
    @Override
    public ForumComment addNewComment(ForumComment comment) {
        return forumCmntRepository.save(comment);
    }

 /*       @Override
    public void assignCommentToPost(Post post, int idComment) {
        ForumComment comment = forumCmntRepository.findById(idComment).orElse(null);
        post.setComments(comment);
            forumCmntRepository.save(post);
    }*/

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




}
