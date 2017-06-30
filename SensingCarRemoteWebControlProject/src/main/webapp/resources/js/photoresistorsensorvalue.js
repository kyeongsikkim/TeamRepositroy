$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/photoresistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var brightness = data.photoresistor;
		
		$("#divPhoto").html(brightness);
	};
});
