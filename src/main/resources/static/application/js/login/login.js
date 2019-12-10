$(document).ready(function() {
	submitLogin();
	
});

function validationLoginForm(){
	var user = $("#username").val();
	var password = $("#password").val();
	
}

function submitLogin(){
	$("#btnSubmit").click(function() {
		var user = $("#username").val();
		var password = $("#password").val();
		
		var request = {
			"email": user,
			"password" : password
		};
		
		$.ajax({
		    type: "POST",
		    url: _urlBackend + "api/login",
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
		    	$("#loader").addClass("d-none");
		    	$("#ajaxError").removeClass("d-none");
		    	$("#ajaxErrorMessage").text(e.responseJSON.message);
		    	
		    	setTimeout(function(){ 
		    		$("#ajaxError").addClass("d-none");
		    	}, 3000);
		    },
		    complete: function() {
			}
		});
	});
}


