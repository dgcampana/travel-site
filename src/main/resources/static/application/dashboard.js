var _travels = [];
var _tableExperience;

$(document).ready( function () {
	getMyExperiences(1);
	
	//Hacer clic a la fila seleccionada de la tabla de experiencias.
	$('#experiences-table tbody').on('click', 'tr', function () {
	    var data = _tableExperience.row( this ).data();
	    location.href = 'experience?id='+data.id;
	} );
});

//Realiza la petecion al servicio para obtener la experiencias del asociadas
//al usuario si este pertenece a una agencia
function getMyExperiences( page ) {
	var token = localStorage.getItem('token');
	$.ajax({
	    type: "GET",
	    url: _urlBackend + "travel/find/my-experiences?pageNumber=" + page,
	    contentType: "application/json",
	    headers: {
	    	'Authorization': 'Bearer ' + token
	    },
	    beforeSend: function () {
	    	$("#loader").removeClass("d-none");
	    },
	    success: function (data) {
	    	if( data.code == 200 ) {
	    		_travels = data.travels;
	    		
	    		_tableExperience =
	    		dataTableExperiences (_travels);
	    	}else{
	    		//TODO: enviar mensaje de error
	    		alert("No se pudieron cargar tu experiencias. ");
	    	}
	    },
	    error: function (e) {
	    },
	    complete: function() {
	    	$("#loader").addClass("d-none");
		}
	});
}

//Pinta la tabla del experiencias en el dashboard
function dataTableExperiences (data) {
	var tableExperience = $('#experiences-table').DataTable( {
		"bLengthChange": false,
		"searching": false, 
		data: _travels,
	    columns: [
	        { data: 'id' },
	        { data: 'title' },
	        { data: 'departDate' },
	        { data: 'returnDate' },
	        { data: 'status' }
	    ],
	    "columnDefs": [ {
	        "targets": [2, 3],
	        "render": function ( data, type, row, meta ) {
	          return data.substr( 0, 10 );
	        }
	      } ]
	});
	
	return tableExperience;
}





