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
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Agregar" onclick="javascript:inputNewOnClick(event)"/></div>
		<div class="divButton"><input type="submit" value="Exportar a Excel" onclick="javascript:inputExportarAExcelOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<table id="tableClientes" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td class="tdClienteDocumento"><div>Documento</div></td>
					<td class="tdClienteNombre"><div>Nombre</div></td>
					<td class="tdClienteApellido"><div>Apellido</div></td>
					<td class="tdClienteDomicilio"><div>Domicilio</div></td>
					<td class="tdClienteTelefono"><div>Tel&eacute;fono</div></td>
					<td class="tdClienteFechaAlta"><div>Ingreso</div></td>
				</tr>
			</thead>
			<tbody>
				<tr>
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
	<div id="divIFrameCliente" style="display: none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Cliente</div>
			<div class="divTitleBarCloseButton" onclick="javascript:divCloseOnClick(event, this)">&nbsp;</div>
		</div>
		<iframe id="iFrameCliente" frameborder="0" src="#"></iframe>
	</div>
	<div id="divModalBackground">&nbsp;</div>
</body>
</html>