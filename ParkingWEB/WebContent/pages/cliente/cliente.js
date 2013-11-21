$(document).ready(function() {
	ClienteDWR.list(
		{
			callback: function(data) {
				$("#tableClientes > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableClientes > tbody:last").append(
						"<tr id='" + data[i].id + "'>"
							+ "<td class='tdClienteDocumento'><div class='divClienteDocumento'>" + data[i].documento + "</div></td>"
							+ "<td class='tdClienteNombre'><div class='divClienteNombre'>" + data[i].nombre + "</div></td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
});

function inputEditOnClick(event, element, id) {
	document.getElementById("iFrameCliente").src = "./cliente_edit.jsp?id=" + id;
	showPopUp(document.getElementById("divIFrameCliente"));
}

function inputDeleteOnClick(event, element, id) {
	var cliente = {
		id: id
	};
	
	ClienteDWR.remove(
		cliente,
		{
			callback: function(data) {
				$("#tableClientes > tbody:last > tr[id|='" + id + "']").remove();
			}, async: false
		}
	);
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameCliente").src = "./cliente_edit.jsp";
	showPopUp(document.getElementById("divIFrameCliente"));
}