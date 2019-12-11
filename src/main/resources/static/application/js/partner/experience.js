'use strict';
$( document ).ready(function() {
    
	dataTableReservations ();
    initDatePickers();
    
}); 

//Pinta la tabla del experiencias en el dashboard
function dataTableReservations () {
	var tableExperience = $('#table-reservaciones').DataTable( {
		"bLengthChange": false,
		"searching": false, 
		"language": {
			"emptyTable": "No hay reservaci&oacute;nes registradas",
			"info":           "Mostrando _START_ de _END_ de _TOTAL_ reservaciones",
		    "infoEmpty":      "",
		    "paginate": __paginate
		}
		
	});
	return tableExperience;
}

function initDatePickers(){
	 $('#returnDate').bootstrapMaterialDatePicker({
		lang : 'es',
		switchOnClick: true,
        weekStart: 0,
        shortTime: false,
        cancelText: 'Cerrar',
        time: false,
        format: 'DD/MM/YYYY'
	 });
	 
    $('#departDate').bootstrapMaterialDatePicker({
    	lang : 'es',
    	switchOnClick: true,
	    weekStart: 0,
	    shortTime: false,
	    cancelText: 'Cerrar',
	    format: 'DD/MM/YYYY',
	    time: false
    }).on('change', function(e, date) {
        $('#returnDate').bootstrapMaterialDatePicker('setMinDate', date);
    });
    
}
