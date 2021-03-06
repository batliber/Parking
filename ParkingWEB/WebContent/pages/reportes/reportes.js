$(document).ready(function() {
	var hoy = new Date();
	
	$("#inputHasta").val(formatShortDate(hoy));
	
	hoy.setMonth(hoy.getMonth() - 1);
	$("#inputDesde").val(formatShortDate(hoy));
	
	reloadData();
});

function reloadData() {
	FacturaDWR.listDesdeHasta(
		parseShortDate($("#inputDesde").val()),
		parseShortDate($("#inputHasta").val()),
		{
			callback: function(data) {
				$("#tableFacturas > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableFacturas > tbody:last").append(
						"<tr id='" + data[i].id + "'" 
							+ " onclick='javascript:trFacturaOnClick(event, this);'>"
							+ "<td class='tdFacturaNumero'>"
								+ "<div class='divFacturaNumero'>"
									+ data[i].numero
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaFecha'>"
								+ "<div class='divFacturaFecha'>" 
									+ formatShortDate(data[i].fecha, true)
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaCliente'>"
								+ "<div class='divFacturaCliente'>" 
									+ data[i].apellido + ", " + data[i].nombre
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaMoneda'>"
								+ "<div class='divFacturaMoneda'>" 
									+ data[i].moneda.abreviacion
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaImporteSubtotal'>"
								+ "<div class='divFacturaImporteSubtotal'>" 
									+ new Number(data[i].importeSubtotal).toFixed(0)
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaImporteIVA'>"
								+ "<div class='divFacturaImporteIVA'>" 
									+ new Number(data[i].importeIVA).toFixed(0)
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaImporteTotal'>"
								+ "<div class='divFacturaImporteTotal'>" 
									+ new Number(data[i].importeTotal).toFixed(0)
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaAnulada'>"
								+ "<div class='divFacturaAnulada'>" 
									+ (data[i].anulada ? "Si" : "No")
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function inputDesdeOnChange(event, element) {
	reloadData();
}

function inputHastaOnChange(event, element) {
	reloadData();
}

function trFacturaOnClick(event, element) {
	document.getElementById("iFrameFactura").src = "/ParkingWEB/pages/factura/factura.jsp?id=" + $(element).attr("id");
	showPopUp(document.getElementById("divIFrameFactura"));
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
	
	reloadData();
}

function inputExportarAExcelOnClick(event, element) {
	FacturaDWR.exportarListDesdeHastaAExcel(
		parseShortDate($("#inputDesde").val()),
		parseShortDate($("#inputHasta").val()),
		{
			callback: function(data) {
				if (data != null) {
					alert("Archivo generado: " + data);
				}
			}, async: false
		}
	);
}