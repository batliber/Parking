var cliente = null;

var servicios = 0;

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
					$("#inputClienteApellido").val(data.apellido != null ? data.apellido : "");
					$("#inputClienteDomicilio").val(data.domicilio != null ? data.domicilio : "");
					$("#inputClienteTelefono").val(data.telefono != null ? data.telefono : "");
					$("#inputClienteFechaAlta").val(data.fechaAlta != null ? formatShortDate(data.fechaAlta, true) : "");
					
					$("#tableClienteVehiculos > tbody:last > tr").remove();
					
					for (var i=0; i<data.vehiculos.length; i++) {
						VehiculoServicioPrecioDWR.getPrecioVigenteParkingMensualByVehiculo(
							data.vehiculos[i],
							{
								callback: function(dataVehiculoServicioPrecio) {
									if (dataVehiculoServicioPrecio != null) {
										$("#tableClienteVehiculos > tbody:last").append(
											"<tr class='trVehiculoServicioPrecio' vid='" + data.vehiculos[i].id + "'"
												+ " vspid='" + dataVehiculoServicioPrecio.id + "'>"
												+ "<td class='tdVehiculoServicioPrecioAcciones'" 
													+ " onclick='javascript:tdAccionesOnClick(event, this)'>"
													+ "&nbsp;"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioMatricula'"
													+ " vid='" + data.vehiculos[i].id + "'>"
													+ "<div class='divVehiculoServicioPrecioMatricula'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioMatricula'"
															+ " value='" + data.vehiculos[i].matricula + "'/>"
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioDescripcion'>"
													+ "<div class='divVehiculoServicioPrecioDescripcion'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioDescripcion'"
															+ " value='" + data.vehiculos[i].descripcion + "'/>"
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioServicio'"
													+ " sid='" + dataVehiculoServicioPrecio.servicio.id + "'"
													+ " stid='" + dataVehiculoServicioPrecio.servicio.servicioTipo.id + "'>"
													+ "<div class='divVehiculoServicioPrecioServicio'>"
														+ dataVehiculoServicioPrecio.servicio.descripcion
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioPrecio'"
													+ " precio='" + dataVehiculoServicioPrecio.precio + "'>"
													+ "<div class='divVehiculoServicioPrecioPrecio'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioPrecio'"
															+ " value='" + dataVehiculoServicioPrecio.precio + "'/>"
													+ "</div>"
												+ "</td>"
											+ "</tr>"
										);
									} else {
										$("#tableClienteVehiculos > tbody:last").append(
											"<tr class='trVehiculoServicioPrecio'>"
												+ "<td class='tdVehiculoServicioPrecioAcciones'" 
													+ " onclick='javascript:tdAccionesOnClick(event, this)'>"
													+ "&nbsp;"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioMatricula'"
													+ " vid='" + data.vehiculos[i].id + "'>"
													+ "<div class='divVehiculoServicioPrecioMatricula'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioMatricula'"
															+ " value='" + data.vehiculos[i].matricula + "'/>"
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioDescripcion'>"
													+ "<div class='divVehiculoServicioPrecioDescripcion'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioDescripcion'"
															+ " value='" + data.vehiculos[i].descripcion + "'/>"
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioServicio'>"
													+ "<div class='divVehiculoServicioPrecioServicio'>"
														+ "&nbsp;"
													+ "</div>"
												+ "</td>"
												+ "<td class='tdVehiculoServicioPrecioPrecio'"
													+ "<div class='divVehiculoServicioPrecioPrecio'>"
														+ "<input type='text'"
															+ " class='inputVehiculoServicioPrecioPrecio'/>"
													+ "</div>"
												+ "</td>"
											+ "</tr>"
										);
									}
								}, async: false
							}
						);
					}
					
					if (data.vehiculos.length == 0) {
						$("#tableClienteVehiculos > tbody:last").append(
							"<tr class='trDummyRow'>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
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
	var vehiculoServicioPrecios = [];
	var vehiculos = [];
	
	var trsVehiculoServicioPrecio = $(".trVehiculoServicioPrecio");
	for (var i=0; i<trsVehiculoServicioPrecio.length; i++) {
		var tds = $(trsVehiculoServicioPrecio[i]).children();
		
		var vehiculoServicioPrecio = {
			moneda: {
				id: 1
			},
			servicio: {
				id: $(tds[3]).attr("sid"),
				servicioTipo: {
					id: $(tds[3]).attr("stid")
				}
			},
			precio: $($($(tds[4]).children(0)).children(0)).val(),
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		vehiculoServicioPrecio.vehiculo = {
			matricula: $($(tds[1]).children(0)).children(0).val(),
			descripcion: $($(tds[2]).children(0)).children(0).val(),
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		DepartamentoDWR.getByMatricula(
			vehiculoServicioPrecio.vehiculo.matricula,
			{
				callback: function(data) {
					vehiculoServicioPrecio.vehiculo.departamento = data;
				}, async: false
			}
		);
		
		if ($(tds[1]).attr("vid") != null) {
			vehiculoServicioPrecio.vehiculo.id = $(tds[1]).attr("vid");
		}
		
		vehiculos[vehiculos.length] = vehiculoServicioPrecio.vehiculo;
		vehiculoServicioPrecios[vehiculoServicioPrecios.length] = vehiculoServicioPrecio;
	}
	
	if (id != null) {
		cliente.nombre = $("#inputClienteNombre").val();
		cliente.apellido = $("#inputClienteApellido").val();
		cliente.documento = $("#inputClienteDocumento").val();
		cliente.domicilio = $("#inputClienteDomicilio").val();
		cliente.telefono = $("#inputClienteTelefono").val();
		cliente.fechaAlta = parseShortDate($("#inputClienteFechaAlta").val());
		cliente.fact = new Date();
		cliente.vehiculos = vehiculos;
		
		ClienteDWR.updateConVehiculoServicioPrecios(
			cliente,
			vehiculoServicioPrecios,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	} else {
		cliente = {
			nombre: $("#inputClienteNombre").val(),
			apellido: $("#inputClienteApellido").val(),
			documento: $("#inputClienteDocumento").val(),
			domicilio: $("#inputClienteDomicilio").val(),
			telefono: $("#inputClienteTelefono").val(),
			fechaAlta: parseShortDate($("#inputClienteFechaAlta").val()),
			vehiculos: vehiculos,
			uact: 1,
			fact: new Date(),
			term: 1,
		};
		
		ClienteDWR.addConVehiculoServicioPrecios(
			cliente,
			vehiculoServicioPrecios,
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
	}
}

function inputAgregarVehiculoOnClick(event) {
	$("#tableClienteVehiculos > tbody:last > .trDummyRow").remove();
	
	ServicioPrecioDWR.getPrecioVigenteParkingMensual(
		{
			callback: function(data) {
				$("#tableClienteVehiculos > tbody:last").append(
					"<tr class='trVehiculoServicioPrecio'>"
						+ "<td class='tdVehiculoServicioPrecioAcciones'" 
							+ " onclick='javascript:tdAccionesOnClick(event, this)'>"
							+ "&nbsp;"
						+ "</td>"
						+ "<td class='tdVehiculoServicioPrecioMatricula'>"
							+ "<div class='divVehiculoServicioPrecioMatricula'>"
								+ "<input type='text' class='inputVehiculoServicioPrecioMatricula'/>"
							+ "</div>"
						+ "</td>"
						+ "<td class='tdVehiculoServicioPrecioDescripcion'>"
							+ "<div class='divVehiculoServicioPrecioDescripcion'>"
								+ "<input type='text' class='inputVehiculoServicioPrecioDescripcion'/>"
							+ "</div>"
						+ "</td>"
						+ "<td class='tdVehiculoServicioPrecioServicio'"
							+ " sid='" + data.servicio.id + "'"
							+ " stid='" + data.servicio.servicioTipo.id + "'>"
							+ "<div class='divVehiculoServicioPrecioServicio'>"
								+ data.servicio.descripcion
							+ "</div>"
						+ "</td>"
						+ "<td class='tdVehiculoServicioPrecioPrecio'>"
							+ "<div class='divVehiculoServicioPrecioPrecio'>"
								+ "<input type='text'"
									+ " class='inputVehiculoServicioPrecioPrecio'"
									+ " value='" + data.precio + "'/>"
							+ "</div>"
						+ "</td>"
					+ "</tr>"
				);
			}, async: false
		}
	);
}

function tdAccionesOnClick(event, element) {
	$(element).parent().remove();
	
	var trs = $("#tableClienteVehiculos > tbody:last > tr");
	if (trs.length == 0) {
		$("#tableClienteVehiculos > tbody:last").append(
			"<tr class='trDummyRow'>"
				+ "<td><div>&nbsp;</div></td>"
				+ "<td><div>&nbsp;</div></td>"
				+ "<td><div>&nbsp;</div></td>"
				+ "<td><div>&nbsp;</div></td>"
				+ "<td><div>&nbsp;</div></td>"
			+ "</tr>"
		);
	}
}