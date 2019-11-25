$( document ).ready(function() {
	getAgencyName();
	
	
});

function getAgencyName() {	
	var token = localStorage.getItem('token');
	
	$.ajax({
	    type: "POST",
	    url: _urlBackend + "agency/find/token",
	    contentType: "application/json",
	    headers: {
	    	'Authorization': 'Bearer ' + token
	    },
	    beforeSend: function () {
	    	$("#loader").removeClass("d-none");
	    },
	    success: function (data) {
	    	if( data.code == 200 ){
	    		$("#agency-name").text(data.name);
	    	}
	    },
	    error: function (e) {
	    },
	    complete: function() {
	    	$("#loader").addClass("d-none");
		}
	});
}