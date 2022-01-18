package com.bs.spring2.common.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

// 웹소켓 요청을 처리하는 클래스로 등록하려면 springwebsocket에서 제공하는 handler를 상속받아야한다.
// TextWebSocketHandler클래스
// TextWebSocketHandler가 가지고 있는 메소드를 구현하면 된다.
@Slf4j
public class ChattingServer extends TextWebSocketHandler {
	
	private List<WebSocketSession> clients = new ArrayList();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// FRONT에서 websocket 객체를 생성했을 때 바로 실행이 되는 부분
		log.debug("{}", session.getId());
//		session.sendMessage(null); -> 채팅방 개수, 입장 인원 수 등등
		clients.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.debug(message.getPayload()); // session이 보낸 데이터 출력
		session.sendMessage(message);
		for(WebSocketSession client : clients) {
			if(client.isOpen()) {
				client.sendMessage(message);
			} /*clients.remove(client);*/
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}

}
