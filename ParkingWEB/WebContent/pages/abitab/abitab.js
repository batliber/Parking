$(document).ready(function() {
//	$("#inputGenerarArchivo").prop("disabled", true);
	$("#inputImportarArchivo").prop("disabled", true);
	
	reloadData();
});

function inputArchivoOnChange(event) {
	$("#inputImportarArchivo").prop("disabled", false);
}

function reloadData() {
	CobranzaMovimientoDWR.listArchivos(
		{
			callback: function(data) {
				$("#tableArchivos > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableArchivos > tbody:last").append(
						"<tr>"
							+ "<td>"
								+ "<div class='divArchivosNombre'>"
									+ "<a href='/ParkingWEB/Download?fn=" + data[i].nombre + "'>" 
										+ data[i].nombre
									+ "</a>"
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}
		}
	);
}

function inputGenerarArchivoOnClick(event, element) {
	CobranzaMovimientoDWR.generarArchivoCobranzaAbitabByFecha(
		new Date(),
		{
			callback: function(data) {
				if (data != null) {
					alert("Archivo generado: " + data);
					
					reloadData();
				}
			}, async: false
		}
	);
}

function inputImportarArchivoOnClick(event, element) {
	$("#formSubirArchivo").submit();
}