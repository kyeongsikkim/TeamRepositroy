<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name=viewport
	content="width=device-width initial-scale=1 user-scalable=no">
<!-- CSS -->
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/clock.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/rssfeed.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/schedule.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/weather.css"
	rel="stylesheet" type="text/css" />
<!-- JS -->
<script type="text/javascript"
	src="http://code.responsivevoice.org/responsivevoice.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/js/annyang.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/startannyang.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/responsivevoice.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/checkscript.js"
	type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/js/clock.js"
	type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/js/rssfeed.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/controller.js"
	type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/js/weather.js"
	type="text/javascript"></script>
<script src="https://rawgithub.com/darkskyapp/skycons/master/skycons.js"></script>
<!-- Rss&Weather execute -->
<script>
	requestNews();
	setInterval(requestNews, 600000);
/* 	setInterval(refresh, 100000);
	setInterval(drawIcon, 100000); */ 
	$(function() {
		weatherDefault();
	});
</script>
<!-- Style -->
<style>
body {
	background-color: black;
	overflow: hidden;
}
/*.col-md-4 {
				border:1px solid white;
			}
			.col-md-3 {
				border:1px solid white;
			}
			.col-md-5 {
			 	border:1px solid white;
			}*/
</style>
</head>
<body>
	<div>
		<div class="row" style="margin-top: 2vh;">
			<div class="col-md-4">
				<div id="weather">
					<p id="weatherLocation">Seoul</p>
					<div id="currentWeather">
						<span>
							<canvas id="icon" width="40px" height="40px">		
			                	</canvas>
						</span> <span id="temperature"></span>
						<div id="summary"></div>
					</div>
				</div>
				<br />
				<p id="weatherHeader">Weekly Weather</p>
				<div id="weekdaysWeather">
					<div>
						<span id="weekday1"></span>
						<span><canvas id="iconW1"
								width="40px" height="40px"></canvas></span> 
						
						<span id="tempMax1"> </span><span id="tempMin1"></span>
						
					</div>
					<div>
						<span id="weekday2"></span> 
						<span><canvas id="iconW2"
								width="40px" height="40px"></canvas></span> 
						
						 <span id="tempMax2"> </span> <span id="tempMin2"></span>
						
					</div>
					<div>
						<span id="weekday3"></span> <span><canvas id="iconW3"
								width="40px" height="40px"></canvas></span> <span> <span
							id="tempMax3"> </span> <span id="tempMin3"></span>
						</span>
					</div>
					<div>
						<span id="weekday4"></span> <span><canvas id="iconW4"
								width="40px" height="40px"></canvas></span> <span> <span
							id="tempMax4"></span> <span id="tempMin4"></span>
						</span>
					</div>
					<div>
						<span id="weekday5"></span> <span><canvas id="iconW5"
								width="40px" height="40px"></canvas></span> <span> <span
							id="tempMax5"></span> <span id="tempMin5"></span>
						</span>
					</div>
					<div>
						<span id="weekday6"></span> <span><canvas id="iconW6"
								width="40px" height="40px"></canvas></span> <span> <span
							id="tempMax6"></span> <span id="tempMin6"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
			<div class="col-md-5">
				<!-- clock -->
				<div class="clock">
					<div id="Date"></div>
					<ul class="clockul">
						<li id="hours" class="clockli"></li>
						<li id="point" class="clockli">:</li>
						<li id="min" class="clockli"></li>
						<li id="sec" class="clocklis"></li>
					</ul>
				</div>
				<!-- rssfeed -->
				<div id="rssheader">
					<img
						src="<%=application.getContextPath()%>/resources/images/newspaper.png"
						style="display: inline; width: 2vw; height: 2vw; margin: 0.3vw 0.3vw 0.6vw 0.3vw;">Today's
					Headline
				</div>
				<div id="rssfeed"></div>
				<!-- schedule -->
				<div id="scheduleheader">
					<img
						src="<%=application.getContextPath()%>/resources/images/calendar.png"
						style="display: inline; width: 2vw; height: 2vw; margin: 0.3vw 0.3vw 0.6vw 0.3vw;">Schedule
				</div>
				<div id="schedule"></div>
			</div>
		</div>

		<div id="content"></div>
	</div>
</body>
</html>