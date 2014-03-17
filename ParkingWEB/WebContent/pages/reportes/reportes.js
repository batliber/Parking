$(document).ready(function() {
	reloadData();
});

function reloadData() {
	FacturaDWR.list(
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
									+ data[i].cliente.nombre + " " + data[i].cliente.apellido
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaMoneda'>"
								+ "<div class='divFacturaMoneda'>" 
									+ data[i].moneda.abreviacion
								+ "</div>"
							+ "</td>"
							+ "<td class='tdFacturaImporte'>"
								+ "<div class='divFacturaImporte'>" 
									+ new Number(data[i].importeTotal).toFixed(2)
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function trFacturaOnClick(event, element) {
	document.getElementById("iFrameFactura").src = "/ParkingWEB/pages/factura/factura.jsp?id=" + $(element).attr("id");
	showPopUp(document.getElementById("divIFrameFactura"));
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
}