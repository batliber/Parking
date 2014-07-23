<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Historial de cobranza</title>
	<script type="text/javascript">
		var id = <%= request.getParameter("id") != null ? request.getParameter("id") : "null" %>;
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./historial.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./historial.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Actualizar" onclick="javascript:inputActualizarOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Cliente:</div><div class="divFullWidth" id="divCliente">&nbsp;</div>
		<br>
		<br>
		<div id="divCobranzaMovimientos">
			<table id="tableCobranzaMovimientos" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tdFecha">Fecha</td>
						<td class="tdCobranzaTipoDocumento">Tipo</td>
						<td class="tdServicio">Servicio</td>
						<td class="tdMoneda">Moneda</td>
						<td class="tdImporte">Importe</td>
						<td class="tdFacturaNumero">Factura</td>
					</tr>
				</thead>
				<tbody>
					<tr class="trDummyRow">
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>