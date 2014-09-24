$(document).ready(function() {
	reloadData();
});

function reloadData() {
	ClienteDWR.list(
		{
			callback: function(data) {
				$("#tableClientes > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableClientes > tbody:last").append(
						"<tr id='" + data[i].id + "'" 
							+ " onclick='javascript:trClienteOnClick(event, this);'>"
							+ "<td class='tdClienteDocumento'>"
								+ "<div class='divClienteDocumento'>"
									+ data[i].documento 
								+ "</div>"
							+ "</td>"
							+ "<td class='tdClienteNombre'>"
								+ "<div class='divClienteNombre'>" 
									+ data[i].nombre 
								+ "</div>"
							+ "</td>"
							+ "<td class='tdClienteApellido'>"
								+ "<div class='divClienteApellido'>" 
									+ (data[i].apellido != null ? data[i].apellido : "&nbsp;")
								+ "</div>"
							+ "</td>"
							+ "<td class='tdClienteDomicilio'>"
								+ "<div class='divClienteDomicilio'>" 
									+ (data[i].domicilio != null ? data[i].domicilio : "&nbsp;")
								+ "</div>"
							+ "</td>"
							+ "<td class='tdClienteTelefono'>"
								+ "<div class='divClienteTelefono'>" 
									+ (data[i].telefono != null ? data[i].telefono : "&nbsp;")
								+ "</div>"
							+ "</td>"
							+ "<td class='tdClienteFechaAlta'>"
								+ "<div class='divClienteFechaAlta'>" 
									+ formatShortDate(data[i].fechaAlta)
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function trClienteOnClick(event, element) {
	document.getElementById("iFrameCliente").src = "./cliente_edit.jsp?id=" + $(element).attr("id");
	showPopUp(document.getElementById("divIFrameCliente"));
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameCliente").src = "./cliente_edit.jsp";
	showPopUp(document.getElementById("divIFrameCliente"));
}

function inputExportarAExcelOnClick(event) {
	ClienteDWR.exportarAExcel(
		{
			callback: function(data) {
				if (data != null) {
					alert("Archivo generado: " + data);
				}
			}, async: false
		}
	);
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
	
	reloadData();
}