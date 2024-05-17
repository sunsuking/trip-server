package com.ssafy.trip.domain.chatbot.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.chatbot.MessageData;
import com.ssafy.trip.domain.chatbot.MessageData.Message;
import com.ssafy.trip.domain.chatbot.service.ChatbotService;
import com.ssafy.trip.domain.review.dto.ReviewData;
import groovy.util.logging.Slf4j;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private static final Logger log = LoggerFactory.getLogger(ChatbotController.class);
    private final ChatbotService chatbotService;

    @PostMapping("/question")
    public SuccessResponse<String> getResponse(@RequestBody Message message){
        log.debug("message : {}",message.getMessage());
//        return SuccessResponse.of("test 응답");
        return SuccessResponse.of(chatbotService.getChatbotResponse(message.getMessage()));
    }
}
