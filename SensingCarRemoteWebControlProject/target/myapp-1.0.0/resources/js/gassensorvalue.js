var statusTmp = "off";
$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/gassensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var gas = data.gas;
		if(gas > 200 && statusTmp == "off") {
			statusTmp = "on";
			buzzer2("on");
		} else if(gas <= 200 && statusTmp == "on") {
			statusTmp = "off";
			buzzer2("off");
		}
		$("#divGas").html(gas);
	};
});

function buzzer2(status) {
	$.ajax({
		url: "http://" + location.host + "/SensingCarRemoteWebControlProject/buzzer",
		data : {
			"command" : "change",
			"status" : status
		},
		method : "post",
		success: function(data) {
			$("#divBuzzer").html(data.status);
			if(status == "on") {
				$("#buzzeronoff").removeAttr("checked","checked");
			} else {
				$("#buzzeronoff").attr("checked","checked");
			}
		}
	})
}
