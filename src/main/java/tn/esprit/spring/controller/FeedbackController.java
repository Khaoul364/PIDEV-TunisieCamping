package tn.esprit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Feedback;
import tn.esprit.spring.service.FeedBackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedBackService feedBackService;

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedBackService.getAllFeedbacks();
    }

    @GetMapping("/{activiteId}")
    public List<Feedback> getAllFeedbacksByActiviteId(@PathVariable int activiteId) {
        return feedBackService.getAllFeedbacksByActivityId(activiteId);
    }

    @PostMapping
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return feedBackService.ajouterFeedBack(feedback);
    }

    @PutMapping
    public void editFeedback(@RequestBody Feedback feedback) {
        feedBackService.editFeedback(feedback);
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteFeedback(@PathVariable int feedbackId) {
        feedBackService.deleteFeedback(feedbackId);
    }

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable int feedbackId) {
        return feedBackService.getFeedbackById(feedbackId);
    }

}
