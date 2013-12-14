<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cobranza</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./cobranza.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./cobranza.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Generar cobranza" onclick="javascript:inputGenerarCobranzaOnClick(event)"/></div>
		<div class="divButton"><input type="submit" value="Actualizar" onclick="javascript:inputActualizarOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<table id="tableCobranzaMovimientos" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td class="tdCobranzaMovimientoClienteDocumento">Documento</td>
					<td class="tdCobranzaMovimientoClienteNombre">Nombre</td>
					<td class="tdCobranzaMovimientoClienteApellido">Apellido</td>
					<td class="tdCobranzaMovimientoMoneda">Moneda</td>
					<td class="tdCobranzaMovimientoImporte">Importe</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>