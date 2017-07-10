function lcd(){
	console.log(lcd);
	var line0 = $("#inputline0").val();
	var line1 = $("#inputline1").val();
	var json = {"command":'change', "line0":line0, "line1":line1};

	$.ajax({
		url:"http://" + location.host + "/SensingCarWebProject/lcd",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#outputline0").html(data.line0);
				$("#outputline1").html(data.line1);
			}
		}
	});
}