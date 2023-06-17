package tn.esprit.spring.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ForumComment;
import tn.esprit.spring.repository.ForumCmntRepository;
import tn.esprit.spring.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ForumCmntServiceImpl implements IForumCmntService{

    ForumCmntRepository forumCmntRepository;
    PostRepository postRepository;
    @Override
    public ForumComment addNewComment(ForumComment comment) {
        return forumCmntRepository.save(comment);
    }

/*    @Override
    public void assignCommentToPost(int idComment, int idPost) {

    }*/

    @Override
    public ForumComment editComment(ForumComment comment) {

        ForumComment comment1 = forumCmntRepository.findById(comment.getIdComment()).orElse(null);
        comment1.setPost(comment.getPost());
        comment1.setContent(comment.getContent());
        comment1.setMediaComment(comment.getMediaComment());
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
