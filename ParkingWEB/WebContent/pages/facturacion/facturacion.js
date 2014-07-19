var __PORCENTAJE_IVA = 0.22;
var __CLIENTE_GENERICO_DOCUMENTO = 1;
var __CLIENTE_GENERICO_ID = 0;

var servicios = 0;

$(document).ready(function() {
	clearForm();
	
	ClienteDWR.getByDocumento(
		__CLIENTE_GENERICO_DOCUMENTO,
		{
			callback: function(data) {
				__CLIENTE_GENERICO_ID = data.id;
			}, async: false
		}
	);
	
	$("#inputClienteDocumento").focus();
});

function inputClienteDocumentoOnChange(event, element) {
	clearForm(false);
	
	ClienteDWR.getByDocumento(
		$("#inputClienteDocumento").val(),
		{
			callback: function(data) {
				if (data != null) {
					$("#inputClienteId").val(data.id);
					$("#divClienteNombre").html(data.nombre);
					$("#divClienteApellido").html(data.apellido);
					$("#divClienteDomicilio").html(data.domicilio);
					$("#divClienteTelefono").html(data.telefono);
					
					CobranzaMovimientoDWR.listSinFacturarByCliente(
						data
						, { 
							callback: function(dataCobranza) {
								$("#tableFacturaLineas > tbody:last > tr").remove();
								
								var i = 0;
								for (i=0; i<dataCobranza.length; i++) {
									$("#tableFacturaLineas > tbody:last").append(
										"<tr class='trCobranzaMovimiento' id='" + dataCobranza[i].id + "'>"
											+ "<td>&nbsp;</td>"
											+ "<td class='tdServicio'"
												+ "id='" + dataCobranza[i].servicio.id + "' " 
												+ "stid='" + dataCobranza[i].servicio.servicioTipo.id + "' "
												+ "title='" + dataCobranza[i].servicio.descripcion + "'>"
												+ dataCobranza[i].cobranzaTipoDocumento.descripcion
											+ "</td>"
											+ "<td class='tdImporteUnitario'>" 
												+ (dataCobranza[i].importe / (1 + __PORCENTAJE_IVA)).toFixed(0) 
											+ "</td>"
											+ "<td class='tdCantidad'>1</td>"
											+ "<td class='tdNeto'>"
												+ (dataCobranza[i].importe / (1 + __PORCENTAJE_IVA)).toFixed(0)
											+ "</td>"
											+ "<td class='tdTotal'>" 
												+ dataCobranza[i].importe.toFixed(0)
											+ "</td>"
										+ "</tr>"
									);
								}
								
								if (i == 0) {
									generarDummyRow();
								}
								
								calcularPieDeFactura();
							}, async: false
						}
					);
					
					$("#inputAgregarServicio").prop("disabled", false);
					$("#inputGenerarFactura").prop("disabled", false);
					$("#inputImprimirFactura").prop("disabled", true);
				} else {
					alert("No se encuentra el Cliente.");
					
					$("#divClienteNombre").html("<input type='text' id='inputClienteNombre'/>");
					$("#divClienteApellido").html("<input type='text' id='inputClienteApellido'/>");
					$("#divClienteDomicilio").html("<input type='text' id='inputClienteDomicilio'/>");
					$("#divClienteTelefono").html("<input type='text' id='inputClienteTelefono'/>");
					
					$("#inputAgregarServicio").prop("disabled", false);
					$("#inputGenerarFactura").prop("disabled", false);
					$("#inputImprimirFactura").prop("disabled", true);
				}
			}, async: false
		}
	);
}

function inputAgregarServicioOnClick(event, element) {
	$("#tableFacturaLineas > tbody:last > .trDummyRow").remove();
	
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
				
				$("#tableFacturaLineas > tbody:last").append(
					"<tr class='trServicioAdicional'>"
						+ "<td class='tdAcciones' onclick='javascript:tdAccionesOnClick(event, this)'>"
							+ "&nbsp;"
						+ "</td>"
						+ "<td class='tdServicio'>"
							+ "<select id='selectServicio" + servicios 
								+ "' onchange='javascript:selectServicioOnChange(event, this)'>"
								+ options
							+ "</select>" 
						+ "</td>"
						+ "<td class='tdImporteUnitario'>0</td>"
						+ "<td class='tdCantidad'>"
							+ "<input type='text' value='0' class='inputCantidad'" 
								+ " onchange='javascript:inputCantidadOnChange(event, this)'/>" 
						+ "</td>"
						+ "<td class='tdNeto'>0</td>"
						+ "<td class='tdTotal'>"
							+ "<input type='text' value='0' class='inputTotal'"
								+ " onchange='javascript: inputTotalOnChange(event, this)'/>"
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
	
	var trs = $("#tableFacturaLineas > tbody:last > tr");
	if (trs.length == 0) {
		generarDummyRow();
	}
}

function selectServicioOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	var tdImporteUnitario = $(tds[1]);
	var tdCantidad = $(tds[2]);
	var tdNeto = $(tds[3]);
	var tdTotal = $(tds[4]);
	
	tdImporteUnitario.html("0");
	$(tdCantidad.children()[0]).val(0);
	tdNeto.html("0");
	$(tdTotal.children()[0]).val(0);
	
	calcularPieDeFactura();
	
	ServicioPrecioDWR.getById(
		$(element).val()
		, {
			callback: function(data) {
				tdImporteUnitario.html(data.precio.toFixed(0));
				
				$(tdCantidad.children()[0]).focus();
			}, async: false
		}
	);
}

function inputCantidadOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	var tdImporteUnitario = $(tds[2]);
	var tdNeto = $(tds[3]);
	var tdTotal = $(tds[4]);
	
	tdNeto.html((tdImporteUnitario.html() * $(element).val() / (1 + __PORCENTAJE_IVA)).toFixed(0));
	tdTotal.children("input").val($(element).val() * tdImporteUnitario.html());
	
	calcularPieDeFactura();
}

function inputTotalOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	var tdImporteUnitario = $(tds[2]);
	var tdCantidad = $(tds[3]);
	var tdNeto = $(tds[4]);
	
	if ($(element).val() > 0) {
		tdCantidad.children("input").val(
			($(element).val() / tdImporteUnitario.html()).toFixed(0)
		);
		tdNeto.html(
			($(element).val() / (1 + __PORCENTAJE_IVA)).toFixed(0)
		);
	} else {
		tdCantidad.children("input").val(0);
		tdNeto.html("0");
	}
	
	calcularPieDeFactura();
}

function calcularPieDeFactura() {
	var importeTotal = 0;
	
	var trsCobranzaMovimientos = $(".trCobranzaMovimiento");
	for (var i=0; i<trsCobranzaMovimientos.length; i++) {
		importeTotal += 1 * $($(trsCobranzaMovimientos[i]).children()[5]).html();
	}
	
	var trsServicioAdicional = $(".trServicioAdicional");
	for (var i=0; i<trsServicioAdicional.length; i++) {
		importeTotal += 1 * $($($(trsServicioAdicional[i]).children()[5]).children()[0]).val();
	}
	
	var importeSubtotal = importeTotal / (1 + __PORCENTAJE_IVA);
	var importeIVA = importeTotal - importeSubtotal;
	
	$("#divImporteSubtotal").html(importeSubtotal.toFixed(0));
	$("#divImporteIVA").html(importeIVA.toFixed(0));
	$("#divImporteTotal").html(importeTotal.toFixed(0));
}

function inputCancelarOnClick(event, element) {
	clearForm(true);
	
	$("#inputClienteDocumento").focus();
}

