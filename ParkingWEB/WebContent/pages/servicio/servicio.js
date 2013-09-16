$(document).ready(function() {
	ServicioPrecioDWR.listVigentes(
		{
			callback: function(data) {
				$("#tableServicios > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableServicios > tbody:last").append(
						"<tr id='" + data[i].id + "'>"
							+ "<td class='tdActions'>"
								+ "<div class='divEdit' onclick='javascript:inputEditOnClick(event, this, " + data[i].id + ")'>&nbsp;</div>"
								+ "<div class='divDelete' onclick='javascript:inputDeleteOnClick(event, this, " + data[i].id + ")'>&nbsp;</div>"
							+ "</td>"
							+ "<td class='tdServicioDescripcionServicioPrecio'><div id='divServicioDescripcionServicioPrecio" + data[i].id + "'>" + data[i].servicio.descripcion + "</div></td>"
							+ "<td class='tdMonedaAbreviacionServicioPrecio'><div id='divMonedaAbreviacionServicioPrecio" + data[i].id + "'>" + data[i].moneda.abreviacion + "</div></td>"
							+ "<td class='tdPrecioServicioPrecio'><div id='divPrecioServicioPrecio" + data[i].id + "'>"+ new Number(data[i].precio).toFixed(2) + "</div></td>"
						+ "</tr>"
					);
				}
				
				$("#tableServicios > tbody:last").append(
					"<tr>"
						+ "<td class='tdActions'>"
							+ "<div class='divNew' onclick='javascript:inputNewOnClick(event, this)'>&nbsp;</div>"
						+ "</td>"
						+ "<td class='tdServicioDescripcionServicioPrecio'><div>&nbsp;</div></td>"
						+ "<td class='tdMonedaAbreviacionServicioPrecio'><div>&nbsp;</div></td>"
						+ "<td class='tdPrecioServicioPrecio'><div>&nbsp;</div></td>"
					+ "</tr>"
				);
			}, async: false
		}
	);
});

function inputEditOnClick(event, element, id) {
	document.getElementById("iFrameServicioPrecio").src = "./servicio_edit.jsp?id=" + id;
	showPopUp(document.getElementById("divIFrameServicioPrecio"));
}

function inputDeleteOnClick(event, element, id) {
	var servicioPrecio = {
		id: id
	};
	
	ServicioPrecioDWR.remove(
		servicioPrecio,
		{
			callback: function(data) {
				$("#tableServicios > tbody:last > tr[id|='" + id + "']").remove();
			}, async: false
		}
	);
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameServicioPrecio").src = "./servicio_edit.jsp";
	showPopUp(document.getElementById("divIFrameServicioPrecio"));
}