<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅화면</title>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<input id="msg" type="text"/><button onclick="sendMsg()">전송</button>
	<div id="container">
		
	</div>
	
	<script>
		const socket = new SockJS("http://localhost:9090/${pageContext.request.contextPath}/chatting");
		socket.onopen=e=>{
			console.log(e);
			$("#container").append($("<h3>").html("채팅방에 입장하셨습니다."));
		}
		
		const sendMsg=()=>{
			socket.send(JSON.stringify(){"userId":${loginMember.userId}, "msg":$("#msg").val());
		}

		socket.onmessage=message=>{
			console.log(message);
			console.log(message.timeStamp);
			$("#container").append($("<h4>").html(message.data + "<sub>" + new Date(message.timeStamp) + "</sub>"))
		}
	</script>

</body>
</html>