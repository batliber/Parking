var links = {
	registro: "/ParkingWEB/pages/registro/registro.jsp",
	facturas: "/ParkingWEB/pages/factura/factura.jsp",
	caja: "/ParkingWEB/pages/caja/caja.jsp",
	clientes: "/ParkingWEB/pages/cliente/cliente.jsp",
	vehiculos: "/ParkingWEB/pages/vehiculo/vehiculo.jsp",
	servicios: "/ParkingWEB/pages/servicio/servicio.jsp",
	reportes: "/ParkingWEB/pages/reportes/reportes.jsp",
	abitab: "/ParkingWEB/pages/abitab/abitab.jsp"
};

function menuItemOnClick(event, element) {
	window.frames[0].location = links[element.id];
}