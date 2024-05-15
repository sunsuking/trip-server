package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentData.Create;
import com.ssafy.trip.domain.comment.dto.CommentData.Detail;
import com.ssafy.trip.domain.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public void save(Create create) {
        commentMapper.save(create);
    }

    @Override
    public List<Detail> list(Long reviewId) {
        return commentMapper.list(reviewId);
    }

    @Override
    public List<Detail> listByUserId(Long userId) {
        return commentMapper.listByUserId(userId);
    }

    @Override
    public void delete(Long commentId) {
        commentMapper.delete(commentId);
    }

    @Override
    public void update() {

    }
}
