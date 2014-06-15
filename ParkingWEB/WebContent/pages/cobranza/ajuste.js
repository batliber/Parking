$(document).ready(function() {
	$("#inputFecha").val(formatShortDate(new Date()));
	
	ClienteDWR.list(
		{
			callback: function (data) {
				for (var i=0; i<data.length; i++) {
					$("#selectCliente").append(
						"<option value='" + data[i].id + "'>" 
							+ data[i].apellido + ", " + data[i].nombre 
						+ "</option>"
					);
				}
			}, async: false
		}
	);
	
	ServicioPrecioDWR.listVigentes(
		{
			callback: function(data) {
				for (var i=0; i<data.length; i++) {
					$("#selectServicio").append(
						"<option value='" + data[i].servicio.id + "'>" 
							+ data[i].servicio.descripcion
						+ "</option>"
					);
				}
			}, async: false
		}
	);
	
	ServicioPrecioDWR.getPrecioVigenteParkingMensual(
		{
			callback: function (data) {
				$("#selectServicio").val(data.servicio.id);
			}, async: false
		}
	);
	
	CobranzaTipoDocumentoDWR.list(
		{
			callback: function (data) {
				for (var i=0; i<data.length; i++) {
					$("#selectCobranzaTipoDocumento").append(
						"<option value='" + data[i].id + "'>" + data[i].descripcion + "</option>"
					);
				}
			}, async: false
		}
	);
	
	CobranzaTipoDocumentoDWR.getTipoDocumentoAjusteCobranza(
		{
			callback: function (data) {
				$("#selectCobranzaTipoDocumento").val(data.id);
			}, async: false
		}
	);
	
	MonedaDWR.list(
		{
			callback: function (data) {
				for (var i=0; i<data.length; i++) {
					$("#selectMoneda").append(
						"<option value='" + data[i].id + "'>" + data[i].descripcion + "</option>"
					);
				}
			}, async: false
		}
	);
});

function inputGrabarAjusteOnClick(event) {
	var cobranzaMovimiento = {
		fecha: new Date(),
		importe: $("#inputImporte").val(),
		cobranzaTipoDocumento: {
			id: $("#selectCobranzaTipoDocumento").val()
		},
		moneda: {
			id: $("#selectMoneda").val()
		},
		cliente: {
			id: $("#selectCliente").val()
		},
		servicio: {
			id: $("#selectServicio").val()
		},
		
		uact: 1,
		fact: new Date(),
		term: 1
	};
	
	CobranzaMovimientoDWR.save(
		cobranzaMovimiento,
		{
			callback: function (data) {
				
			}, async: false
		}
	);
}