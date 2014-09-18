var cliente = null;

$(document).ready(function() {
	$("#inputEliminarCliente").prop("disabled", true);
	
	if (id != null) {
		ClienteDWR.getById(
			id,
			{
				callback: function(data) {
					cliente = data;
					
					$("#divCliente").html(
						data.documento + " - " + 
						data.apellido + ", " + data.nombre
					);
				}, async: false
			}
		);
		
		CobranzaMovimientoDWR.listHistorialByCliente(
			cliente,
			{
				callback: function(data) {
					$("#tableCobranzaMovimientos > tbody:last > tr").remove();
					
					for (var i=0; i<data.length; i++) {
						$("#tableCobranzaMovimientos > tbody:last").append(
							"<tr>"
								+ "<td class='tdFecha'>"
									+ "<div>" + formatShortDate(data[i].fecha) + "</div>"
								+ "</td>"
								+ "<td class='tdCobranzaTipoDocumento'>"
									+ "<div>" + data[i].cobranzaTipoDocumento.descripcion + "</div>"
								+ "</td>"
								+ "<td class='tdServicio'>"
									+ "<div>" + data[i].servicio.descripcion + "</div>"
								+ "</td>"
								+ "<td class='tdMoneda'>"
									+ "<div>" + data[i].moneda.abreviacion + "</div>"
								+ "</td>"
								+ "<td class='tdImporte'>"
									+ "<div>" + data[i].importe.toFixed(0) + "</div>"
								+ "</td>"
								+ "<td class='tdFacturaNumero'>"
									+ "<div>" 
										+ (data[i].factura != null ? data[i].factura.numero  : "&nbsp;")
									+ "</div>"
								+ "</td>"
							+ "</tr>"
						);
					}
					
					if (data.length == 0) {
						$("#tableCobranzaMovimientos > tbody:last").append(
							"<tr class='trDummyRow'>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
								+ "<td><div>&nbsp;</div></td>"
							+ "</tr>"
						);
					}
				}, async: false
			}
		);
	}
});