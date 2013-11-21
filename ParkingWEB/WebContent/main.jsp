<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parking</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/SeguridadDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/main.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/main.css"/>
</head>
<body>
	<div class="divMenuBar">
		<div class="activeMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="registro">Registro</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="facturacion">Facturaci&oacute;n</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="cobranza">Cobranza</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="vehiculos">Veh&iacute;culos</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="clientes">Clientes</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="servicios">Servicios</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="reportes">Reportes</a></div></div>
		<div class="inactiveMenuBarItem"><div><a href="#" onclick="javascript:menuItemOnClick(event, this)" id="abitab">ABITAB</a></div></div>
		<div class="divUserInfo">
			<div class="divLogout" style="float: right;" onclick="javascript:divLogoutOnClick(event, this)">&nbsp;</div>
			<div id="divActiveUser" style="float: right;">&nbsp;</div>
			<div style="float: right;">Usuario:</div>
		</div>
	</div>
	<div id="divIFrameActivePage"><iframe id="iFrameActivePage" src="/ParkingWEB/pages/registro/registro.jsp" frameborder="0"></iframe></div>
</body>
</html>