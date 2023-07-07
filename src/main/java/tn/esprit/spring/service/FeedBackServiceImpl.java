package tn.esprit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Feedback;
import tn.esprit.spring.repository.FeedbackRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getAllFeedbacksByActivityId(int activityId) {
        return feedbackRepository
                .findAll()
                .stream()
                .filter(f -> f.getActivite().getIdActivite() == activityId)
                .collect(Collectors.toList());
    }

    @Override
    public Feedback ajouterFeedBack(Feedback feedback) {
        return feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void editFeedback(Feedback feedback) {
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void deleteFeedback(int idFeedback) {
        feedbackRepository.deleteById(idFeedback);
    }

    @Override
    public Feedback getFeedbackById(int idFeedback) {
        return feedbackRepository.findById(idFeedback).get();
    }
}
