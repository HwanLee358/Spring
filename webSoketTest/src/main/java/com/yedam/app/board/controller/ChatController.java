package com.yedam.app.board.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.board.service.ChatMessage;

@Controller
public class ChatController {
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }
	
	@MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        return chatMessage;
    }
	
	@GetMapping("boardList")
	public String boardList() {
		return "board/boardList";
	}
}
