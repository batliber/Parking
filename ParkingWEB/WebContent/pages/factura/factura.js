var factura = null;

$(document).ready(function() {
	if (matricula != null && matricula != "") {
		FacturaDWR.generateFacturaByMatricula(
			matricula,
			{
				callback: function(data)  {
					factura = data;
					
					$("#divFacturaFecha").text(formatShortDate(data.fecha));
					$("#divFacturaClienteNombre").text(data.cliente.nombre);
					$("#divFacturaMonedaDescripcion").text(data.moneda.descripcion);
					
					$("#tableFacturaLineas > tbody:last > tr").remove();
					
					for (var i=0; i<data.facturaLineas.length; i++) {
						$("#tableFacturaLineas > tbody:last").append(
							"<tr>"
								+ "<td><div>" + data.facturaLineas[i].numero + "</div></td>"
								+ "<td><div>" + data.facturaLineas[i].detalle + "</div></td>"
								+ "<td><div>" + data.moneda.abreviacion + " " + new Number(data.facturaLineas[i].importeUnitario).toFixed(2) + "</div></td>"
								+ "<td><div>" + new Number(data.facturaLineas[i].unidades).toFixed(2) + "</div></td>"
								+ "<td><div id='divImporteTotalFacturaLinea" + data.facturaLineas[i].numero + "'>" 
									+ data.moneda.abreviacion 
									+ " <input type='text' id='inputImporteTotalFacturaLinea" + data.facturaLineas[i].numero + "'"
									+ " value='" + new Number(data.facturaLineas[i].importeTotal).toFixed(2) + "'" 
									+ " onchange='javascript:inputImporteTotalFacturaLineaOnChange(event)'/>"
								+ "</div></td>"
							+ "</tr>"
						);
					}
					
					$("#divFacturaImporteSubtotal").text(data.moneda.abreviacion + " " + new Number(data.importeSubtotal).toFixed(2));
					$("#divFacturaImporteIVA").text(data.moneda.abreviacion + " " + new Number(data.importeIVA).toFixed(2));
					$("#divFacturaImporteTotal").text(data.moneda.abreviacion + " " + new Number(data.importeTotal).toFixed(2));
				}, async: false
			}
		);
	}
});

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
	$("#inputGrabarFactura").hide();
	
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
			}, async: false
		}
	);
}