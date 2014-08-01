<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Reportes</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/FacturaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./reportes.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./reportes.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" value="Facturaci&oacute;n a la fecha"/></div>
		<div class="divButton"><input type="submit" value="Facturaci&oacute;n por servicio"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
		<div class="divButton"><input type="submit" value="Exportar a Excel" onclick="javascript:inputExportarAExcelOnClick(event, this)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Reportes</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div id="divFiltros" style="width: 100%;">
			<div id="divDesde" style="float: left;height: 22px;width: 300px;">
				<div class="divFormLabel">Desde:</div>
				<div><input type="text" id="inputDesde" onchange="javascript:inputDesdeOnChange(event, this)"/></div>
			</div>
			<div id="divHasta" style="float: left;height: 22px;width: 300px;">
				<div class="divFormLabel">Hasta:</div>
				<div><input type="text" id="inputHasta" onchange="javascript:inputHastaOnChange(event, this)"/></div>
			</div>
		</div>
		<table id="tableFacturas" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td class="tdFacturaNumero"><div>N&uacute;mero</div></td>
					<td class="tdFacturaFecha"><div>Fecha</div></td>
					<td class="tdFacturaCliente"><div>Cliente</div></td>
					<td class="tdFacturaMoneda"><div>Moneda</div></td>
					<td class="tdFacturaImporteSubtotal"><div>Subtotal</div></td>
					<td class="tdFacturaImporteIVA"><div>IVA</div></td>
					<td class="tdFacturaImporteTotal"><div>Total</div></td>
					<td class="tdFacturaAnulada"><div>Anulada</div></td>
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
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="divIFrameFactura" style="display: none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Factura</div>
			<div class="divTitleBarCloseButton" onclick="javascript:divCloseOnClick(event, this)">&nbsp;</div>
		</div>
		<iframe id="iFrameFactura" frameborder="0" src="#"></iframe>
	</div>
	<div id="divModalBackground">&nbsp;</div>
</body>
</html>