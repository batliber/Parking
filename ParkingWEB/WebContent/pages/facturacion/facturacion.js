var servicios = 0;

$(document).ready(function() {
	$("#inputClienteDocumento").focus();
});

function inputClienteDocumentoOnChange(event, element) {
	$("#tableFacturaLineas > tbody:last > tr").remove();
	
	ClienteDWR.getByDocumento(
		$("#inputClienteDocumento").val(),
		{
			callback: function(data) {
				$("#divClienteNombre").html(data.nombre);
				$("#divClienteDomicilio").html(data.domicilio);
				$("#divClienteTelefono").html(data.telefono);
				
				CobranzaMovimientoDWR.listSinFacturarByCliente(
					data
					, { 
						callback: function(dataCobranza) {
							var i = 0;
							for (i=0; i<dataCobranza.length; i++) {
								$("#tableFacturaLineas > tbody:last").append(
									"<tr class='trCobranzaMovimiento'>"
										+ "<td>" + dataCobranza[i].servicio.descripcion  + "</td>"
										+ "<td>&nbsp;</td>"
										+ "<td>&nbsp;</td>"
										+ "<td>" + dataCobranza[i].importe + "</td>"
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
									+ "</tr>"
								);
							}
							
							calcularPieDeFactura();
						}, async: false
					}
				);
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
						"<option value='" + data[i].id + "'>" 
							+ data[i].servicio.descripcion 
						+ "</option>";
				}
				
				$("#tableFacturaLineas > tbody:last").append(
					"<tr class='trServicioAdicional'>"
						+ "<td>"
							+ "<select id='selectServicio" + servicios 
								+ "' onchange='javascript:selectServicioOnChange(event, this)'>"
								+ options
							+ "</select>" 
						+ "</td>"
						+ "<td>&nbsp;</td>"
						+ "<td>"
							+ "<input type='text'" 
								+ " onchange='javascript:inputCantidadOnChange(event, this)'/>" 
						+ "</td>"
						+ "<td>"
							+ "<input type='text' class='inputTotal'"
								+ " onchange='javascript: inputTotalOnChange(event, this)'/>"
						+ "</td>"
					+ "</tr>"
				);
				
				servicios++;
			}, async: false
		}
	);
}

function selectServicioOnChange(event, element) {
	ServicioPrecioDWR.getById(
		$(element).val()
		, {
			callback: function(data) {
				var tds = $(element).parent().siblings();
				
				$(tds[0]).html(data.precio);
			}, async: false
		}
	);
}

function inputCantidadOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	$(tds[2]).children("input").val($(element).val() * $(tds[1]).html());
	
	calcularPieDeFactura();
}

function inputTotalOnChange(event, element) {
	var tds = $(element).parent().siblings();
	
	$(tds[2]).children("input").val($(element).val() / $(tds[1]).html());
	
	calcularPieDeFactura();
}

function calcularPieDeFactura() {
	var importeSubtotal = 0;
	
	var trsCobranzaMovimientos = $(".trCobranzaMovimiento");
	for (var i=0; i<trsCobranzaMovimientos.length; i++) {
		importeSubtotal += 1 * $($(trsCobranzaMovimientos[i]).children()[3]).html();
	}
	
	var trsServicioAdicional = $(".trServicioAdicional");
	for (var i=0; i<trsServicioAdicional.length; i++) {
		importeSubtotal += 1 * $($($(trsServicioAdicional[i]).children()[3]).children()[0]).val();
	}
	
	var importeIVA = importeSubtotal * 0.22;
	var importeTotal = importeSubtotal + importeIVA;
	
	$("#divImporteSubtotal").html(importeSubtotal);
	$("#divImporteIVA").html(importeIVA);
	$("#divImporteTotal").html(importeTotal);
}

function inputGenerarFacturaOnClick(event, element) {
	
}