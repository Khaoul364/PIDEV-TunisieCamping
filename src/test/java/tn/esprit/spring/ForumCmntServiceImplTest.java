package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.Service.ForumCmntServiceImpl;
import tn.esprit.spring.entity.ForumComment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.ForumCmntRepository;
import tn.esprit.spring.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ForumCmntServiceImplTest {

    @Mock
    private ForumCmntRepository forumCmntRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private ForumCmntServiceImpl forumCmntService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewComment_shouldReturnSavedComment() {
        // Create a sample comment
        ForumComment comment = new ForumComment();
        comment.setContent("Test Comment");

        // Mock the forum comment repository behavior
        when(forumCmntRepository.save(comment)).thenReturn(comment);

        // Call the service method
        ForumComment savedComment = forumCmntService.addNewComment(comment);

        // Assertions
        assertNotNull(savedComment);
        assertEquals("Test Comment", savedComment.getContent());

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).save(comment);
    }

    @Test
    void editComment_shouldReturnUpdatedComment() {
        // Create a sample comment
        ForumComment comment = new ForumComment();
        comment.setIdComment(1);
        comment.setContent("Test Comment");

        // Mock the forum comment repository behavior
        when(forumCmntRepository.findById(1)).thenReturn(Optional.of(comment));
        when(forumCmntRepository.save(comment)).thenReturn(comment);

        // Call the service method
        ForumComment updatedComment = forumCmntService.editComment(comment);

        // Assertions
        assertNotNull(updatedComment);
        assertEquals(1, updatedComment.getIdComment());
        assertEquals("Test Comment", updatedComment.getContent());

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).findById(1);
        verify(forumCmntRepository, times(1)).save(comment);
    }

    @Test
    void deleteComment_shouldDeleteComment() {
        // Call the service method
        forumCmntService.deleteComment(1);

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).deleteById(1);
    }

    @Test
    void retrieveAll_shouldReturnAllComments() {
        // Create a list of sample comments
        List<ForumComment> comments = new ArrayList<>();
        comments.add(new ForumComment(1, "Comment 1"));
        comments.add(new ForumComment(2, "Comment 2"));

        // Mock the forum comment repository behavior
        when(forumCmntRepository.findAll()).thenReturn(comments);

        // Call the service method
        List<ForumComment> retrievedComments = forumCmntService.retrieveAll();

        // Assertions
        assertNotNull(retrievedComments);
        assertEquals(2, retrievedComments.size());

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).findAll();
    }

    @Test
    void assignPostToComment_existingPost_shouldReturnSavedComment() {
        // Create a sample comment
        ForumComment comment = new ForumComment();
        comment.setContent("Test Comment");

        // Create a sample post
        Post post = new Post();
        post.setPostId(1);

        // Mock the post repository behavior
        when(postRepository.findById(1)).thenReturn(Optional.of(post));

        // Mock the forum comment repository behavior
        when(forumCmntRepository.save(comment)).thenReturn(comment);

        // Call the service method
        ForumComment savedComment = forumCmntService.assignPostToComment(comment, 1);

        // Assertions
        assertNotNull(savedComment);
        assertEquals(post, savedComment.getPost());

        // Verify the interaction with the post repository
        verify(postRepository, times(1)).findById(1);

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).save(comment);
    }

    @Test
    void getPostComments_existingPostId_shouldReturnComments() {
        // Create a sample post ID
        int postId = 1;

        // Create a list of sample comments
        List<ForumComment> comments = new ArrayList<>();
        comments.add(new ForumComment(1, "Comment 1"));
        comments.add(new ForumComment(2, "Comment 2"));

        // Mock the forum comment repository behavior
        when(forumCmntRepository.findByPostPostId(postId)).thenReturn(comments);

        // Call the service method
        List<ForumComment> retrievedComments = forumCmntService.getPostComments(postId);

        // Assertions
        assertNotNull(retrievedComments);
        assertEquals(2, retrievedComments.size());

        // Verify the interaction with the forum comment repository
        verify(forumCmntRepository, times(1)).findByPostPostId(postId);
    }

}
