$(function() {
	var status = $("#buzzer").html();
	var red = $("#colorR").html();
	var green = $("#colorG").html();
	var blue = $("#colorB").html();
	if(status=="on") {
		$("#switch").prop("checked", true);
	} else if(status=="off"){
		$("#switch").prop("checked", false);
	}
	
	if(red=="255") {
		$(".tgl-red").prop("checked", true);
	}
	if(green=="255") {
		$(".tgl-green").prop("checked", true);
	}
	if(blue=="255") {
		$(".tgl-blue").prop("checked", true);
	}
});