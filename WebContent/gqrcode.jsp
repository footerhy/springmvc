<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script>
</head>
<body>
	<!-- http://jindo.dev.naver.com/collie -->
	<input id="text" type="text" value="http://www.baidu.com"
		style="width: 80%" />
	<br />
	<div id="qrcode" style="width: 100px; height: 100px; margin-top: 15px;"></div>

	<script type="text/javascript">
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 100,
			height : 100
		});

		function makeCode() {
			var elText = document.getElementById("text");

			if (!elText.value) {
				alert("Input a text");
				elText.focus();
				return;
			}

			qrcode.makeCode(elText.value);
		}

		makeCode();

		$("#text").on("blur", function() {
			makeCode();
		}).on("keydown", function(e) {
			if (e.keyCode == 13) {
				makeCode();
			}
		});
	</script>
</body>
</html>