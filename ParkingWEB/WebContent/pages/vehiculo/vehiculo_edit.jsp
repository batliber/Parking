<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Veh&iacute;culo</title>
	<script type="text/javascript">
		var id = <%= request.getParameter("id") != null ? request.getParameter("id") : "null" %>;
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/DepartamentoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./vehiculo_edit.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./vehiculo_edit.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Grabar vehiculo" onclick="javascript:inputGrabarVehiculoOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Veh&iacute;culo</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Matr&iacute;cula:</div>
		<div id="divVehiculoMatricula"><input type="text" id="inputVehiculoMatricula"/></div>
		<div class="divFormLabel">Departamento:</div>
		<div id="divVehiculoDepartamento"><select id="selectVehiculoDepartamento"></select></div>
		<div class="divFormLabel">Marca/Modelo:</div>
		<div id="divVehiculoDescripcion"><input type="text" id="inputVehiculoDescripcion"/></div>
		<br/>
		<div class="divFormLabel">Documento:</div>
		<div id="divVehiculoClienteDocumento"><input type="text" id="inputVehiculoClienteDocumento" onchange="javascript:inputVehiculoClienteDocumentoOnChange(event)"/></div>
		<div class="divFormLabel">Nombre:</div>
		<div id="divVehiculoClienteNombre"><input type="text" id="inputVehiculoClienteNombre"/></div>
		<div class="divFormLabel">Domicilio:</div>
		<div id="divVehiculoClienteDomicilio"><input type="text" id="inputVehiculoClienteDomicilio"/></div>
		<div class="divFormLabel">Tel&eacute;fono:</div>
		<div id="divVehiculoClienteTelefono"><input type="text" id="inputVehiculoClienteTelefono"/></div>
		<div>
			<table id="tableVehiculoClientes" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td><div>Nombre</div></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><div>&nbsp;</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>