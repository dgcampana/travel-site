$(document).ready(function() {

	$("#btnSubmit").click(function() {
		var user = $("#username").val();
		var password = $("#password").val();
		
		var request = {
			"email": user,
			"password" : password
		};
		
		$.ajax({
		    type: "POST",
		    url: "https://kmp-travel-ws.herokuapp.com/api/login",
		    data: JSON.stringify(request),
		    contentType: "application/json",

		    beforeSend: function () {
		    	$("#loader").removeClass("d-none");
		    },
		    success: function (data) {
		    	if( data.code == 200 ){
		    		localStorage.setItem("token", data.token);
		    	}
		    	$("#loginForm").submit();
		    },
		    error: function (e) {
		    	$("#ajaxError").removeClass("d-none");
		    	$("#ajaxErrorMessage").text(e.responseJSON.message);
		        console.error(e);
		    },
		    complete: function() {
		    	$("#loader").addClass("d-none");
			}
		});
		
		
	});

});