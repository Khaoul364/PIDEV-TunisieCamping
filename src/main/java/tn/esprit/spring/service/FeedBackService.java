package tn.esprit.spring.service;

import tn.esprit.spring.entity.Feedback;

import java.util.List;

public interface FeedBackService {
    List<Feedback> getAllFeedbacks();
    Feedback ajouterFeedBack(Feedback feedback);
    void editFeedback(Feedback feedback);
    void deleteFeedback(int idFeedback);
    Feedback getFeedbackById(int idFeedback);
}
