<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cliente</title>
	<script type="text/javascript">
		var id = <%= request.getParameter("id") != null ? request.getParameter("id") : "null" %>;
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/DepartamentoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./cliente_edit.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./cliente_edit.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" id="inputGuardarCliente" value="Guardar" onclick="javascript:inputGuardarOnClick(event)"/></div>
		<div class="divButton"><input type="submit" id="inputEliminarCliente" value="Eliminar" onclick="javascript:inputEliminarOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
		<div class="divButton"><input type="submit" id="inputAgregarVehiculo" value="Agregar veh&iacute;culo" onclick="javascript:inputAgregarVehiculoOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Cliente</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Veh&iacute;culos</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Documento:</div><div id="divClienteDocumento"><input type="text" id="inputClienteDocumento"/></div>
		<div class="divFormLabel">Nombre:</div><div id="divClienteNombre"><input type="text" id="inputClienteNombre"/></div>
		<div class="divFormLabel">Apellido:</div><div id="divClienteApellido"><input type="text" id="inputClienteApellido"/></div>
		<div class="divFormLabel">Domicilio:</div><div id="divClienteDomicilio"><input type="text" id="inputClienteDomicilio"/></div>
		<div class="divFormLabel">Tel&eacute;fono:</div><div id="divClienteTelefono"><input type="text" id="inputClienteTelefono"/></div>
		<div class="divFormLabel">Fecha de ingreso:</div><div id="divClienteFechaAlta"><input type="text" id="inputClienteFechaAlta"/></div>
		<br>
		<div id="divClienteVehiculos">
			<table id="tableClienteVehiculos" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tdVehiculoServicioPrecioAcciones">&nbsp;</td>
						<td class="tdVehiculoServicioPrecioMatricula">Matr&iacute;cula</td>
						<td class="tdVehiculoServicioPrecioDescripcion">Marca/Modelo</td>
						<td class="tdVehiculoServicioPrecioServicio">Servicio</td>
						<td class="tdVehiculoServicioPrecioPrecio">Precio</td>
					</tr>
				</thead>
				<tbody>
					<tr class="trDummyRow">
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