function inputGenerarFacturaOnClick(event, element) {
	var factura = {
		fecha: new Date(),
		nombre: $("#inputClienteId").val() != "" ? 
			$("#divClienteNombre").text() : $("#inputClienteNombre").val(),
		apellido: $("#inputClienteId").val() != "" ?
			$("#divClienteApellido").text() : $("#inputClienteApellido").val(),
		domicilio: $("#inputClienteId").val() != "" ?
			$("#divClienteDomicilio").text() : $("#inputClienteDomicilio").val(),
		telefono: $("#inputClienteId").val() != "" ?
			$("#divClienteTelefono").text() : $("#inputClienteTelefono").val(),
		documento: $("#inputClienteDocumento").val(),
		importeSubtotal: $("#divImporteSubtotal").html(),
		importeIVA: $("#divImporteIVA").html(),
		importeTotal: $("#divImporteTotal").html(),
		cliente: {
			id: $("#inputClienteId").val() != "" ? $("#inputClienteId").val() : __CLIENTE_GENERICO_ID
		},
		moneda: {
			id: 1
		},
		facturaLineas: [],
		uact: 1,
		fact: new Date(),
		term: 1
	};
	
	var cobranzaMovimientos = [];
	
	var numeroLinea = 1;
	
	var trsCobranzaMovimientos = $(".trCobranzaMovimiento");
	for (var i=0; i<trsCobranzaMovimientos.length; i++) {
		var tds = $(trsCobranzaMovimientos[i]).children();
		
		cobranzaMovimientos[cobranzaMovimientos.length] = {
			id: $(trsCobranzaMovimientos[i]).attr("id")
		};
		
		var tdServicio = $(tds[1]);
		var tdImporteUnitario = $(tds[2]);
		var tdCantidad = $(tds[3]);
		var tdNeto = $(tds[4]);
		
		factura.facturaLineas[factura.facturaLineas.length] = {
			numero: numeroLinea,
			detalle: tdServicio.html(),
			importeUnitario: tdImporteUnitario.html(),
			unidades: tdCantidad.html(),
			importeTotal: tdNeto.html(),
			servicio: {
				id: tdServicio.attr("id"),
				servicioTipo: {
					id: tdServicio.attr("stid")
				}
			},
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		numeroLinea++;
	}
	
	CobranzaTipoDocumentoDWR.getTipoDocumentoCobranzaManual(
		{
			callback: function(data) {
				var trsServicioAdicional = $(".trServicioAdicional");
				for (var i=0; i<trsServicioAdicional.length; i++) {
					var tds = $(trsServicioAdicional[i]).children();
					
					var tdServicio = $(tds[1]);
					var tdImporteUnitario = $(tds[2]);
					var tdCantidad = $(tds[3]);
					var tdNeto = $(tds[4]);
					
					var facturaLinea = {
						numero: numeroLinea,
						detalle: $(tdServicio.children()[0]).find("option:selected").text(),
						importeUnitario: tdImporteUnitario.html(),
						unidades: $(tdCantidad.children(0)).val(),
						importeTotal: tdNeto.html(),
						servicio: {
							id: $(tdServicio.children()[0]).find("option:selected").attr("id"),
							servicioTipo: {
								id: $(tdServicio.children()[0]).find("option:selected").attr("stid")
							}
						},
						uact: 1,
						fact: new Date(),
						term: 1	
					};
					
					factura.facturaLineas[factura.facturaLineas.length] = facturaLinea;
					
					cobranzaMovimientos[cobranzaMovimientos.length] = {
						fecha: factura.fecha,
						importe: facturaLinea.importeTotal,
						cobranzaTipoDocumento: data,
						moneda: factura.moneda,
						cliente: factura.cliente,
						servicio: facturaLinea.servicio,
						uact: 1,
						fact: new Date(),
						term: 1
					};
					
					numeroLinea++;
				}
			}, async: false
		}
	);
	
	FacturaDWR.facturarCobranzaMovimientos(
		factura
		, cobranzaMovimientos
		, {
			callback: function(data) {
				$("#inputFacturaId").val(data.id);
				$("#divFacturaNumero").html(data.numero);
				
				$("#divClienteNombre").html(data.nombre);
				$("#divClienteApellido").html(data.apellido);
				$("#divClienteDomicilio").html(data.domicilio);
				$("#divClienteTelefono").html(data.telefono);
				
				$("#tableFacturaLineas > tbody:last > tr").remove();
				
				for (var i=0; i<data.facturaLineas.length; i++) {
					$("#tableFacturaLineas > tbody:last").append(
						"<tr class='trCobranzaMovimiento'>"
							+ "<td>&nbsp;</td>"
							+ "<td class='tdServicio'>" 
								+ data.facturaLineas[i].detalle 
							+ "</td>"
							+ "<td class='tdImporteUnitario'>" 
								+ data.facturaLineas[i].importeUnitario 
							+ "</td>"
							+ "<td class='tdCantidad'>" 
								+ data.facturaLineas[i].unidades 
							+ "</td>"
							+ "<td class='tdNeto'>" 
								+ data.facturaLineas[i].importeTotal
							+ "</td>"
							+ "<td class='tdTotal'>" 
								+ (data.facturaLineas[i].importeTotal * (1 + __PORCENTAJE_IVA)).toFixed(0)
							+ "</td>"
						+ "</tr>"
					);
				}
				
				$("#inputAgregarServicio").prop("disabled", true);
				$("#inputGenerarFactura").prop("disabled", true);
				$("#inputImprimirFactura").prop("disabled", false);
			}, async: false
		}
	);
}

function inputImprimirFacturaOnClick(event, element) {
	window.open("/ParkingWEB/pages/facturacion/factura_print.jsp?id=" + $("#inputFacturaId").val());
}

function clearForm(clearAll) {
	$("#inputFacturaId").val("");
	$("#inputClienteId").val("");
	
	if (clearAll) {
		$("#inputClienteDocumento").val("");
	}
	
	$("#divClienteNombre").html("&nbsp;");
	$("#divClienteApellido").html("&nbsp;");
	$("#divClienteDomicilio").html("&nbsp;");
	$("#divClienteTelefono").html("&nbsp;");
	$("#divFacturaNumero").html("&nbsp;");
	
	$("#tableFacturaLineas > tbody:last > tr").remove();
	
	generarDummyRow();
	
	$("#divImporteSubtotal").html("&nbsp;");
	$("#divImporteIVA").html("&nbsp;");
	$("#divImporteTotal").html("&nbsp;");
	
	$("#inputAgregarServicio").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputImprimirFactura").prop("disabled", true);
}

function generarDummyRow() {
	$("#tableFacturaLineas > tbody:last").append(
		"<tr class='trDummyRow'>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
		+ "</tr>"
	);
}
