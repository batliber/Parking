var cliente = null;

$(document).ready(function() {
	$("#inputEliminarCliente").prop("disabled", true);
	
	if (id != null) {
		ClienteDWR.getById(
			id,
			{
				callback: function(data) {
					cliente = data;
					
					$("#inputClienteDocumento").val(data.documento);
					$("#inputClienteNombre").val(data.nombre);
					$("#inputClienteDomicilio").val(data.domicilio != null ? data.domicilio : "");
					$("#inputClienteTelefono").val(data.telefono != null ? data.telefono : "");
					
					$("#tableClienteVehiculos > tbody:last > tr").remove();
					
					for (var i=0; i<data.vehiculos.length; i++) {
						$("#tableClienteVehiculos > tbody:last").append(
							"<tr id='" + data.vehiculos[i].id + "'>"
								+ "<td class='tdClienteVehiculoMatricula'><div id='divClienteVehiculoMatricula" + data.vehiculos[i].id + "'>" + data.vehiculos[i].matricula + "</div></td>"
								+ "<td class='tdClienteVehiculoDescripcion'><div id='divClienteVehiculoDescripcion" + data.vehiculos[i].id + "'>" + data.vehiculos[i].descripcion + "</div></td>"
							+ "</tr>"
						);
					}
					
					$("#inputEliminarCliente").prop("disabled", false);
				}, async: false
			}
		);
	}
});

function inputGuardarOnClick(event) {
	if (id != null) {
		cliente.nombre = $("#inputClienteNombre").val();
		cliente.documento = $("#inputClienteDocumento").val();
		cliente.domicilio = $("#inputClienteDomicilio").val();
		cliente.telefono = $("#inputClienteTelefono").val();
		cliente.fact = new Date();
		
		ClienteDWR.update(
			cliente,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	} else {
		cliente = {
			nombre: $("#inputClienteNombre").val(),
			documento: $("#inputClienteDocumento").val(),
			domicilio: $("#inputClienteDomicilio").val(),
			telefono: $("#inputClienteTelefono").val(),
			fact: new Date(),
			vehiculos: []
		};
		
		ClienteDWR.add(
			cliente,
			{
				callback: function(data) {
					$("#inputEliminarCliente").prop("disabled", false);
				}, async: false
			}
		);
	}
}

function inputEliminarOnClick(event) {
	if ((id != null) && confirm("Se eliminará el Cliente")) {
		cliente.fechaBaja = new Date();
		cliente.fact = new Date();
		
		ClienteDWR.update(
			cliente,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}