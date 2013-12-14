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
									+ data[i].moneda.descripcion
								+ "</div>"
							+ "</td>"
							+ "<td class='tdCobranzaMovimientoImporte'>"
								+ "<div class='divCobranzaMovimientoImporte'>"
									+ data[i].importe
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

function inputActualizarOnClick(event) {
	reloadData();
}