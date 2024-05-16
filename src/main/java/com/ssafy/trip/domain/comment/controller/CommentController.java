package com.ssafy.trip.domain.comment.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.comment.dto.CommentData;
import com.ssafy.trip.domain.comment.dto.CommentData.Create;
import com.ssafy.trip.domain.comment.dto.CommentData.Detail;
import com.ssafy.trip.domain.comment.dto.CommentData.SimpleDetail;
import com.ssafy.trip.domain.comment.entity.Comment;
import com.ssafy.trip.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{reviewId}")
    public SuccessResponse<List<Detail>> list(@PathVariable("reviewId") Long reviewId) {
        return SuccessResponse.of(commentService.list(reviewId));
    }

    @GetMapping("/user/{userId}")
    public SuccessResponse<List<Detail>> listByUserId(@PathVariable("userId") Long userId) {
        return SuccessResponse.of(commentService.listByUserId(userId));
    }

    @GetMapping("/simple/{userId}")
    public SuccessResponse<List<SimpleDetail>> simpleCommentListByUserId(@PathVariable("userId") Long userId) {
        return SuccessResponse.of(commentService.simpleCommentListByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<Void> create(@RequestBody Create create){
        log.debug("{}", create);
        commentService.save(create);
        return SuccessResponse.empty();
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse<Void> delete(@PathVariable("commentId") Long commentId){
        return SuccessResponse.empty();
    }

    @PutMapping("/{commentId}")
    public SuccessResponse<Void> update(@PathVariable("reviewId") Long reviewId, @PathVariable("commentId") Long commentId) {
        return SuccessResponse.empty();
    }
}
