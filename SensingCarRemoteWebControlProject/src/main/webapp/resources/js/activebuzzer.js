function buzzer() {
	var status = "";
	if($("#buzzeronoff").is(':checked')==false) {
		status ="on";
	} else if($("#buzzeronoff").is(':checked')==true) {
		status="off";
	}
	$.ajax({
		url: "http://" + location.host + "/SensingCarRemoteWebControlProject/buzzer",
		data : {
			"command" : "change",
			"status" : status
		},
		method : "post",
		success: function(data) {
			$("#divBuzzer").html(data.status);
			
		}
	})
}