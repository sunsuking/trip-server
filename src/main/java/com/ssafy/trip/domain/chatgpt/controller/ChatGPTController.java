package com.ssafy.trip.domain.chatgpt.controller;

import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.chatgpt.MessageData.Message;
import com.ssafy.trip.domain.chatgpt.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/gpt")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/review")
    public SuccessResponse<String> getResponse(@RequestBody Message message) {
        return SuccessResponse.of("임시 응답 + " + message.getLocation() + "위치 :  " + message.getContent());
//        return SuccessResponse.of(chatGPTService.getResponse(message));
    }
}
