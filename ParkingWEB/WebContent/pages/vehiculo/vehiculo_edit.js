var vehiculo = null;

$(document).ready(function() {
	DepartamentoDWR.list(
		{
			callback: function(data) {
				for (var i = 0; i<data.length; i++) {
					$("#selectVehiculoDepartamento").append(
						"<option value='" + data[i].id + "'>" + data[i].nombre + "</option>"
					);
				}
			}, async: false
		}
	);
	
	if (id != null) {
		VehiculoDWR.getById(
			id,
			{
				callback: function(data) {
					vehiculo = data;
					
					$("#inputVehiculoMatricula").val(data.matricula);
					$("#inputVehiculoDescripcion").val(data.descripcion);
					
					$("#tableVehiculoClientes > tbody:last > tr").remove();
					
					for (var i=0; i<data.clientes.length; i++) {
						$("#tableVehiculoClientes > tbody:last").append(
							"<tr id='" + data.clientes[i].id + "'>"
								+ "<td class='tdVehiculoClienteNombre'><div id='divVehiculoClienteNombre" + data.clientes[i].id + "'>" + data.clientes[i].nombre + "</div></td>"
							+ "</tr>"
						);
					}
				}, async: false
			}
		);
	}
});

function inputVehiculoMatriculaOnChange(event) {
	vehiculo = null;
	
	$("#inputVehiculoDescripcion").val("");
	
	$("#tableVehiculoClientes > tbody:last > tr").remove();
	
	matricula = $("#inputVehiculoMatricula").val();
	if (matricula != "") {
		VehiculoDWR.getByMatricula(
			matricula,
			{
				callback: function(data)  {
					if (data != null) {
						vehiculo = data;
						
						$("#inputVehiculoDescripcion").val(data.descripcion);
						
						for (var i=0; i<data.clientes.length; i++) {
							$("#tableVehiculoClientes > tbody:last").append(
								"<tr>"
									+ "<td><div>" + data.clientes[i].nombre + "</div></td>"
								+ "</tr>"
							);
						}
					}
				}, async: false
			}
		);
	}
}

function inputVehiculoClienteDocumentoOnChange(event) {
	$("#inputVehiculoClienteNombre").val("");
	$("#inputVehiculoClienteDomicilio").val("");
	$("#inputVehiculoClienteTelefono").val("");
	
	ClienteDWR.getByDocumento(
		$("#inputVehiculoClienteDocumento").val()
		, {
			callback: function(data) {
				if (data != null) {
					$("#inputVehiculoClienteNombre").val(data.nombre);
					$("#inputVehiculoClienteDomicilio").val(data.domicilio);
					$("#inputVehiculoClienteTelefono").val(data.telefono);
				}
			}, async: false
		}
	);
}

function inputGrabarVehiculoOnClick(event) {
	if (id != null) {
		vehiculo.matricula = $("#inputVehiculoMatricula").val();
		vehiculo.descripcion = $("#inputVehiculoDescripcion").val();
		vehiculo.uact = 1;
		vehiculo.fact = new Date();
		vehiculo.term = 1;
		
		VehiculoDWR.update(
			vehiculo,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	} else {
		vehiculo = {
			matricula: $("#inputVehiculoMatricula").val(),
			descripcion: $("#inputVehiculoDescripcion").val(),
			clientes: [],
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		VehiculoDWR.add(
			vehiculo,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}