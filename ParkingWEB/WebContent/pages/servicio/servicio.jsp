<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servicio</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./servicio.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./servicio.css"/>
</head>
<body>
	<div class="divButtonBar">&nbsp;</div>
	<div class="divButtonTitleBar">&nbsp;</div>
	<div class="divMainWindow">
		<table id="tableServicios" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td><div>&nbsp;</div></td>
					<td><div>Descripci&oacute;n</div></td>
					<td><div>Moneda</div></td>
					<td><div>Precio</div></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="divIFrameServicioPrecio" style="display: none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Servicio</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this.parentNode.parentNode)">&nbsp;</div>
		</div>
		<iframe id="iFrameServicioPrecio" frameborder="0" src="#"></iframe>
	</div>
	<div id="divModalBackground">&nbsp;</div>
</body>
</html>