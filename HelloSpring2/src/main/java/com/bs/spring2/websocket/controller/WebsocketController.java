package com.bs.spring2.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsocketController {

	@RequestMapping("/chatting/connectChatting")
	public String chatting() {
		return "chat/chatting";
	}
	
}
