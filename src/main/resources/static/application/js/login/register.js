
$( document ).ready(function() {
	validFormRegisterStep1();
});


function clickRegister() {
	if( $("#registerFormStep1").valid() ){
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
	    	loadingButton(true);
	    },
	    success: function (data) {
	    	if( data.code == 200 ){
	    		$("#successRegister").removeClass("d-none");
	    		$("#successText").text(data.message);
	    		loadingButton(false);
	    		// Se resetea el formulario
	    		$("#registerFormStep1")[0].reset();
	    		
	    		setTimeout(function(){
	    			$("#successRegister").addClass("d-none");
	    		}, 5000);
	    		
	    	}else {
	    		$("#errorRegister").removeClass("d-none");
	    		$("#errorText").text(data.message);
	    		loadingButton(false);
	    	}
	    },
	    error: function (e) {
	    	$("#errorRegister").removeClass("d-none");
    		$("#errorText").text(e.responseJSON.message);
    		loadingButton(false);
	    	
	    	setTimeout(function(){
    			$("#errorRegister").addClass("d-none");
    		}, 5000);
	    },
	    complete: function() {
		}
	});
}
/**
 * Oculta o muestra el boton con el icono de cargar 
 * @param e si es true muestra el boton cargando si no lo oculta
 */
function loadingButton(e){
	if(e){
		$("#btnSubmitLoad").removeClass("d-none");
    	$("#btnSubmitRegister").addClass("d-none");
	}else{
		$("#btnSubmitLoad").addClass("d-none");
    	$("#btnSubmitRegister").removeClass("d-none");
	}
}

function validFormRegisterStep1() {
    // [ Initialize validation ] start
    $('#registerFormStep1').validate({
        ignore: '.ignore, .select2-input',
        focusInvalid: false,
        rules: {
        	'validation-email': {
                required: true,
                email: true
            },
            'validation-required': {
                required: true
            },
            'validation-required-2': {
                required: true
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




