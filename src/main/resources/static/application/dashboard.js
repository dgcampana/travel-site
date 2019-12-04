var _travels = [];

$(document).ready( function () {
	getMyExperiences(1);
});


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
	    		dataTableExperiences (_travels);
	    	}else{
	    		
	    	}
	    },
	    error: function (e) {
	    },
	    complete: function() {
	    	$("#loader").addClass("d-none");
		}
	});
}


function dataTableExperiences (data) {
	console.log(data);
	$('#experiences-table').DataTable( {
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
}