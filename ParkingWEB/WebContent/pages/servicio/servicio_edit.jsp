<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servicio</title>
	<script type="text/javascript">
		var id = <%= request.getParameter("id") != null ? request.getParameter("id") : "null" %>;
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/MonedaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./servicio_edit.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./servicio_edit.css"/>
</head>
<body>
	<div>
		<div style="float: left;">Servicio:</div><div id="divServicioNombre"><input type="text" id="inputServicioDescripcion"/></div>
		<div style="float: left;">Moneda:</div><div id="divMonedaDescripcion"><select id="selectMoneda"></select></div>
		<div style="float: left;">Precio:</div><div id="divServicioPrecioPrecio"><input type="text" id="inputServicioPrecioPrecio"/></div>
		<div>
			<input type="submit" value="Grabar servicio" onclick="javascript:inputGrabarServicioPrecioOnClick(event)"/>
		</div>
	</div>
</body>
</html>