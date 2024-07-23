package com.yedam.app.board.service;


import lombok.Data;

@Data
public class ChatMessage {
	private MessageType type;
    private String content;
    private String sender;
}

enum MessageType {
    CHAT,
    JOIN,
    LEAVE
}