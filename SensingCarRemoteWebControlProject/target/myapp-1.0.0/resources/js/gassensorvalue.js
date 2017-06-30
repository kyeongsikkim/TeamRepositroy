$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/gassensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var gas = data.gas;
		
		$("#divGas").html(gas);
	};
});
