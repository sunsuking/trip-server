package com.ssafy.trip.domain.chatgpt.service;

import com.ssafy.trip.domain.chatgpt.MessageData;
import groovy.util.logging.Slf4j;
import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatgptService chatgptService;

    @Override
    public String getResponse(MessageData.Message message) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setPrompt(String.format(
                "여행 후기 작성을 도와주세요 위치를 기반으로 한 여행 후기를 작성해야하고 현재 주어진 후기가 있다면 이어서 작성해야하고 존재하지 않는다면 위치에 대한 정보를 담아서 작성해야합니다. \n" +
                "의문문을 사용하지말아주세요. \n" +
                "100글자보다 많고 300글자보다적게 응답해주세요\n" +
                "실제로 존재하는 정보를 토대로 응답해주세요\n" +
                "문자를 완성시켜서 응답해주세요" +
                "위치: %s\n 내용: %s", message.getLocation(), message.getContent()));
        chatRequest.setMaxTokens(500);
        chatRequest.setModel("gpt-3.5-turbo-instruct");
        chatRequest.setTemperature(1.0);
        ChatResponse chatResponse = chatgptService.sendChatRequest(chatRequest);
        return chatResponse.getChoices().get(0).getText();
    }
}
