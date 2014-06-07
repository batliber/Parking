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
							+ "<td class='tdServicioPrecioServicioDescripcion'>"
								+ "<div class='divServicioPrecioServicioDescripcion'>" 
									+ data[i].servicio.descripcion 
								+ "</div>"
							+ "</td>"
							+ "<td class='tdServicioPrecioMonedaDescripcion'>"
								+ "<div class='divServicioPrecioMonedaDescripcion'>" 
									+ data[i].moneda.abreviacion 
								+ "</div>"
							+ "</td>"
							+ "<td class='tdServicioPrecioPrecio'>"
								+ "<div class='divServicioPrecioPrecio'>"
									+ new Number(data[i].precio).toFixed(2) 
								+ "</div>"
							+ "</td>"
							+ "<td class='tdServicioPrecioServicioServicioTipoDescripcion'>"
								+ "<div class='divServicioPrecioServicioServicioTipoDescripcion'>" 
									+ data[i].servicio.servicioTipo.descripcion 
								+ "</div>"
							+ "</td>"
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