function handleLabelBuzzer() {
	var label = "off";
	$(".m-rocker:not(:checked) > input:checked + label").addClass("status_ON");
	if ($(".m-rocker:not(:checked) > input:checked + label").hasClass(
			"status_ON")) {
		label = "on";
	}
	console.log(label);
	$.ajax({
		url : "http://" + location.host + "/SensingCarLocalWebControl/buzzer",
		data : {
			"command" : "change",
			"status" : label
		},
		method : "post"
	})
}