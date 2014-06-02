$(document).ready(function() {
	reloadData();
});

function reloadData() {
	UsuarioDWR.list(
		{
			callback: function(data) {
				$("#tableUsuarios > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableUsuarios > tbody:last").append(
						"<tr id='" + data[i].id + "'" 
							+ " onclick='javascript:trUsuarioOnClick(event, this);'>"
							+ "<td class='tdUsuarioLogin'>"
								+ "<div class='divUsuarioLogin'>"
									+ data[i].login
								+ "</div>"
							+ "</td>"
							+ "<td class='tdUsuarioNombre'>"
								+ "<div class='divUsuarioNombre'>" 
									+ data[i].nombre 
								+ "</div>"
							+ "</td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function trUsuarioOnClick(event, element) {
	document.getElementById("iFrameUsuario").src = "./usuario_edit.jsp?id=" + $(element).attr("id");
	showPopUp(document.getElementById("divIFrameUsuario"));
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameUsuario").src = "./usuario_edit.jsp";
	showPopUp(document.getElementById("divIFrameUsuario"));
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
	
	reloadData();
}