function ultrasonicsensor(command, angle) {
	var json = {"command":command, "angle":angle};

	$.ajax({
		url:"http://" + location.host + "/SensingCarRemoteWebControlProject/ultrasonicsensor",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#ultrasonicleftrightAngle").html(data.angle + "º");
			}
		}
	});
}