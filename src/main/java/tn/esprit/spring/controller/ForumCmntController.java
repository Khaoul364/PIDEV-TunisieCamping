package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Service.IForumCmntService;
import tn.esprit.spring.Service.IPostService;
import tn.esprit.spring.entity.ForumComment;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ForumCmntController {
    @Autowired
    IForumCmntService forumCmntService;
    @Autowired
    IPostService postService;
/*    @Autowired
    ForumEmailService emailService;*/

    @PostMapping("/add-comment")
    @ResponseBody
    ForumComment addNewComment(@RequestBody ForumComment forumComment) {
        return forumCmntService.addNewComment(forumComment);
    }

    /*@PostMapping("/add-comment/{postId}")
    @ResponseBody
    public ForumComment addNewCommentToPost(@PathVariable("postId") int postId, @RequestBody ForumComment forumComment) {
        ForumComment newComment = forumCmntService.addNewComment(forumComment);

        // Retrieve the associated post
        Post post = postService.getPostById(postId);

        // Send email notification
        String emailTo = "rym.baazaoui@esprit.tn"; // Set the recipient email address
        String subject = "New Comment Added to Post: " + post.getTitle();
        String content = "A new comment has been added to the post: " + post.getDescription();

        emailService.sendEmail(emailTo, subject, content);

        return newComment;
    }*/

    @PutMapping("/edit-comment")
    @ResponseBody
    ForumComment editComment(@RequestBody ForumComment forumComment) {
        return forumCmntService.editComment(forumComment);
    }


    @DeleteMapping("/delete-comment/{id}")
    @ResponseBody
    void deleteComment(@PathVariable("id") int idComment) {
        forumCmntService.deleteComment(idComment);
    }

    @GetMapping("/all-comments")
    public List<ForumComment> getForums() {
        return forumCmntService.retrieveAll();
    }

    @PostMapping("/assign-comment-to-post/{postId}")
    public ResponseEntity<ForumComment> assignPostToComment(@RequestBody ForumComment comment, @PathVariable("postId") int postId) {
        ForumComment commentToPost = forumCmntService.assignPostToComment(comment, postId);
        if (commentToPost == null) {
            return ResponseEntity.notFound().build();
        }

        // Retrieve the post details based on the postId
        /*Post post = postService.getPostById(postId);

        // Send the email with the post title in the subject
        String emailSubject = "New Comment on Post: " + post.getTitle();
        String emailContent = "A new comment has been added to the post: " + post.getTitle();
        emailService.sendCommentEmail("rym.baazaoui@esprit.tn", emailSubject, emailContent);*/

        return ResponseEntity.ok(commentToPost);
    }

}

