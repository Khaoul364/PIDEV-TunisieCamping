package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Service.IForumCmntService;
import tn.esprit.spring.entity.ForumComment;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ForumCmntController {
    @Autowired
    IForumCmntService forumCmntService;


    @PostMapping("/addComment")
    @ResponseBody
    ForumComment addNewComment(@RequestBody ForumComment forumComment) {
        return forumCmntService.addNewComment(forumComment);
    }

    @PutMapping("/editComment")
    @ResponseBody
    ForumComment editComment(@RequestBody ForumComment forumComment) {
        return forumCmntService.editComment(forumComment);
    }


    @DeleteMapping("/deleteComment/{id}")
    @ResponseBody
    void deleteComment(@PathVariable("id") int idComment) {
        forumCmntService.deleteComment(idComment);
    }

    @GetMapping("/all-comments")
    public List<ForumComment> getForums() {
        return forumCmntService.retrieveAll();
    }

    @PostMapping("/assignCommentToPost/{postId}")
    public ResponseEntity<ForumComment> assignPostToComment(@RequestBody ForumComment comment, @PathVariable("postId") int postId) {
        ForumComment commentToPost = forumCmntService.assignPostToComment(comment, postId);
        if (commentToPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(commentToPost);
    }


}

