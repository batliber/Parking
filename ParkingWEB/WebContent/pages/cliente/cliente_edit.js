var cliente = null;

$(document).ready(function() {
	if (id != null) {
		ClienteDWR.getById(
			id,
			{
				callback: function(data) {
					cliente = data;
					
					$("#inputClienteDocumento").val(data.documento);
					$("#inputClienteNombre").val(data.nombre);
					$("#inputClienteDomiclio").val(data.domicilio != null ? data.domicilio : "");
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
				}, async: false
			}
		);
	}
});

function inputGrabarClienteOnClick(event) {
	if (id != null) {
		cliente.nombre = $("#inputClienteNombre").val();
		cliente.uact = 1;
		cliente.fact = new Date();
		cliente.term = 1;
		
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
			vehiculos: [],
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		ClienteDWR.add(
			cliente,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}