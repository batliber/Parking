$(document).ready(function() {
	reloadData();
});

function reloadData() {
	$("#tableCobranzaMovimientos > tbody:last > tr").remove();
	
	CobranzaMovimientoDWR.listDeudas(
		{
			callback: function(data) {
				for (var i=0; i<data.length; i++) {
					$("#tableCobranzaMovimientos > tbody:last").append(
						"<tr>"
							+ "<td class='tdCobranzaMovimientoClienteDocumento'>"
								+ "<div class='divCobranzaMovimientoClienteDocumento'>"
									+ data[i].cliente.documento
								+ "</div>"
							+ "</td>"
							+ "<td class='tdCobranzaMovimientoClienteNombre'>"
								+ "<div class='divCobranzaMovimientoClienteNombre'>"
									+ data[i].cliente.nombre
								+ "</div>"
							+ "</td>"
							+ "<td class='tdCobranzaMovimientoClienteApellido'>"
								+ "<div class='divCobranzaMovimientoClienteApellido'>"
									+ data[i].cliente.apellido
								+ "</div>"
							+ "</td>"
							+ "<td class='tdCobranzaMovimientoMoneda'>"
								+ "<div class='divCobranzaMovimientoMoneda'>"
									+ data[i].moneda.abreviacion
								+ "</div>"
							+ "</td>"
							+ "<td class='tdCobranzaMovimientoImporte'>"
								+ "<div class='divCobranzaMovimientoImporte'>"
									+ data[i].importe.toFixed(2)
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function inputGenerarCobranzaOnClick(event) {
	CobranzaMovimientoDWR.generarCobranzaMovimientosByFecha(
		new Date(),
		{
			callback: function(data) {
				reloadData();
			}, async: false
		}
	);
}

function inputDeshacerCobranzaOnClick(event) {
	if (confirm("¿Desea anular la última cobranza generada? Esta acción no puede deshacerse.")) {
		CobranzaMovimientoDWR.deshacerCobranzaMovimientosByFecha(
			new Date(),
			{
				callback: function(data) {
					reloadData();
				}, async: false
			}
		);
	}
}

function inputActualizarOnClick(event) {
	reloadData();
}

function inputAjusteOnClick(event) {
	document.getElementById("iFrameAjuste").src = "./ajuste.jsp";
	showPopUp(document.getElementById("divIFrameAjuste"));
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
	
	reloadData();
}