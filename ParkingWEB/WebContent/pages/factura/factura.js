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
					
					for (var i=0; i<data.facturaLineas.length; i++) {
						$("#tableFacturaLineas > tbody:last").append(
							"<tr>"
								+ "<td><div>" + data.facturaLineas[i].numero + "</div></td>"
								+ "<td><div>" + data.facturaLineas[i].detalle + "</div></td>"
								+ "<td><div>" + data.moneda.abreviacion + " " + new Number(data.facturaLineas[i].importeUnitario).toFixed(2) + "</div></td>"
								+ "<td><div>" + new Number(data.facturaLineas[i].unidades).toFixed(2) + "</div></td>"
								+ "<td><div>" + data.moneda.abreviacion + " <input type='text' id='inputImporteTotalFacturaLinea" + data.facturaLineas[i].numero + "' value='" + new Number(data.facturaLineas[i].importeTotal).toFixed(2) + "'/></div></td>"
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

function inputGrabarFacturaOnClick(event) {
	for (var i=0; i<factura.facturaLineas.length; i++) {
		factura.facturaLineas[i].importeTotal = $("#inputImporteTotalFacturaLinea" + factura.facturaLineas[i].numero).val();
	}
	
	FacturaDWR.saveAndCloseRegistro(
		factura,
		matricula,
		{
			callback: function(data) {
				$("#divFacturaNumero").text(data.numero);
				
				$("#inputGrabarFactura").hide();
			}, async: false
		}
	);
}