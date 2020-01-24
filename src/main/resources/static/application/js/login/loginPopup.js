$(document).ready(function() {
	validFormLogin();
	submitLogin();
	
});


function submitLogin(){
	$("#btnSubmit").click(function() {
		if($("#loginForm").valid()){
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
			    headers: {
			    	'Accept-Language': 'es'
			    },

			    beforeSend: function () {
			    	$("#btnSubmitLoad").removeClass("d-none");
			    	$("#btnSubmit").addClass("d-none");
			    	
			    },
			    success: function (data) {
			    	if( data.code == 200 ){
			    		localStorage.setItem("token", data.token);
			    	}
			    	$("#loginForm").submit();
			    },
			    error: function (e) {
			    	$("#btnSubmitLoad").addClass("d-none");
			    	$("#btnSubmit").removeClass("d-none");
			    	
			    	$("#ajaxError").removeClass("d-none");
			    	$("#ajaxErrorMessage").text(e.responseJSON.message);
			    	
			    	setTimeout(function(){ 
			    		$("#ajaxError").addClass("d-none");
			    	}, 10000);
			    },
			    complete: function() {
			    	
				}
			});
		}
	});
}

function validFormLogin() {
    // [ Initialize validation ] start
    $('#loginForm').validate({
        ignore: '.ignore, .select2-input',
        focusInvalid: false,
        rules: {
            'password': {
                required: true
            },
            'username': {
            	required: true,
                email: true
            }
        },
        // Errors //
        errorPlacement: function errorPlacement(error, element) {
            var $parent = $(element).parents('.form-group');

            // Do not duplicate errors
            if ($parent.find('.jquery-validation-error').length) {
                return;
            }
            $parent.append(
                error.addClass('jquery-validation-error small form-text invalid-feedback')
            );
        },
        highlight: function highlight(element) {
            var $el = $(element);
            var $parent = $el.parents('.form-group');

            $el.addClass('is-invalid');

            // Select2 and Tagsinput
            if ($el.hasClass('select2-hidden-accessible') || $el.attr('data-role') === 'tagsinput') {
                $el.parent().addClass('is-invalid');
            }
        },
        unhighlight: function(element) {
            $(element).parents('.form-group').find('.is-invalid').removeClass('is-invalid');
        }
    });
}


