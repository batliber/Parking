var links = {
//	registro: {
//		link: "/ParkingWEB/pages/registro/registro.jsp",
//		descripcion: "Registro",
//		nivel: 1
//	},
	facturacion: { 
		link: "/ParkingWEB/pages/facturacion/facturacion.jsp",
		descripcion: "Facturaci&oacute;n",
		nivel: 1
	},
//	facturas: {
//		link: "/ParkingWEB/pages/factura/factura.jsp",
//		descripcion: "Facturas",
//		nivel: 1
//	},
//	caja: {
//		link: "/ParkingWEB/pages/caja/caja.jsp",
//		descripcion: "Caja",
//		nivel: 1
//	},
	abitab: {
		link: "/ParkingWEB/pages/abitab/abitab.jsp",
		descripcion: "ABITAB",
		nivel: 2
	},
	cobranza: {
		link: "/ParkingWEB/pages/cobranza/cobranza.jsp",
		descripcion: "Cobranza",
		nivel: 2
	},
	clientes: {
		link: "/ParkingWEB/pages/cliente/cliente.jsp",
		descripcion: "Clientes",
		nivel: 2
	},
//	vehiculos: {
//		link: "/ParkingWEB/pages/vehiculo/vehiculo.jsp",
//		descripcion: "Veh&iacute;culos",
//		nivel: 2
//	},
	servicios: {
		link: "/ParkingWEB/pages/servicio/servicio.jsp",
		descripcion: "Servicios",
		nivel: 2
	},
	reportes: {
		link: "/ParkingWEB/pages/reportes/reportes.jsp",
		descripcion: "Reportes",
		nivel: 2
	},
	usuarios: {
		link: "/ParkingWEB/pages/usuario/usuario.jsp",
		descripcion: "Usuarios",
		nivel: 2
	}
};

$(document).ready(function() {
	SeguridadDWR.getActiveUserData(
		{
			callback: function(data) {
				var html = "";
				
				var nivel = 1;
				for (var j=0; j<data.grupos.length; j++) {
					nivel = Math.max(nivel, data.grupos[j].nivel);
				}
				
				var i = 0;
				for (var id in links) {
					if (links[id].nivel <= nivel) {
						html += 
							"<div class='" + (i == 0 ? "activeMenuBarItem" : "inactiveMenuBarItem") + "'>"
								+ "<div>"
									+ "<a href='#' id='" + id + "'"
										+ " onclick='javascript:menuItemOnClick(event, this)'>"
										+ links[id].descripcion
										+ "</a>"
								+ "</div>"
							+ "</div>";
						
						i++;
					}
				}
				
				html += 
					"<div class='divUserInfo'>"
						+ "<div class='divLogout' style='float: right;'"
							+ " onclick='javascript:divLogoutOnClick(event, this)'>"
							+ "&nbsp;"
						+ "</div>"
						+ "<div id='divActiveUser' style='float: right;'>"
							+ data.nombre
						+ "</div>"
						+ "<div style='float: right;'>Usuario:</div>"
					+ "</div>";
				
				$(".divMenuBar").html(html);
				
				$(".divUserInfo").width(
					$(".divMenuBar").width() - i * $(".activeMenuBarItem").width() - 18
				);
			}, async: false
		}
	);
});

function menuItemOnClick(event, element) {
	window.frames[0].location = links[element.id].link;
	
	var active = $(".activeMenuBarItem");
	active.removeClass("activeMenuBarItem");
	active.addClass("inactiveMenuBarItem");
	
	element.parentNode.parentNode.setAttribute("class", "activeMenuBarItem");
}

function divLogoutOnClick() {
	SeguridadDWR.logout(
		{
			callback: function(data) {
				window.location = "/ParkingWEB";
			}, async: false
		}
	);
}