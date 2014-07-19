$(document).ready(function() {
	$("#inputFecha").val(formatLongDate(new Date()));
	
	UsuarioDWR.list(
		{
			callback: function(data) {
				for (var i=0; i<data.length; i++) {
					for (var i=0; i<data.length; i++) {
						$("#selectUsuario").append(
							"<option value='" + data[i].id + "'>" 
								+ data[i].nombre 
							+ "</option>"
						);
					}
				}
			}, async: false
		}
	);
	
	CajaMovimientoDWR.getUltimoMovimientoByCurrentUser(
		{
			callback: function(data) {
				if (data == null) {
					SeguridadDWR.getActiveUserData(
						{
							callback: function(userData) {
								$("#selectUsuario").val(userData.id);
							}, async: false
						}
					);
				}
			}, async: false
		}
	);
});

function inputValorOnChange(event, element) {
	recalcularTotal();
}

function recalcularTotal() {
	var elements = $("#tableCajaValores tr td input");
	
	var total = 0;
	for (var i=0; i<elements.length; i++) {
		total += $(elements[i]).attr("id") * $(elements[i]).val();
	}
	
	$("#divTotal").text(total);
}

function inputImprimirOnClick(event) {
	
}