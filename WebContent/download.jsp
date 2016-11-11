<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>download file</title>
<script type="text/javascript">
	function mydownload(){
		document.getElementById("dlform").submit();
	}
</script>
</head>
<body>
	<form id="dlform" action="<%=request.getContextPath()%>/download"></form>
	<input type="button" value="下载" onclick="mydownload()">
</body>
</html>