var sensorChart;
var ws;

function showThermistorSensorChart(sensor) {
	sensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"chartContainer",
			type:"line",
			events: {
				load: requestThermistorSensorData
			}
		},
		colors: ['red'],
		title: {
			text: "ThermistorSensor"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			maxZoom: 20*1000
		},
		yAxis: {
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "temperature",
				margin: 30
			}
		},
		series: [{
			name: "temperature",
			data: []
		}]
	});
	
	$("#hoverTemperature").css("box-shadow", "5px 5px 10px gray inset");
	$("#hoverDistance").css("box-shadow","none");
	$("#hoverPhoto").css("box-shadow","none");
	$("#hoverGas").css("box-shadow","none");
	$("#hoverTracking").css("box-shadow","none");
}
function showGasSensorChart() {
	sensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"chartContainer",
			type:"line",
			events: {
				load: requestGasSensorData
			}
		},
		colors: ['green'],
		title: {
			text: "GasSensor"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "gas",
				margin: 30
			}
		},
		series: [{
			name: "gas",
			data: []
		}]
	});
	
	$("#hoverTemperature").css("box-shadow","none");
	$("#hoverDistance").css("box-shadow","none");
	$("#hoverPhoto").css("box-shadow","none");
	$("#hoverGas").css("box-shadow", "5px 5px 10px gray inset");
	$("#hoverTracking").css("box-shadow","none");
}

function showPhotoresistorSensorChart() {
	sensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"chartContainer",
			type:"line",
			events: {
				load: requestPhotoresistorSensorData
			}
		},
		colors: ['yellow'],
		title: {
			text: "PhotoresistorSensor"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "brightness",
				margin: 30
			}
		},
		series: [{
			name: "photoresistor",
			data: []
		}]
	});
	
	$("#hoverTemperature").css("box-shadow","none");
	$("#hoverDistance").css("box-shadow","none");
	$("#hoverPhoto").css("box-shadow", "5px 5px 10px gray inset");
	$("#hoverGas").css("box-shadow","none");
	$("#hoverTracking").css("box-shadow","none");
}

function showUltrasonicSensorChart() {
	sensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"chartContainer",
			type:"line",
			events: {
				load: requestUltrasonicSensorData
			}
		},
		colors: ['purple'],
		title: {
			text: "UltrasonicSensor"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			maxZoom: 20*1000
		},
		yAxis: {
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "distance",
				margin: 30
			}
		},
		series: [{
			name: "distance",
			data: []
		}]
	});
	
	$("#hoverTemperature").css("box-shadow","none");
	$("#hoverDistance").css("box-shadow","5px 5px 10px gray inset");
	$("#hoverPhoto").css("box-shadow", "none");
	$("#hoverGas").css("box-shadow","none");
	$("#hoverTracking").css("box-shadow","none");
}

function showTrackingSensorChart() {
	sensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"chartContainer",
			type:"line",
			events: {
				load: requestTrackingSensorData
			}
		},
		colors: ['black'],
		title: {
			text: "TrackingSensor"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			maxZoom: 20*1000
		},
		yAxis: {
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "tracking",
				margin: 30
			}
		},
		series: [{
			name: "tracking",
			data: []
		}]
	});
	
	$("#hoverTemperature").css("box-shadow","none");
	$("#hoverDistance").css("box-shadow","none");
	$("#hoverPhoto").css("box-shadow", "none");
	$("#hoverGas").css("box-shadow","none");
	$("#hoverTracking").css("box-shadow","5px 5px 10px gray inset");
}

function showClearSensorChart() {
	sensorChart = null;
	if(ws != null) {
		ws.close();
		ws = null;
	}
	$("#chartContainer").html("<br/><br/>Please put your mouse<br/>To the data that you want to see in the chart.");

	$("#hoverTemperature").css("box-shadow","none");
	$("#hoverDistance").css("box-shadow","none");
	$("#hoverPhoto").css("box-shadow", "none");
	$("#hoverGas").css("box-shadow","none");
	$("#hoverTracking").css("box-shadow","none");
}

function requestThermistorSensorData() {
	if(ws != null) {
		ws.close();
		ws = null;
	}
	ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/thermistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = sensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.temperature], true, shift);
	};
}

function requestGasSensorData() {
	if(ws != null) {
		ws.close();
		ws = null;
	}
	ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/gassensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = sensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.gas], true, shift);
	};
}

function requestPhotoresistorSensorData() {
	if(ws != null) {
		ws.close();
		ws = null;
	}
	ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/photoresistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = sensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.photoresistor], true, shift);
	};
}

function requestUltrasonicSensorData() {
	if(ws != null) {
		ws.close();
		ws = null;
	}
	ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/ultrasonicsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = sensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.distance], true, shift);
	};
}

function requestTrackingSensorData() {
	if(ws != null) {
		ws.close();
		ws = null;
	}
	ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/trackingsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = sensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.tracking], true, shift);
	};
}