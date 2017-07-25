<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name=viewport content="width=device-width initial-scale=1 user-scalable=no">
		<!-- Bootstrap & Jquery -->
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- STT & TTS -->
		<script src="<%=application.getContextPath()%>/resources/js/annyang.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/startannyang.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/responsivevoice.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/checkscript.js"></script>
		<script type="text/javascript" src="http://code.responsivevoice.org/responsivevoice.js"></script>
		<!-- Rss news -->
		<script>
			$(function() {
				$.ajax({
					type : "GET",
					url : "/SmartMirrorProject/news",
					success : function(data) {
						var list = data.titleList;
						var i = 0;
						setInterval(function(){
							$("#rss").html(list[i].title);
							i++;
							if(i == list.length) {
								i = 0;
							}
						}, 3000);
					}
				});
			});
		</script>
		<!-- Clock -->
		<script src="<%=application.getContextPath()%>/resources/js/clock.js"></script>
		<link href="<%=application.getContextPath()%>/resources/css/clock.css" rel="stylesheet" type="text/css" />
		<!--  -->
		<script src="<%=application.getContextPath()%>/resources/js/controller.js"></script>
		<style>
			body {
				background-color: black;
			}
			#rss {
				color: white;
				text-align: right;
			}
		</style>
	</head>
	<body>
		<div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="clock">
					<div id="Date"></div>
					<ul class="clockul">
					    <li id="hours" class="clockli"></li>
					    <li id="point" class="clockli">:</li>
					    <li id="min" class="clockli"></li>
					    <li id="point" class="clockli">:</li>
					    <li id="sec" class="clockli"></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
				<div class="col-md-4"><div id="rss"></div></div>
			</div>
			<div id="content"></div>
		</div>
	</body>
</html>