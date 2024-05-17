package com.ssafy.trip.domain.chatgpt.service;

import com.ssafy.trip.domain.chatgpt.MessageData;

public interface ChatGPTService {

    String getResponse(MessageData.Message message);
}
