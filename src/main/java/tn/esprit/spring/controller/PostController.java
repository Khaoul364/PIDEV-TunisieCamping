package tn.esprit.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.Service.IForumCmntService;
import tn.esprit.spring.Service.IPostService;
import tn.esprit.spring.entity.ForumComment;
import tn.esprit.spring.entity.Post;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    IPostService postService;
    IForumCmntService commentService;

    @PostMapping("/add-post")
    public ResponseEntity<Post> addNewPost(@RequestPart("mediaContent") MultipartFile mediaContent,
                                           @RequestPart("post") String postJson) {
        try {
            Post post = new ObjectMapper().readValue(postJson, Post.class);
            if (!mediaContent.isEmpty()) {
                byte[] mediaContentBytes = mediaContent.getBytes();
                post.setMediaContent(mediaContentBytes);
            }
            Post newPost = postService.addNewPost(post);
            return ResponseEntity.ok(newPost);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/edit-post")
    @ResponseBody
    Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }


    @DeleteMapping("/delete-post/{id}")
    @ResponseBody
    void deletePost(@PathVariable("id") int idPost) {
        postService.deletePost(idPost);
    }

    @GetMapping("/all-posts")
    public List<Post> getPosts() {
        return postService.retrieveAll();
    }

 /*   @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<ForumComment>> getPostComments(@PathVariable("postId") int postId) {
        List<ForumComment> comments = commentService.getPostComments(postId);
        return ResponseEntity.ok(comments);
    }*/
    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<ForumComment>> getCommentsByPostId(@PathVariable("postId") int postId) {
        List<ForumComment> comments = commentService.getPostComments(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") int postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }


}

