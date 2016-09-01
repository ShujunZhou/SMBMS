<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<style>
* {padding:0px; margin:0px;height:100%; width:100%;}
.content  {padding-top:100px;box-sizing:border-box}
.top {height:100px; width:100%;position:absolute; top:0px;}
.left{ float:left;width:20%;}
.right{ float:left;width:80%;}
#top{height:100px;}
</style>
</head>
<body>
<div class="content">
			<div class="top">
				<iframe src="top.jsp" scrolling="No" id="top" noresize="noresize">
				</iframe>
			</div>
				<div class="main">
					<div class="left">
						<iframe src="left.jsp" scrolling="No" id="left" noresize="noresize">
					  </iframe>
						</div>
					<div class="right">
						<iframe src="main.jsp" id="mainFrame" name="mainFrame" noresize="noresize" >
					  </iframe>
						</div>
				</div>
</div>

</body>
</html>