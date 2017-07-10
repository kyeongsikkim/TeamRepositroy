function backtireDirection(direction, speed) {
	var json = {"command":"change", "direction":direction, "speed":speed};

	$.ajax({
		url:"http://" + location.host + "/SensingCarWebProject/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#divDirection").html(data.direction);
				$("#backtirespeedup").attr("onmousedown","speedup('" + data.direction + "','" + data.speed + "')");
				$("#backtirespeeddown").attr("onmousedown","speeddown('" + data.direction + "','" + data.speed + "')");
				$("#backtirespeedzero").attr("onclick", "speedzero('" + data.direction + "')");
			}
		}
	});
}