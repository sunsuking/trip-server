package com.ssafy.trip.domain.chatbot.service;

import groovy.util.logging.Slf4j;
import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.dto.Choice;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService{

    private final ChatgptService chatgptService;

    @Override
    public String getChatbotResponse(String userMessage) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setPrompt("너는 여행 후기를 작성하는 것을 도와주는 트립봇이야.\n" +
                "\n" +
                "설명: 여행 관련된 후기 글 작성을 도와주는 트립봇\n" +
                "\n" +
                "지침:\n" +
                "1. 위치에 대한 정보를 토대로 후기를 작성하는 것을 도와줘.\n" +
                "2. 기존에 있던 설명을 참고해서 후기를 작성해줘. 만약 설명이 비어있다면 참고해서 작성해줘.\n" +
                "3. 만약 기존에 설명이 작성되어 있다면 그 말투를 참고해서 자연스럽게 이어서 작성해줘.\n" +
                "4. 너무 딱딱한 말투가 아닌 자연스러운 말투로 작성해줘.\n" +
                "5. 응답 데이터는 작성한 후기만 응답 데이터 폼에 담아서 결과로 보내줘.\n" +
                "\n" +
                "응답 데이터 폼:\n" +
                "추천 후기: ${작성한 후기}\n" +
                "\n" +
                "예시:\n" +
                "위치: 남산공원(서울)\n" +
                "후기: 여기서 여자친구랑 싸웠습니다. ㅠㅠ\n" +
                "추천 후기: 여기서 여자친구랑 싸웠습니다. ㅠㅠ 남산공원은 정말 아름다운 곳인데, 그날은 기분이 좋지 않아서 그런지 모든 게 안 좋게 느껴졌어요. 그래도 공원 자체는 산책하기 좋고, 전망도 훌륭해서 다음에는 다시 와서 좋은 기억을 만들고 싶어요.\n" +
                "\n" +
                "---\n" +
                "위치 : 사직공원(광주) \n" +
                "후기: ");
        chatRequest.setMaxTokens(1000);
        chatRequest.setModel("gpt-3.5-turbo-instruct-0914");

        ChatResponse chatResponse = chatgptService.sendChatRequest(chatRequest);
        StringBuilder sb = new StringBuilder();
        chatResponse.getChoices().forEach((choice -> sb.append(choice.getText())));
        return sb.toString();
    }
}
