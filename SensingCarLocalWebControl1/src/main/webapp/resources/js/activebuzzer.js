function handleLabelBuzzer() {
	var status = $("#buzzer").html();
	if($("#switch").is(':checked')==true) {
		status = "on";
	} else if($("#switch").is(':checked')==false) {
		status="off";
	}
	console.log(status);
	$.ajax({
		url : "http://" + location.host + "/SensingCarLocalWebControl/buzzer",
		data : {
			"command" : "change",
			"status" : status
		},
		method : "post",
		success: function(data) {
			$("#buzzer").html(data.status);
		}
	})
}