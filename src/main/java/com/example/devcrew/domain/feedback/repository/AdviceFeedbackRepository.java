package com.example.devcrew.domain.feedback.repository;

import com.example.devcrew.domain.feedback.entity.AdviceFeedback;
import com.example.devcrew.domain.feedback.entity.FeedbackTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceFeedbackRepository extends JpaRepository<AdviceFeedback, Long> {
    Page<AdviceFeedback> findByFeedbackTag(FeedbackTag feedbackTag, PageRequest pageRequest);

    Page<AdviceFeedback> findAll(Pageable pageable);
}
