package tn.esprit.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Service.IPostService;
import tn.esprit.spring.entity.Post;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("postcontroller")
public class PostController {

    @Autowired
    IPostService postService;


   @PostMapping("/addPost")
	@ResponseBody
    Post addNewPost(@RequestBody Post post) {
       return postService.addNewPost(post);
   }

   	@PutMapping("/editPost/{idPost}")
	@ResponseBody
	Post updatePost(@RequestBody Post post, @PathVariable ("idPost") int idPost) {
	return postService.updatePost(post, idPost);
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



}

