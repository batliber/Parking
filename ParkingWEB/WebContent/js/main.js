var links = {
	registro: "/ParkingWEB/pages/registro/registro.jsp",
	facturacion: "/ParkingWEB/pages/facturacion/facturacion.jsp",
	facturas: "/ParkingWEB/pages/factura/factura.jsp",
	cobranza: "/ParkingWEB/pages/cobranza/cobranza.jsp",
	caja: "/ParkingWEB/pages/caja/caja.jsp",
	clientes: "/ParkingWEB/pages/cliente/cliente.jsp",
	vehiculos: "/ParkingWEB/pages/vehiculo/vehiculo.jsp",
	servicios: "/ParkingWEB/pages/servicio/servicio.jsp",
	reportes: "/ParkingWEB/pages/reportes/reportes.jsp",
	abitab: "/ParkingWEB/pages/abitab/abitab.jsp"
};

$(document).ready(function() {
	SeguridadDWR.getActiveUserData(
		{
			callback: function(data) {
				$("#divActiveUser").text(data);
			}, async: false
		}
	);
});

function menuItemOnClick(event, element) {
	window.frames[0].location = links[element.id];
	
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