<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Vehiculo</title>
	<script type="text/javascript">
		var matricula = "<%= request.getParameter("m") != null ? request.getParameter("m") : "" %>";
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./vehiculo.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
</head>
<body>
	<div>
		<div style="float: left;">Matr&iacute;cula:</div><div id="divVehiculoMatricula"><input type="text" id="inputVehiculoMatricula" onchange="javascript:inputVehiculoMatriculaOnChange(event)"/></div>
		<div style="float: left;">Descripci&oacute;n:</div><div id="divVehiculoDescripcion"><input type="text" id="inputVehiculoDescripcion"/></div>
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
		<div>
			<input type="submit" value="Grabar vehiculo" onclick="javascript:inputGrabarVehiculoOnClick(event)"/>
		</div>
	</div>
</body>
</html>