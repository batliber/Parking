$(document).ready(function() {
	ClienteDWR.list(
		{
			callback: function(data) {
				$("#tableClientes > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableClientes > tbody:last").append(
						"<tr id='" + data[i].id + "'>"
							+ "<td class='tdActions'>"
								+ "<div class='divEdit' onclick='javascript:inputEditOnClick(event, this, " + data[i].id + ")'>&nbsp;</div>"
								+ "<div class='divDelete' onclick='javascript:inputDeleteOnClick(event, this, " + data[i].id + ")'>&nbsp;</div>"
							+ "</td>"
							+ "<td class='tdClienteNombre'><div id='divClienteNombre" + data[i].id + "'>" + data[i].nombre + "</div></td>"
						+ "</tr>"
					);
				}
				
				$("#tableClientes > tbody:last").append(
					"<tr>"
						+ "<td class='tdActions'>"
							+ "<div class='divNew' onclick='javascript:inputNewOnClick(event, this)'>&nbsp;</div>"
						+ "</td>"
						+ "<td class='tdClienteNombre'><div>&nbsp;</div></td>"
					+ "</tr>"
				);
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