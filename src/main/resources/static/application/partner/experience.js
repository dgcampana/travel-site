'use strict';
$( document ).ready(function() {
    
    
    initDatePickers();
    
}); 


function initDatePickers(){
	
	 $('#returnDate').bootstrapMaterialDatePicker({
		 lang : 'es',
        weekStart: 0,
        format: 'dddd DD MMMM YYYY - HH:mm'
	 });
	 
    $('#departDate').bootstrapMaterialDatePicker({
    	lang : 'es',
	    weekStart: 0,
	    format: 'dddd DD MMMM YYYY - HH:mm'
    }).on('change', function(e, date) {
        $('#returnDate').bootstrapMaterialDatePicker('setMinDate', date);
    });
    
}
