<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Caja</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/UsuarioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/SeguridadDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CajaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./caja.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./caja.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div id="divButtonApertura" class="divButton"><input type="submit" id="inputApertura" value="Apertura de caja" onclick="javascript:inputAperturaOnClick(event);"/></div>
		<div id="divButtonCierre" class="divButton"><input type="submit" id="inputCierre" value="Cierre de caja" onclick="javascript:inputCierreOnClick(event);"/></div>
		<div id="divButtonCierre" class="divButton"><input type="submit" id="inputImprimir" value="Imprimir" onclick="javascript:inputImprimirOnClick(event);"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleTripleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Fecha:</div><div id="divFecha"><input type="text" id="inputFecha"/></div>
		<div class="divFormLabel">Usuario:</div><div id="divUsuario"><select id="selectUsuario"></select></div>
		<div id="divCajaValores">
			<table id="tableCajaValores" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tdCajaValoresImporte">Importe</td>
						<td class="tdCajaValoresCantidad">Cantidad</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><div>$U 2.000</div></td>
						<td><div><input type="text" id="2000" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 1.000</div></td>
						<td><div><input type="text" id="1000" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 500</div></td>
						<td><div><input type="text" id="500" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 200</div></td>
						<td><div><input type="text" id="200" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 100</div></td>
						<td><div><input type="text" id="100" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 50</div></td>
						<td><div><input type="text" id="50" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 20</div></td>
						<td><div><input type="text" id="20" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 10</div></td>
						<td><div><input type="text" id="10" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 5</div></td>
						<td><div><input type="text" id="5" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 2</div></td>
						<td><div><input type="text" id="2" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
					<tr>
						<td><div>$U 1</div></td>
						<td><div><input type="text" id="1" value="0" onchange="javascript:inputValorOnChange(event, this)"/></div></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><div>Total:</div></td>
						<td><div id="divTotal">0</div></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>