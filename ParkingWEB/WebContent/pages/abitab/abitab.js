$(document).ready(function() {
	$("#inputGenerarArchivo").prop("disabled", true);
	$("#inputImportarArchivo").prop("disabled", true);
});

function inputArchivoOnChange(event) {
	$("#inputImportarArchivo").prop("disabled", false);
}

function inputGenerarArchivoOnClick(event, element) {
	GeneracionArchivoABITABDWR.generarArchivoABITAB(
		{
			callback: function(data) {
				
			}, async: false
		}
	);
}

function inputImportarArchivoOnClick(event, element) {
	$("#formSubirArchivo").submit();
}