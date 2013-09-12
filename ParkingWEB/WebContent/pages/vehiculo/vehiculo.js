var vehiculo = null;

$(document).ready(function() {
	if (matricula != null && matricula != "") {
		$("#divVehiculoMatricula").text(matricula);
		
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

function inputGrabarVehiculoOnClick(event) {
	if (vehiculo == null) {
		vehiculo = {
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		if (matricula != null && matricula != "") {
			vehiculo.matricula = matricula;
		} else {
			vehiculo.matricula = $("#inputVehiculoMatricula").val();
		}

		vehiculo.descripcion = $("#inputVehiculoDescripcion").val();
		
		vehiculo.clientes = [];
		
		VehiculoDWR.add(
			vehiculo,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	} else {
		vehiculo.descripcion = $("#inputVehiculoDescripcion").val();
		
		vehiculo.clientes = [];
		
		VehiculoDWR.update(
			vehiculo,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}