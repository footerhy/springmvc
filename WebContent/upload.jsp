<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Upload the file</title>
</head>
<body>
	<%=request.getContextPath() + "x" %>
	<form method="post" action="<%=request.getContextPath() %>/upload" enctype="multipart/form-data">
		<input type="text" name="xheadx"><br>
		<input type="file" name="file1"><br>
		<input type="submit" value="确定">
	</form>
</body>
</html>