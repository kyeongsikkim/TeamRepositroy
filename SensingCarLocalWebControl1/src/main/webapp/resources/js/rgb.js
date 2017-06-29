function toggleColor(redT, greenT, blueT) {

	var red = $("#colorR").html();
	var green = $("#colorG").html();
	var blue = $("#colorB").html();

	if ($(".tgl-red").is(':checked') == true) {
		red = "255";
	} else if ($(".tgl-red").is(':checked') == false) {
		red = "0";
	}
	if ($(".tgl-green").is(':checked') == true) {
		green = "255";
	} else if ($(".tgl-green").is(':checked') == false) {
		green = "0";
	}
	if ($(".tgl-blue").is(':checked') == true) {
		blue = "255";
	} else if ($(".tgl-blue").is(':checked') == false) {
		blue = "0";
	}
	
	if (redT == "255" || greenT == "255" || blueT == "255") {
		red = redT;
		green = greenT;
		blue = blueT;
	}
	var json = {
		"command" : "change",
		"red" : red,
		"green" : green,
		"blue" : blue
	}
	$.ajax({
		url : "http://" + location.host + "/SensingCarLocalWebControl/rgbled",
		data : json,
		method : "post",
		success : function(data) {
			if (data.red == "255") {
				$(".tgl-red").prop("checked", true);
			} else if (data.red == "0") {
				$(".tgl-red").prop("checked", false);
			}
			if (data.green == "255") {
				$(".tgl-green").prop("checked", true);
			} else if (data.green = "0") {
				$(".tgl-green").prop("checked", false);
			}
			if (data.blue == "255") {
				$(".tgl-blue").prop("checked", true);
			} else if (data.blue == "0") {
				$(".tgl-blue").prop("checked", false);
			}
		}
	});

}