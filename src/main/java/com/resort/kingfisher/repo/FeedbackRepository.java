package com.resort.kingfisher.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.resort.kingfisher.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
