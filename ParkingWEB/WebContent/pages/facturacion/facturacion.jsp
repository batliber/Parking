<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Facturaci&oacute;n</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ClienteDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/ServicioPrecioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/FacturaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/RegistroDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./facturacion.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./facturacion.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div id="divButtonAgregarServicio" class="divButton"><input type="submit" id="inputAgregarServicio" value="Agregar servicio" onclick="javascript:inputAgregarServicioOnClick(event);"/></div>
		<div id="divButtonGenerarFactura" class="divButton"><input type="submit" id="inputGenerarFactura" value="Generar factura" onclick="javascript:inputGenerarFacturaOnClick(event);"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Acciones</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Documento:</div>
		<div class="divFullWidth" id="divClienteDocumento"><input type="text" id="inputClienteDocumento" onchange="javascript:inputClienteDocumentoOnChange(event, this)"/></div>
		<div class="divFormLabel">Nombre:</div>
		<div class="divFullWidth" id="divClienteNombre">&nbsp;</div>
		<div class="divFormLabel">Domicilio:</div>
		<div class="divFullWidth" id="divClienteDomicilio">&nbsp;</div>
		<div class="divFormLabel">Tel&eacute;fono:</div>
		<div class="divFullWidth" id="divClienteTelefono">&nbsp;</div>
		<div id="divFacturaLineas">
			<table id="tableFacturaLineas" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tdServicio">Servicio</td>
						<td class="tdImporteUnitario">Importe unit.</td>
						<td class="tdCantidad">Cantidad</td>
						<td class="tdTotal">Total</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="divFormLabel">Subtotal:</div>
		<div class="divFullWidth" id="divImporteSubtotal">&nbsp;</div>
		<div class="divFormLabel">IVA:</div>
		<div class="divFullWidth" id="divImporteIVA">&nbsp;</div>
		<div class="divFormLabel">Total:</div>
		<div class="divFullWidth" id="divImporteTotal">&nbsp;</div>
	</div>
	<div id="divModalBackground">&nbsp;</div>
</body>
</html>