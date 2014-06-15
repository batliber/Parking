<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ajuste de cobranza</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/MonedaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaTipoDocumentoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./ajuste.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./ajuste.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Grabar ajuste" onclick="javascript:inputGrabarAjusteOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Fecha:</div><div id="divFecha"><input type="text" id="inputFecha"/></div>
		<div class="divFormLabel">Cliente:</div><div id="divCliente"><select id="selectCliente"></select></div>
		<div class="divFormLabel">Servicio:</div><div id="divServicio"><select id="selectServicio"></select></div>
		<div class="divFormLabel">Tipo:</div><div id="divCobranzaTipoDocumento"><select id="selectCobranzaTipoDocumento"></select></div>
		<div class="divFormLabel">Moneda:</div><div id="divMoneda"><select id="selectMoneda"></select></div>
		<div class="divFormLabel">Importe:</div><div id="divImporte"><input type="text" id="inputImporte"/></div>
	</div>
</body>
</html>