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
					
					ClienteServicioPrecioDWR.listVigentesByCliente(
						cliente,
						{
							callback: function(dataServicios) {
								$("#tableClienteServicios > tbody:last > tr").remove();
								
								var j = 0;
								for (j=0; j<dataServicios.length; j++) {
									$("#tableClienteServicios > tbody:last").append(
										"<tr class='trClienteServicio' id='" + dataServicios[j].id + "'>"
											+ "<td class='tdClienteServiciosAcciones'>&nbsp;</td>"
											+ "<td class='tdClienteServiciosServicio'"
												+ " id='" + dataServicios[j].servicio.id + "'"
												+ " stid='" + dataServicios[j].servicio.servicioTipo.id + "'>"
												+ dataServicios[j].servicio.descripcion
											+ "</td>"
											+ "<td class='tdClienteServiciosPrecio'"
												+ " precio='" + dataServicios[j].precio + "'>"
												+ "<input type='text'"
													 + " value='" + dataServicios[j].precio + "'"
													 + " class='inputClienteServicioPrecio'/>"
											+ "</td>"
										+ "</tr>"
									);
								}
								
								if (j == 0) {
									$("#tableClienteServicios > tbody:last").append(
										"<tr>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
										+ "</tr>"
									);
								}
							}, async: false
						}
					);
					
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
	var clienteServicioPrecios = [];
	
	var trsClienteServicio = $(".trClienteServicio");
	for (var i=0; i<trsClienteServicio.length; i++) {
		var tds = $(trsClienteServicio[i]).children();
		
		var clienteServicioPrecio = {
			moneda: {
				id: 1
			},
			precio: $($(tds[2]).children(0)).val(),
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		if ($(trsClienteServicio[i]).attr("id") != null) {
			var clienteServicioPrecioAnterior = {
				id: $(trsClienteServicio[i]).attr("id"),
				validoHasta: new Date(),
				precio: $(tds[2]).attr("precio"),
				moneda: {
					id: 1
				},
				servicio: {
					id: $(tds[1]).attr("id"),
					servicioTipo: {
						id: $(tds[1]).attr("stid")
					}
				},
				uact: 1,
				fact: new Date(),
				term: 1
			};
			
			clienteServicioPrecio.servicio = {
				id: $(tds[1]).attr("id"),
				servicioTipo: {
					id: $(tds[1]).attr("stid")
				}
			};
			
			clienteServicioPrecios[clienteServicioPrecios.length] = clienteServicioPrecioAnterior;
		} else {
			clienteServicioPrecio.servicio = {
				id: $($(tds[1]).children()[0]).find("option:selected").attr("id"),
				servicioTipo: {
					id: $($(tds[1]).children()[0]).find("option:selected").attr("stid")
				}
			};
		}
			
		clienteServicioPrecios[clienteServicioPrecios.length] = clienteServicioPrecio;
	}
	
	if (id != null) {
		cliente.nombre = $("#inputClienteNombre").val();
		cliente.apellido = $("#inputClienteApellido").val();
		cliente.documento = $("#inputClienteDocumento").val();
		cliente.domicilio = $("#inputClienteDomicilio").val();
		cliente.telefono = $("#inputClienteTelefono").val();
		cliente.fechaAlta = parseShortDate($("#inputClienteFechaAlta").val());
		cliente.fact = new Date();
		
		ClienteDWR.updateConClienteServicioPrecios(
			cliente,
			clienteServicioPrecios,
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
			uact: 1,
			fact: new Date(),
			term: 1,
			vehiculos: []
		};
		
		ClienteDWR.addConClienteServicioPrecios(
			cliente,
			clienteServicioPrecios,
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

function inputAgregarVehiculoOnClick(event) {
	DepartamentoDWR.list(
		{
			callback: function(data) {
				var row = 
					"<tr>"
						+ "<td class='tdClienteVehiculoMatricula'>"
							+ "<input type='text'/>"
						+ "</td>"
						+ "<td class='tdClienteVehiculoDepartamento'>"
							+ "<select>";
				
				for (var i=0; i<data.length; i++) {
					row +=
								"<option value='" + data[i].id + "'>" + data[i].nombre + "</option>";
				}
				
				row +=
							"</select>"
						+ "</td>"
						+ "<td>"
							+ "<input type='text'/>"
						+ "</td>"
					+ "</tr>";
				
				$("#tableClienteVehiculos > tbody:last").append(row);
			}, async: false
		}
	);
}

function inputAgregarServicioOnClick(event) {
	ServicioPrecioDWR.listVigentes(
		{
			callback: function(data) {
				var options = "<option>Seleccione...</option>";
				for (var i=0; i<data.length; i++) {
					options += 
						"<option value='" + data[i].id + "' " 
							+ "id='" + data[i].servicio.id + "'"
							+ "stid='" + data[i].servicio.servicioTipo.id + "'>" 
							+ data[i].servicio.descripcion 
						+ "</option>";
				}
				
				$("#tableClienteServicios > tbody:last").append(
					"<tr class='trClienteServicio'>"
						+ "<td class='tdClienteServiciosAcciones'"
							+ " onclick='javascript:tdAccionesOnClick(event, this)'>"
							+ "&nbsp;"
						+ "</td>"
						+ "<td class='tdClienteServiciosServicio'>"
							+ "<select id='selectServicio" + servicios
								+ "' onchange='javascript:selectServicioOnChange(event, this)'>"
								+ options
							+ "</select>" 
						+ "</td>"
						+ "<td class='tdClienteServiciosPrecio'>"
							+ "<input type='text'"
								+ " value='0' class='inputClienteServicioPrecio'/>"
						+ "</td>"
					+ "</tr>"
				);
				
				servicios++;
			}, async: false
		}
	);
}

function tdAccionesOnClick(event, element) {
	$(element).parent().remove();
}

function selectServicioOnChange(event, element) {
	var tds = $(element).parent().siblings();
	$($(tds[1]).children()[0]).val(0);
	
	ServicioPrecioDWR.getById(
		$(element).val()
		, {
			callback: function(data) {
				var tds = $(element).parent().siblings();
				
				$($(tds[1]).children()[0]).val(data.precio.toFixed(2));
				$($(tds[1]).children()[0]).focus();
			}, async: false
		}
	);
}