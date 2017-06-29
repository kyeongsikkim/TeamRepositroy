function toggleColor(red, green, blue) {
	if ($(".tgl-red").is(':checked')==true) {
		red = "255";
	} else if($(".tgl-red").is(':checked')==false) {
		red = "0";
	}
	if($(".tgl-green").is(':checked')==true) {
		green = "255";
	} else if($(".tgl-green").is(':checked')==false) {
		green = "0";
	} 
	if($(".tgl-blue").is(':checked')==true) {
		blue = "255";
	} else if($(".tgl-blue").is(':checked')==false) {
		blue = "0";
	}
	console.log(red);
	console.log(green);
	console.log(blue);
	var json =  {"command":"change", "red": red, "green":green, "blue":blue}
	$.ajax({
		url : "http://" + location.host + "/SensingCarLocalWebControl/rgbled",
		data : json,
		method : "post",
		success:function(data) {
			if(data.red=="255") {
				$(".tgl-red").prop("checked", true);
			} else if(data.red == "0") {
				$(".tgl-red").prop("checked", false);
			}
			if(data.green=="255") {
				$(".tgl-green").prop("checked", true);
			} else if(data.green="0") {
				$(".tgl-green").prop("checked", false);
			}
			if(data.blue=="255") {
				$(".tgl-blue").prop("checked", true);
			} else if(data.blue=="0") {
				$(".tgl-blue").prop("checked", false);
			}
		}
	});
	$(function() {
		if(red=="255") {
			$(".tgl-red").prop("checked", true);
		} else if(red=="0") {
			$(".tgl-red").prop("checked", false);
		}
		if(green=="255") {
			$(".tgl-green").prop("checked", true);
		} else if(green="0") {
			$(".tgl-green").prop("checked", false);
		}
		if(blue=="255") {
			$(".tgl-blue").prop("checked", true);
		} else if(blue=="0") {
			$(".tgl-blue").prop("checked", false);
		}
	});
}