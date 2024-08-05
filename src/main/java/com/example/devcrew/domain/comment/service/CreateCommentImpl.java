package com.example.devcrew.domain.comment.service;

import com.example.devcrew.domain.auth.service.AuthService;
import com.example.devcrew.domain.comment.converter.CommentConverter;
import com.example.devcrew.domain.comment.dto.request.PostCommentRequestDTO;
import com.example.devcrew.domain.comment.entity.Comment;
import com.example.devcrew.domain.comment.repository.CommentRepository;
import com.example.devcrew.domain.feedback.entity.Feedback;
import com.example.devcrew.domain.feedback.repository.FeedbackRepository;
import com.example.devcrew.domain.member.entity.Member;
import com.example.devcrew.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateCommentImpl implements CreateComment{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final FeedbackRepository feedbackRepository;
    private final AuthService authService;


    @Override
    @Transactional
    public Comment createComment(Long feedbackId, Long memberId, PostCommentRequestDTO request) {
        memberId = authService.getLoginUserId();

        Member member = memberRepository.findById(memberId).orElse(null);
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);

        Comment comment = CommentConverter.toComment(request);

        comment.setMember(member);
        comment.setFeedback(feedback);

        return commentRepository.save(comment);
    }
}
