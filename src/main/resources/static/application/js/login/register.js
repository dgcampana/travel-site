
$( document ).ready(function() {
	validFormRegister();
});


function clickRegister() {
	if($("#registerForm").valid()){
		registerUser();
	} 
}

function registerUser() {
	var name        = $("#name").val();
	var lastname    = $("#lastname").val();
	var email       = $("#email").val();
	var pass        = $("#passwordRegister").val();
	
	var request = {
		"email": email,
		"lastName" : lastname,
		"name": name,
		"password": pass
	};
	
	$.ajax({
	    type: "POST",
	    url: _urlBackend + "user/create",
	    data: JSON.stringify(request),
	    contentType: "application/json",
	    headers: {
	    	'Accept-Language': 'es'
	    },

	    beforeSend: function () {
	    	$("#loader").removeClass("d-none");
	    },
	    success: function (data) {
	    	if( data.code == 200 ){
	    		notify(data.message, 'success');
	    	}else {
	    		notify(data.message, 'danger');
	    	}
	    },
	    error: function (e) {
	    	notify(e.responseJSON.message, 'danger');
	    	$("#loader").addClass("d-none");
	    },
	    complete: function() {
	    	$("#loader").addClass("d-none");
		}
	});
}



function validFormRegister() {
    // [ Initialize validation ] start
    $('#registerForm').validate({
        ignore: '.ignore, .select2-input',
        focusInvalid: false,
        rules: {
            'validation-email': {
                required: true,
                email: true
            },
            'validation-password': {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            'validation-password-confirmation': {
                required: true,
                minlength: 6,
                equalTo: 'input[name="validation-password"]'
            },
            'validation-required': {
                required: true
            },
            'validation-required-2': {
                required: true
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
        highlight: function(element) {
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

function notify(message, type) {
    $.growl({
        message: message
    }, {
        type: type,
        allow_dismiss: false,
        label: 'Cancel',
        className: 'btn-xs btn-inverse',
        placement: {
            from: 'top',
            align: 'right'
        },
        delay: 10000,
        animate: {
            enter: 'animated flipInX',
            exit: 'animated flipOutX'
        },
        offset: {
            x: 30,
            y: 30
        }
    });
}



