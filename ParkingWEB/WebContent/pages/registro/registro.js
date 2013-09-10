var vehiculo = null;

$(document).ready(function() {
	limpiarFormulario();
});

function inputMatriculaOnChange(event) {
	$("#divButtonRegistrarEntrada").hide();
	$("#divButtonRegistrarSalida").hide();
	$("#divButtonGenerarFactura").hide();
	$("#divButtonAgregarVehiculo").hide();
	
	RegistroDWR.getLastByMatricula(
		$("#inputMatricula").val(),
		{
			callback: function(data) {
				if (data != null) {
					$("#divUltimoRegistro").text(formatLongDate(data.fecha) + " - " + data.registroTipo.descripcion);
					
					if (data.registroTipo.id == 1) {
						$("#divButtonRegistrarSalida").show();
						$("#divButtonGenerarFactura").show();
						
						$("#divButtonGenerarFactura").focus();
					} else {
						$("#divButtonRegistrarEntrada").show();
						
						$("#divButtonRegistrarEntrada").focus();
					}
				} else {
					$("#divUltimoRegistro").text("No hay antecedentes.");
					
					$("#divButtonRegistrarEntrada").show();
					
					$("#divButtonRegistrarEntrada").focus();
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
					
					$("#divButtonAgregarVehiculo").show();
				}
			}, async: false
		}
	);
}

function inputRegistrarEntradaOnClick(event) {
	$("#divButtonRegistrarEntrada").hide();
	$("#divButtonRegistrarSalida").hide();
	$("#divButtonGenerarFactura").hide();
	$("#divButtonAgregarVehiculo").hide();
	
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
				limpiarFormulario();
			}, async: false
		}
	);
}

function inputRegistrarSalidaOnClick(event) {
	$("#divButtonRegistrarEntrada").hide();
	$("#divButtonRegistrarSalida").hide();
	$("#divButtonGenerarFactura").hide();
	$("#divButtonAgregarVehiculo").hide();
	
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
				limpiarFormulario();
			}, async: false
		}
	);
}

function inputGenerarFacturaOnClick(event) {
	$("#divButtonRegistrarEntrada").hide();
	$("#divButtonRegistrarSalida").hide();
	$("#divButtonGenerarFactura").hide();
	$("#divButtonAgregarVehiculo").hide();
	
	window.parent.frames[1].location = "/ParkingWEB/pages/factura/factura.jsp?m=" + vehiculo.matricula;
}

function inputAgregarVehiculoOnClick(event) {
	
}

function limpiarFormulario() {
	vehiculo = null;
	
	$("#inputMatricula").val("");
	$("#divUltimoRegistro").text(".");
	$("#divDescripcion").text(".");
	$("#divClienteNombre").text(".");
	
	$("#inputMatricula").focus();
}