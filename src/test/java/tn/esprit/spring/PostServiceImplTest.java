package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.Service.IPostService;
import tn.esprit.spring.Service.PostServiceImpl;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewPost_shouldReturnSavedPost() {
        // Create a sample post
        Post post = new Post();
        post.setTitle("Test Post");
        post.setDescription("This is a test post");

        // Mock the post repository behavior
        when(postRepository.save(post)).thenReturn(post);

        // Call the service method
        Post savedPost = postService.addNewPost(post);

        // Assertions
        assertNotNull(savedPost);
        assertEquals("Test Post", savedPost.getTitle());
        assertEquals("This is a test post", savedPost.getDescription());

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void updatePost_shouldReturnUpdatedPost() {
        // Create a sample post
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Test Post");
        post.setDescription("This is a test post");

        // Mock the post repository behavior
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);

        // Call the service method
        Post updatedPost = postService.updatePost(post);

        // Assertions
        assertNotNull(updatedPost);
        assertEquals(1, updatedPost.getPostId());
        assertEquals("Test Post", updatedPost.getTitle());
        assertEquals("This is a test post", updatedPost.getDescription());

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).findById(1);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void deletePost_shouldDeletePost() {
        // Call the service method
        postService.deletePost(1);

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).deleteById(1);
    }

    @Test
    void retrieveAll_shouldReturnAllPosts() {
        // Create a list of sample posts
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "Post 1", "Description 1"));
        posts.add(new Post(2, "Post 2", "Description 2"));

        // Mock the post repository behavior
        when(postRepository.findAll()).thenReturn(posts);

        // Call the service method
        List<Post> retrievedPosts = postService.retrieveAll();

        // Assertions
        assertNotNull(retrievedPosts);
        assertEquals(2, retrievedPosts.size());

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void getPostById_existingId_shouldReturnPost() {
        // Create a sample post
        Post post = new Post(1, "Test Post", "This is a test post");

        // Mock the post repository behavior
        when(postRepository.findById(1)).thenReturn(Optional.of(post));

        // Call the service method
        Post retrievedPost = postService.getPostById(1);

        // Assertions
        assertNotNull(retrievedPost);
        assertEquals(1, retrievedPost.getPostId());
        assertEquals("Test Post", retrievedPost.getTitle());
        assertEquals("This is a test post", retrievedPost.getDescription());

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).findById(1);
    }

    @Test
    void getPostById_nonExistingId_shouldThrowNoSuchElementException() {
        // Mock the post repository behavior
        when(postRepository.findById(1)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> postService.getPostById(1));

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).findById(1);
    }
}

