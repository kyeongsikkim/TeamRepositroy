function laser() {
	var status = "";
	if($("#laseronoff").is(':checked')==false) {
		status ="on";
	} else if($("#laseronoff").is(':checked')==true) {
		status="off";
	}
	$.ajax({
		url: "http://" + location.host + "/SensingCarWebProject/laseremitter",
		data : {
			"command" : "change",
			"status" : status
		},
		method : "post",
		success: function(data) {
			$("#divLaser").html(data.status);
		}
	})
}