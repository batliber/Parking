<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cliente</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./cliente.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./cliente.css"/>
</head>
<body>
	<div>
		<table id="tableClientes" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td><div>&nbsp;</div></td>
					<td class="tdClienteNombre"><div>Nombre</div></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div>&nbsp;</div></td>
					<td class="tdClienteNombre"><div>&nbsp;</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="divIFrameCliente" style="display: none;">
		<div class="divTitleBar">
			<div style="float:left;">Cliente</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this.parentNode.parentNode)">&nbsp;</div>
		</div>
		<iframe id="iFrameCliente" src="#"></iframe>
	</div>
</body>
</html>