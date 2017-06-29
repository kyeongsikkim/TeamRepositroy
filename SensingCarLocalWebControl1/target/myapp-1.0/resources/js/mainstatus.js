$(function() {
	$.ajax({
		url:"<%=application.getContextPath()%>/",
		data : {
			"command" : "change",
			"status" : label
		},
		success: function(data) {
			if(data.label=="on") {
				$("#switch").prop("checked", true);
			} else {
				$("#switch").prop("checked", false);
			}
		}
	});
});