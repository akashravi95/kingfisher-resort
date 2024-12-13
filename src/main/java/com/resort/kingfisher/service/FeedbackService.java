package com.resort.kingfisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resort.kingfisher.model.Feedback;
import com.resort.kingfisher.repo.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Create a new Feedback
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Retrieve all Feedbacks
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // Retrieve a Feedback by ID
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    // Update an existing Feedback
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        // Check if the Feedback with the given ID exists
        if (feedbackRepository.existsById(id)) {
            updatedFeedback.setId(id); // Ensure the ID is set to the correct value
            return feedbackRepository.save(updatedFeedback);
        } else {
            throw new IllegalArgumentException("Feedback with ID " + id + " does not exist.");
        }
    }

    // Delete a Feedback by ID
    public void deleteFeedback(Long id) {
        // Check if the Feedback with the given ID exists
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Feedback with ID " + id + " does not exist.");
        }
    }
}
