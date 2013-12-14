$(document).ready(function() {
	reloadData();
});

function reloadData() {
	ServicioPrecioDWR.listVigentes(
		{
			callback: function(data) {
				$("#tableServicios > tbody:last > tr").remove();
				
				for (var i=0; i<data.length; i++) {
					$("#tableServicios > tbody:last").append(
						"<tr id='" + data[i].id + "'"
							+ "onclick='javascript:trServicioOnClick(event, this);'>"
							+ "<td class='tdServicioDescripcionServicioPrecio'><div id='divServicioDescripcionServicioPrecio" + data[i].id + "'>" + data[i].servicio.descripcion + "</div></td>"
							+ "<td class='tdMonedaAbreviacionServicioPrecio'><div id='divMonedaAbreviacionServicioPrecio" + data[i].id + "'>" + data[i].moneda.abreviacion + "</div></td>"
							+ "<td class='tdPrecioServicioPrecio'><div id='divPrecioServicioPrecio" + data[i].id + "'>"+ new Number(data[i].precio).toFixed(2) + "</div></td>"
						+ "</tr>"
					);
				}
			}, async: false
		}
	);
}

function trServicioOnClick(event, element) {
	document.getElementById("iFrameServicioPrecio").src = "./servicio_edit.jsp?id=" + $(element).attr("id");
	showPopUp(document.getElementById("divIFrameServicioPrecio"));
}

function inputNewOnClick(event, element) {
	document.getElementById("iFrameServicioPrecio").src = "./servicio_edit.jsp";
	showPopUp(document.getElementById("divIFrameServicioPrecio"));
}

function divCloseOnClick(event, element) {
	closePopUp(event, element.parentNode.parentNode);
	
	reloadData();
}