$(document).ready(function() {
	VehiculoDWR.list(
		{
			callback: function(data) {
				$("#tableVehiculos > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableVehiculos > tbody:last").append(
						"<tr id='" + data[i].id + "'>"
							+ "<td class='tdVehiculoMatricula'><div id='divVehiculoMatricula" + data[i].id + "'>" + data[i].matricula + "</div></td>"
							+ "<td class='tdVehiculoDescripcion'><div id='divVehiculoDescripcion" + data[i].id + "'>" + data[i].descripcion + "</div></td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
});

function inputEditOnClick(event, element, id) {
	document.getElementById("iFrameVehiculo").src = "./vehiculo_edit.jsp?id=" + id;
	showPopUp(document.getElementById("divIFrameVehiculo"));
}

function inputDeleteOnClick(event, element, id) {
	var vehiculo = {
		id: id
	};
	
	VehiculoDWR.remove(
		vehiculo,
		{
			callback: function(data) {
				$("#tableVehiculos > tbody:last > tr[id|='" + id + "']").remove();
			}, async: false
		}
	);
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameVehiculo").src = "./vehiculo_edit.jsp";
	showPopUp(document.getElementById("divIFrameVehiculo"));
}