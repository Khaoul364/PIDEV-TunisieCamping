package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.Service.IPostService;
import tn.esprit.spring.entity.Post;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    IPostService postService;


    @PostMapping("/addPost")
    @ResponseBody
    Post addNewPost(@RequestBody Post post) {
        return postService.addNewPost(post);
    }

    @PutMapping("/editPost")
    @ResponseBody
    Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }


    @DeleteMapping("/deletePost/{id}")
    @ResponseBody
    void deletePost(@PathVariable("id") int idPost) {
        postService.deletePost(idPost);
    }

    @GetMapping("/all-posts")
    public List<Post> getPosts() {
        return postService.retrieveAll();
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> addImageToPost(@PathVariable int postId, @RequestParam("file") MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            // Save the image to a local directory
            String uploadDir = "images/forumsPics";
            String filePath = uploadDir + fileName;
            FileCopyUtils.copy(image.getBytes(), new FileOutputStream(filePath));

            // Update the post with the image file path
            Post post = postService.getPostById(postId);
            if (post != null) {
                post.setImagePath(filePath);

                // Return a success response
                return ResponseEntity.ok("Image added to post successfully");
            } else {
                // Handle the case where the post with the given ID doesn't exist
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // Handle the exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save image");
        }
    }
}

