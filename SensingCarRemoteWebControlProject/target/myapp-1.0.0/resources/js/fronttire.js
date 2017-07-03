function fronttire(nowangle, changeangle) {
	var angle;
	if(changeangle == "increase") {
		angle = nowangle - (-10);
	} else if(changeangle == "decrease") {
		angle = nowangle - 10;
	}
	console.log(angle);
	var json = {"command":"change", "angle":angle};

	$.ajax({
		url:"http://" + location.host + "/SensingCarRemoteWebControlProject/fronttire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#divFronttireAngle").html(data.angle+"º");
				$("#fronttireleft").attr("onclick","fronttire('" + data.angle + "', 'decrease')");
				$("#fronttireright").attr("onclick","fronttire('" + data.angle + "', 'increase')");
			}
		}
	});
}