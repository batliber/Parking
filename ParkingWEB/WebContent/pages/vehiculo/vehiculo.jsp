<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Veh&iacute;culo</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./vehiculo.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./vehiculo.css"/>
</head>
<body>
	<div class="divButtonBar">&nbsp;</div>
	<div class="divButtonTitleBar">&nbsp;</div>
	<div class="divMainWindow">
		<table id="tableVehiculos" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td><div>&nbsp;</div></td>
					<td class="tdVehiculoMatricula"><div>Matr&iacute;cula</div></td>
					<td class="tdVehiculoDescripcion"><div>Descripci&oacute;n</div></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div>&nbsp;</div></td>
					<td class="tdVehiculoMatricula"><div>&nbsp;</div></td>
					<td class="tdVehiculoDescripcion"><div>&nbsp;</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="divIFrameVehiculo" style="display: none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Veh&iacute;culo</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this.parentNode.parentNode)">&nbsp;</div>
		</div>
		<iframe id="iFrameVehiculo" frameborder="0" src="#"></iframe>
	</div>
</body>
</html>