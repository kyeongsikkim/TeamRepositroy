$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarWebProject/websocket/ultrasonicsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var distance = data.distance;
		$("#divDistance").html(distance + "cm");
		
		if(distance < 10) {
			var json = {"command":"change", "direction":"forward", "speed":"0"};
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
						$("#backtirespeedupvalue").html(data.speed);
					}
				}
			});
		}
	};
});
