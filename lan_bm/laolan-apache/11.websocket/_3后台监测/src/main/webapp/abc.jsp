<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	var  ws;
	var url="ws://localhost:8080/echo/echoSocket";
	function connect(){
		
		 if ('WebSocket' in window) {
             ws = new WebSocket(url);
         } else if ('MozWebSocket' in window) {
             ws = new MozWebSocket(url);
         } else {
             alert('WebSocket is not supported by this browser.');
             return;
         }
		 
		 ws.onmessage=function(event){
			console.debug(event);	
			$("#content").append(event.data+"<br/>");
		 };
		
	}
	
	function  send(){
		var value= $("#msg").val();
		ws.send(value);
	}


</script>
</head>
<body>

	<button  onclick="connect();">connect</button>
	<hr/>
	<input id="msg"  /><button  onclick="send();"  >send</button>
	<div  id="content"  style="border: 1px solid black; width: 200px; height: 300px;" ></div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="abc.js"></script>
</body>
</html>