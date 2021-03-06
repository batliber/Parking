var factura = null;

$(document).ready(function() {
	$("#inputGrabarFactura").prop("disabled", true);
	$("#inputAnularFactura").prop("disabled", true);
	$("#inputImprimirFactura").prop("disabled", true);
	
	if (matricula != null && matricula != "") {
		FacturaDWR.generateFacturaByMatricula(
			matricula,
			{
				callback: function(data)  {
					factura = data;
					
					showFactura();
					
					$("#inputGrabarFactura").prop("disabled", false);
				}, async: false
			}
		);
	} else if (id != null && id != "") {
		FacturaDWR.getById(
			id,
			{
				callback: function(data)  {
					factura = data;
					
					showFactura();
					
					$("#inputAnularFactura").prop("disabled", false);
				}, async: false
			}
		);
	} else {
		$("#divFacturaNumero").append("<input id='inputFacturaNumero' type='text' onchange='javascript:inputFacturaNumeroOnChange(event)'/>");
		
		$("#inputFacturaNumero").focus();
	}
});

function showFactura() {
	if (factura.numero != null) {
		$("#divFacturaNumero").text(factura.numero);
	}
	$("#divFacturaFecha").text(formatShortDate(factura.fecha));
	$("#divFacturaClienteNombre").text(
		factura.documento + " - " + factura.apellido + ", " + factura.nombre
	);
	$("#divFacturaMonedaDescripcion").text(factura.moneda.descripcion);
	$("#divFacturaAnulada").text(factura.anulada ? "Si" : "No");
	
	$("#tableFacturaLineas > tbody:last > tr").remove();
	
	for (var i=0; i<factura.facturaLineas.length; i++) {
		$("#tableFacturaLineas > tbody:last").append(
			"<tr>"
				+ "<td class='tdNumero'><div>" + factura.facturaLineas[i].numero + "</div></td>"
				+ "<td class='tdDetalle'><div>" + factura.facturaLineas[i].detalle + "</div></td>"
				+ "<td class='tdImporteUnitario'>"
					+ "<div>" + new Number(factura.facturaLineas[i].importeUnitario).toFixed(0) + "</div>"
				+ "</td>"
				+ "<td class='tdUnidades'>"
					+ "<div>" + new Number(factura.facturaLineas[i].unidades).toFixed(0) + "</div></td>"
				+ "<td class='tdImporteTotal'>" 
					+ "<div id='divImporteTotalFacturaLinea" + factura.facturaLineas[i].numero + "'>" 
						+ ((matricula != null && matricula != "") ?
							" <div>"
								+ "<input type='text'"
									+ " id='inputImporteTotalFacturaLinea" + factura.facturaLineas[i].numero + "'"
									+ " value='" + new Number(factura.facturaLineas[i].importeTotal).toFixed(0) + "'"
									+ " onchange='javascript:inputImporteTotalFacturaLineaOnChange(event)'/>"
							+ "</div>"
							: "<div>" 
								+ new Number(factura.facturaLineas[i].importeTotal).toFixed(0) 
							+ "</div>"
						)
					+ "</div>"
				+ "</td>"
			+ "</tr>"
		);
	}
	
	$("#divFacturaImporteSubtotal").text(new Number(factura.importeSubtotal).toFixed(0));
	$("#divFacturaImporteIVA").text(new Number(factura.importeIVA).toFixed(0));
	$("#divFacturaImporteTotal").text(new Number(factura.importeTotal).toFixed(0));
}

function clearForm() {
	$("#inputGrabarFactura").prop("disabled", true);
	$("#inputImprimirFactura").prop("disabled", true);
	$("#inputAnularFactura").prop("disabled", true);
	
	$("#divFacturaFecha").html("&nbsp;");
	$("#divFacturaClienteNombre").html("&nbsp;");
	$("#divFacturaMonedaDescripcion").html("&nbsp;");
	$("#divFacturaAnulada").html("&nbsp;");
	
	$("#tableFacturaLineas > tbody:last > tr").remove();
	
	$("#tableFacturaLineas > tbody:last").append(
		"<tr>"
			+ "<td><div>&nbsp;</div></td>"
			+ "<td><div>&nbsp;</div></td>"
			+ "<td><div>&nbsp;</div></td>"
			+ "<td><div>&nbsp;</div></td>"
			+ "<td><div>&nbsp;</div></td>"
		+ "</tr>"
	);
	
	$("#divFacturaImporteSubtotal").html("&nbsp;");
	$("#divFacturaImporteIVA").html("&nbsp;");
	$("#divFacturaImporteTotal").html("&nbsp;");
}

function inputFacturaNumeroOnChange(event) {
	clearForm();
	
	if ($("#inputFacturaNumero").val() != "") {
		FacturaDWR.getByNumero(
			$("#inputFacturaNumero").val(),
			{
				callback: function(data) {
					if (data != null) {
						factura = data;
						
						showFactura();
					} else {
						
					}
				}, async: false
			}
		);
	}
}

function inputImporteTotalFacturaLineaOnChange(event) {
	factura.importeSubtotal = 0;
	for (var i=0; i<factura.facturaLineas.length; i++) {
		factura.facturaLineas[i].importeTotal = new Number($("#inputImporteTotalFacturaLinea" + factura.facturaLineas[i].numero).val());
		factura.importeSubtotal += factura.facturaLineas[i].importeTotal;
	}
	factura.importeIVA = factura.importeSubtotal * 0.22;
	factura.importeTotal = new Number(factura.importeSubtotal + factura.importeIVA);
	
	$("#divFacturaImporteSubtotal").text(factura.moneda.abreviacion + " " + new Number(factura.importeSubtotal).toFixed(2));
	$("#divFacturaImporteIVA").text(factura.moneda.abreviacion + " " + new Number(factura.importeIVA).toFixed(2));
	$("#divFacturaImporteTotal").text(factura.moneda.abreviacion + " " + new Number(factura.importeTotal).toFixed(2));
}

function inputGrabarFacturaOnClick(event) {
	$("#inputGrabarFactura").prop("disabled", true);
	
	for (var i=0; i<factura.facturaLineas.length; i++) {
		factura.facturaLineas[i].importeTotal = $("#inputImporteTotalFacturaLinea" + factura.facturaLineas[i].numero).val();
		
		$("#divImporteTotalFacturaLinea" + factura.facturaLineas[i].numero).text(new Number(factura.facturaLineas[i].importeTotal).toFixed(2));
	}
	
	FacturaDWR.saveAndCloseRegistro(
		factura,
		matricula,
		{
			callback: function(data) {
				$("#divFacturaNumero").text(data.numero);
				
				$("#inputImprimirFactura").prop("disabled", false);
			}, async: false
		}
	);
}

function inputImprimirFacturaOnClick(event) {
	window.print();
}

function inputAnularFacturaOnClick(event) {
	if (confirm("�Desea anular la Factura?")) {
		FacturaDWR.anularFacturaById(
			factura.id,
			{
				callback: function(data) {
					if (data != null) {
						factura = data;
						
						alert("Factura anulada correctamente.");
						
						showFactura();
					}
				}, async: false
			}
		);
	}
}