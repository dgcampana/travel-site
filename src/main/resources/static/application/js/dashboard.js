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
	    },
	    success: function (data) {
	    	if( data.code == 200 ) {
	    		_travels = data.travels;
	    		_tableExperience =
	    		dataTableExperiences (_travels);
	    		
	    		$("#loanding-experiences").remove();
	    		$("#container-experiences-table").removeClass("d-none");
	    		
	    	}else{
	    		notify('Lo sentimos no pudimos cargar tus experiencias principales :(', 'danger');
	    	}
	    },
	    error: function (e) {
	    	notify('Error en el servidor contacte a soporte.', 'danger');
	    },
	    complete: function() {
		}
	});
}

//Pinta la tabla del experiencias en el dashboard
function dataTableExperiences (data) {
	var tableExperience = $('#experiences-table').DataTable( {
		"bLengthChange": false,
		"searching": false, 
		"language": {
			"emptyTable": "No hay experiencias registradas",
			"info":       "Mostrando _START_ de _END_ de _TOTAL_ experiencias",
		    "infoEmpty":  "",
		    "paginate": __paginate
		},
		data: _travels,
	    columns: [
	        { data: 'title' },
	        { data: 'departDate' },
	        { data: 'returnDate' },
	        { data: 'status' }
	    ],
	    "columnDefs": [ 
	    	{
    		"targets": [1, 2],
    		"render": function ( data ) {
			    		return data.substr( 0, 10 );
					}
	        },
	        {
	        "targets": [3],
	        "render": function ( data ) {
		        	if(data == 'ACTIVE'){  
		        		return '<a style="width: 120px !important;" class="text-white label theme-bg f-12">' + data + '</a>';
		        	}else{
		        		return '<a style="width: 120px !important;" class="label theme-bg2 f-12 text-white">' + data + '</a>';
		        	}
	        	}
	        } 
	    ]
	});
	return tableExperience;
}


   





