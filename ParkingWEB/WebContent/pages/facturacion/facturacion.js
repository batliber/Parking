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
												+ "title='" + dataCobranza[i].cobranzaTipoDocumento.descripcion + "'>"
												+ dataCobranza[i].servicio.descripcion
											+ "</td>"
											+ "<td class='tdImporteUnitario'>" 
												+ (dataCobranza[i].importe / (1 + __PORCENTAJE_IVA)).toFixed(0) 
											+ "</td>"
											+ "<td class='tdCantidad'>1</td>"
											+ "<td class='tdTotal'>" 
												+ (dataCobranza[i].importe / (1 + __PORCENTAJE_IVA)).toFixed(0)
											+ "</td>"
										+ "</tr>"
									);
								}
								
								if (i == 0) {
									$("#tableFacturaLineas > tbody:last").append(
										"<tr>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
											+ "<td>&nbsp;</td>"
										+ "</tr>"
									);
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
						+ "<td>"
							+ "<select id='selectServicio" + servicios 
								+ "' onchange='javascript:selectServicioOnChange(event, this)'>"
								+ options
							+ "</select>" 
						+ "</td>"
						+ "<td class='tdImporteUnitario'>&nbsp;</td>"
						+ "<td>"
							+ "<input type='text' value='0' class='inputCantidad'" 
								+ " onchange='javascript:inputCantidadOnChange(event, this)'/>" 
						+ "</td>"
						+ "<td>"
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
}

function selectServicioOnChange(event, element) {
	var tds = $(element).parent().siblings();
	$(tds[1]).html("&nbsp;");
	$($(tds[2]).children()[0]).val(0);
	$($(tds[3]).children()[0]).val(0);
	
	calcularPieDeFactura();
	
	ServicioPrecioDWR.getById(
		$(element).val()
		, {
			callback: function(data) {
				var tds = $(element).parent().siblings();
				
				$(tds[1]).html((data.precio / (1 + __PORCENTAJE_IVA)).toFixed(2));
				
				$($(tds[2]).children()[0]).focus();
			}, async: false
		}
	);
}

function inputCantidadOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	$(tds[3]).children("input").val($(element).val() * $(tds[2]).html());
	
	calcularPieDeFactura();
}

function inputTotalOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	if ($(tds[2]).html() > 0) {
		$(tds[3]).children("input").val($(element).val() / $(tds[2]).html());
	} else {
		$(tds[3]).children("input").val(0);
	}
	
	calcularPieDeFactura();
}

function calcularPieDeFactura() {
	var importeSubtotal = 0;
	
	var trsCobranzaMovimientos = $(".trCobranzaMovimiento");
	for (var i=0; i<trsCobranzaMovimientos.length; i++) {
		importeSubtotal += 1 * $($(trsCobranzaMovimientos[i]).children()[4]).html();
	}
	
	var trsServicioAdicional = $(".trServicioAdicional");
	for (var i=0; i<trsServicioAdicional.length; i++) {
		importeSubtotal += 1 * $($($(trsServicioAdicional[i]).children()[4]).children()[0]).val();
	}
	
	var importeIVA = importeSubtotal * __PORCENTAJE_IVA;
	var importeTotal = importeSubtotal + importeIVA;
	
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
		
		factura.facturaLineas[factura.facturaLineas.length] = {
			numero: numeroLinea,
			detalle: $(tds[1]).html(),
			importeUnitario: $(tds[2]).html(),
			unidades: $(tds[3]).html(),
			importeTotal: $(tds[4]).html(),
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
		
		numeroLinea++;
	}
	
	CobranzaTipoDocumentoDWR.getTipoDocumentoCobranzaManual(
		{
			callback: function(data) {
				var trsServicioAdicional = $(".trServicioAdicional");
				for (var i=0; i<trsServicioAdicional.length; i++) {
					var tds = $(trsServicioAdicional[i]).children();
					
					var facturaLinea = {
						numero: numeroLinea,
						detalle: $($(tds[1]).children()[0]).find("option:selected").text(),
						importeUnitario: $(tds[2]).html(),
						unidades: $($(tds[3]).children(0)).val(),
						importeTotal: $($(tds[4]).children(0)).val(),
						servicio: {
							id: $($(tds[1]).children()[0]).find("option:selected").attr("id"),
							servicioTipo: {
								id: $($(tds[1]).children()[0]).find("option:selected").attr("stid")
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
							+ "<td>" + data.facturaLineas[i].detalle + "</td>"
							+ "<td>" + data.facturaLineas[i].importeUnitario + "</td>"
							+ "<td>" + data.facturaLineas[i].unidades + "</td>"
							+ "<td>" + data.facturaLineas[i].importeTotal + "</td>"
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
	
	$("#tableFacturaLineas > tbody:last").append(
		"<tr>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
			+ "<td>&nbsp;</td>"
		+ "</tr>"
	);
	
	$("#divImporteSubtotal").html("&nbsp;");
	$("#divImporteIVA").html("&nbsp;");
	$("#divImporteTotal").html("&nbsp;");
	
	$("#inputAgregarServicio").prop("disabled", true);
	$("#inputGenerarFactura").prop("disabled", true);
	$("#inputImprimirFactura").prop("disabled", true);
}