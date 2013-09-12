<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parking</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/main.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/main.css"/>
</head>
<body>
	<div class="divMenuBar">
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="registro">Registro</a></div>
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="facturas">Facturas</a></div>
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="vehiculos">Veh&iacute;culos</a></div>
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="clientes">Clientes</a></div>
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="reportes">Reportes</a></div>
		<div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="abitab">Generaci&oacute;n de archivo ABITAB</a></div>
	</div>
	<div id="divIFrameActivePage"><iframe id="iFrameActivePage" src="/ParkingWEB/pages/registro/registro.jsp"></iframe></div>
	<div id="divIFrameFactura" style="display:none;">
		<div class="divTitleBar">
			<div style="float:left;" onclick="javascript:closePopUp(event, this)">Factura</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this)">&nbsp;</div>
		</div>
		<iframe id="iFrameFactura" src="/ParkingWEB/pages/factura/factura.jsp"></iframe>
	</div>
	<div id="divIFrameVehiculo" style="display:none;">
		<div class="divTitleBar">
			<div style="float:left;">Veh&iacute;culo</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this)">&nbsp;</div>
		</div>
		<iframe id="iFrameVehiculo" src="/ParkingWEB/pages/vehiculo/vehiculo.jsp"></iframe>
	</div>
</body>
</html>