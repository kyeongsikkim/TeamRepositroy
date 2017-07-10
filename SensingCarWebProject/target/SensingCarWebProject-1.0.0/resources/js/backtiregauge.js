var chartSpeed;

$(function() {
	chartSpeed = Highcharts.chart('container-speed', {

	    chart: {
	        type: 'gauge',
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false
	    },

	    title: {
	        text: null
	    },

	    pane: {
	        startAngle: -150,
	        endAngle: 150,
	        background: [{
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#FFF'],
	                    [1, '#333']
	                ]
	            },
	            borderWidth: 0,
	            outerRadius: '109%'
	        }, {
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#333'],
	                    [1, '#FFF']
	                ]
	            },
	            borderWidth: 1,
	            outerRadius: '107%'
	        }, {
	            // default background
	        }, {
	            backgroundColor: '#FFF',
	            borderWidth: 0,
	            outerRadius: '105%',
	            innerRadius: '103%'
	        }]
	    },

	    // the value axis
	    yAxis: {
	        min: 0,
	        max: 4095,

	        minorTickInterval: 'auto',
	        minorTickWidth: 1,
	        minorTickLength: 10,
	        minorTickPosition: 'inside',
	        minorTickColor: '#666',

	        tickPixelInterval: 30,
	        tickWidth: 2,
	        tickPosition: 'inside',
	        tickLength: 10,
	        tickColor: '#666',
	        labels: {
	            step: 2,
	            rotation: 'auto'
	        },
	        title: {
	            text: 'step'
	        },
	        plotBands: [{
	            from: 0,
	            to: 1250,
	            color: '#55BF3B' // green
	        }, {
	            from: 1250,
	            to: 3000,
	            color: '#DDDF0D' // yellow
	        }, {
	            from: 3000,
	            to: 4095,
	            color: '#DF5353' // red
	        }]
	    },

	    series: [{
	        name: 'Speed',
	        data: [0],
	        tooltip: {
	            valueSuffix: ' steps'
	        }
	    }]

	});
});

var speedIntervalId;
var direction;
var speed;
var point;

function setSpeedGaugeDefault(defaultspeed) {
	console.log(defaultspeed);
	point = chartSpeed.series[0].points[0];
	point.update(Number(defaultspeed));
}

function stopspeedchange() {
	clearInterval(speedIntervalId);
	speedIntervalId = null;
}

function speedzero(tempdirection) {
	direction = tempdirection;
	speed = 0;
	var json = {"command":"change", "direction":direction, "speed":"0"};
	$.ajax({
		url:"http://" + location.host + "/SensingCarWebProject/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#backtirespeedup").attr("onmousedown","speedup('" + data.direction + "','" + data.speed + "')");
				$("#backtirespeeddown").attr("onmousedown","speeddown('" + data.direction + "','" + data.speed + "')");
				$("#backtirego").attr("onclick","backtireDirection('forward','" + data.speed + "')");
				$("#backtireback").attr("onclick","backtireDirection('backward','" + data.speed + "')");
				point = chartSpeed.series[0].points[0];
				point.update(speed);
			}
		}
	});
}

function speedup(tempdirection, tempspeed) {
	speed = parseInt(tempspeed);
	direction = tempdirection;
	speedIntervalId = setInterval(keepspeedup, 100);
}

function keepspeedup() {
	
	speed = speed + 50;
	console.log(speed);
	
	if(speed > 4095) {
		speed = 4095;
	}
	
	newspeed = speed.toString();
	var json = {"command":"change", "direction":direction, "speed":newspeed};
	$.ajax({
		url:"http://" + location.host + "/SensingCarWebProject/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#backtirespeedup").attr("onmousedown","speedup('" + data.direction + "','" + data.speed + "')");
				$("#backtirespeeddown").attr("onmousedown","speeddown('" + data.direction + "','" + data.speed + "')");
				$("#backtirego").attr("onclick","backtireDirection('forward','" + data.speed + "')");
				$("#backtireback").attr("onclick","backtireDirection('backward','" + data.speed + "')");
				point = chartSpeed.series[0].points[0];
				point.update(speed);
			}
		}
	});
}

function speeddown(tempdirection, tempspeed) {
	speed = parseInt(tempspeed);
	direction = tempdirection;
	speedIntervalId = setInterval(keepspeeddown, 100);
}

function keepspeeddown() {
	speed = speed - 200;
	console.log(speed);
	if(speed < 0) {
		speed = 0;
	}
	
	newspeed = speed.toString();
	var json = {"command":"change", "direction":direction, "speed":newspeed};
	$.ajax({
		url:"http://" + location.host + "/SensingCarWebProject/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#backtirespeedup").attr("onmousedown","speedup('" + data.direction + "','" + data.speed + "')");
				$("#backtirespeeddown").attr("onmousedown","speeddown('" + data.direction + "','" + data.speed + "')");
				$("#backtirego").attr("onclick","backtireDirection('forward','" + data.speed + "')");
				$("#backtireback").attr("onclick","backtireDirection('backward','" + data.speed + "')");
				point = chartSpeed.series[0].points[0];
				point.update(speed);
			}
		}
	});
}