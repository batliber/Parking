var vehiculo = null;

$(document).ready(function() {
	clearForm();
});

function inputMatriculaOnChange(event) {
	$("#inputRegistrarEntrada").prop("disabled", true);
	$("#inputRegistrarSalida").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputAgregarVehiculo").prop("disabled", true);
	
	RegistroDWR.getLastByMatricula(
		$("#inputMatricula").val(),
		{
			callback: function(data) {
				if (data != null) {
					$("#divUltimoRegistro").text(formatLongDate(data.fecha) + " - " + data.registroTipo.descripcion);
					
					if (data.registroTipo.id == 1) {
						$("#inputRegistrarSalida").prop("disabled", false);
						$("#inputGenerarFactura").prop("disabled", false);
						
						$("#inputGenerarFactura").focus();
					} else {
						$("#inputRegistrarEntrada").prop("disabled", false);
						
						$("#inputRegistrarEntrada").focus();
					}
				} else {
					$("#divUltimoRegistro").text("No hay antecedentes.");
					
					$("#inputRegistrarEntrada").prop("disabled", false);
					
					$("#inputRegistrarEntrada").focus();
				}
			}, async: false
		}
	);
	
	VehiculoDWR.getByMatricula(
		$("#inputMatricula").val(),
		{
			callback: function(data) {
				if (data != null) {
					vehiculo = data;
					
					$("#divDescripcion").text(data.descripcion);
					$("#divClienteNombre").text(data.clientes[0].nombre);
				} else {
					vehiculo = {
						matricula: $("#inputMatricula").val()
					};
					
					$("#divDescripcion").text("Vehículo no registrado");
					$("#divClienteNombre").text(".");
					
					$("#inputAgregarVehiculo").prop("disabled", false);
				}
			}, async: false
		}
	);
}

function inputRegistrarEntradaOnClick(event) {
	$("#inputRegistrarEntrada").prop("disabled", true);
	$("#inputRegistrarSalida").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputAgregarVehiculo").prop("disabled", true);
	
	var registro = {
		fecha: new Date(),
		matricula: vehiculo.matricula,
		registroTipo: {
			id: 1
		},
		uact: 1,
		fact: new Date(),
		term: 1,
	};
	
	RegistroDWR.add(
		registro,
		{
			callback: function(data) {
				clearForm();
			}, async: false
		}
	);
}

function inputRegistrarSalidaOnClick(event) {
	$("#inputRegistrarEntrada").prop("disabled", true);
	$("#inputRegistrarSalida").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputAgregarVehiculo").prop("disabled", true);
	
	var registro = {
		fecha: new Date(),
		matricula: vehiculo.matricula,
		registroTipo: {
			id: 2
		},
		uact: 1,
		fact: new Date(),
		term: 1,
	};
	
	RegistroDWR.add(
		registro,
		{
			callback: function(data) {
				clearForm();
			}, async: false
		}
	);
}

function inputGenerarFacturaOnClick(event) {
	$("#inputRegistrarEntrada").prop("disabled", true);
	$("#inputRegistrarSalida").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputAgregarVehiculo").prop("disabled", true);
	
	document.getElementById("iFrameFactura").src = "/ParkingWEB/pages/factura/factura.jsp?m=" + vehiculo.matricula;
	showPopUp(document.getElementById("divIFrameFactura"));
}

function inputAgregarVehiculoOnClick(event) {
	document.getElementById("iFrameVehiculo").src = "/ParkingWEB/pages/vehiculo/vehiculo_edit.jsp?m=" + vehiculo.matricula;
	showPopUp(document.getElementById("divIFrameVehiculo"));
}

function clearForm() {
	vehiculo = null;
	
	$("#inputRegistrarEntrada").prop("disabled", true);
	$("#inputRegistrarSalida").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputAgregarVehiculo").prop("disabled", true);
	
	$("#inputMatricula").val("");
	$("#divUltimoRegistro").text(".");
	$("#divDescripcion").text(".");
	$("#divClienteNombre").text(".");
	
	$("#inputMatricula").focus();
}