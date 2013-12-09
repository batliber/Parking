$(document).ready(function() {
//	$("#inputGenerarArchivo").prop("disabled", true);
	$("#inputImportarArchivo").prop("disabled", true);
});

function inputArchivoOnChange(event) {
	$("#inputImportarArchivo").prop("disabled", false);
}

function inputGenerarArchivoOnClick(event, element) {
	CobranzaMovimientoDWR.generarArchivoCobranzaAbitabByFecha(
		new Date(),
		{
			callback: function(data) {
				if (data != null) {
					alert("Archivo generado: " + data);
				}
			}, async: false
		}
	);
}

function inputImportarArchivoOnClick(event, element) {
	$("#formSubirArchivo").submit();
}