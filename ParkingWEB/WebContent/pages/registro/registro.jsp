<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parking</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/RegistroDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="./registro.js"></script>
</head>
<body>
	<div>
		<div style="float: left;">Matr&iacute;cula:</div>
		<div><input type="text" id="inputMatricula" onchange="javascript:inputMatriculaOnChange(event);"></div>
		<div style="float: left;">Descripci&oacute;n:</div>
		<div id="divDescripcion">&nbsp;</div>
		<div style="float: left;"><input type="submit" value="Registro" onclick="javascript:inputRegistroOnClick(event);"/></div>
		<div id="divButtonAgregarVehiculo" style="display: none;"><input type="submit" value="Agregar vehiculo" onclick="javascript:inputAgregarVehiculoOnClick(event);"/></div>
	</div>
</body>
</html>