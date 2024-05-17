package com.ssafy.trip.domain.chatgpt;

import lombok.Data;

public class MessageData {

    @Data
    public static class Message {
        String location;
        String content;
    }
}
