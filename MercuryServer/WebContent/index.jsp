<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="commonJs/general.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body onload="start()">
Time: <span id="foo"></span>
 
<br><br>

 
<script type="text/javascript">function start(){
	
	var eventSource=new EventSource("ServerEvent");eventSource.onmessage=function(event){
		
		kandylogin=event.data;
		setCookie("kandylogin",event.data,30);
		document.getElementById('foo').innerHTML=event.data;};}</script>
</body>
</html>