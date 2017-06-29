<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>SensingCar</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/dashboard.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/switch.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/togglebuttonR.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/togglebuttonG.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/togglebuttonB.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/mainstatus.js">
	
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/activebuzzer.js">
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/lcd.js">
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/rgb.js">
</script>
<script src="https://code.highcharts.com/highcharts.src.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/gassensor.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/thermistorsensor.js"
	type="text/javascript"></script>
</head>

<body>
	<div>
		<i class="glyphicon glyphicon-dashboard" id="mainIcon"></i>
		<p style="display: inline-flex;">DashBoard(Team4)</p>
	</div>
	<hr />
	<div class="panel panel-default" style="width: 300px;">
		<div class="panel-heading">
			<i class="glyphicon glyphicon-tags"> </i>
			<p style="display: inline;">Notice</p>
		</div>
		<div class="panel-body" id="divDisplay">
			<span onchange="handleText(${text})" id="txtMessage">${text}</span>
		</div>
		<div class="panel-footer"></div>
	</div>
	<div class="m-rocker">
		<span id="buzzer">${status}</span> <input id="switch"
			onclick="handleLabelBuzzer()" type="checkbox"><label
			for="switch"></label>
	</div>
	<br />
	<div id="gasSensorChart"></div>
	<br />
	<div id="thermistorSensorChart"></div>
	<br />
	<div id="trackingSensorChart"></div>
	<br />
	<div class="btnRGB">
		<div class="toggle">
			<span id="colorR">${red}</span> <span class="btnColor">Red</span> <input
				onclick="toggleColor()" class="tgl tgl-red" id="red" type="checkbox" />
			<label class="tgl-btn" for="red"></label>
		</div>
		<div class="toggle">
			<span id="colorG">${green}</span> <span class="btnColor">Green</span>
			<input onclick="toggleColor()" class="tgl tgl-green" id="green"
				type="checkbox" /><label class="tgl-btn" for="green"></label>
		</div>
		<div class="toggle">
			<span id="colorB">${blue}</span> <span class="btnColor">Blue</span> <input
				onclick="toggleColor()" class="tgl tgl-blue" id="blue"
				type="checkbox" /><label class="tgl-btn" for="blue"></label>
		</div>
	</div>
</body>
</html>