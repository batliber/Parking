<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Factura</title>
	<script type="text/javascript">
		var matricula = "<%= request.getParameter("m") != null ? request.getParameter("m") : "" %>";
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/FacturaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/RegistroDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./factura.js"></script>
</head>
<body>
	<div>
		<div style="float: left;">N&uacute;mero:</div><div id="divFacturaNumero">&nbsp;</div>
		<div style="float: left;">Fecha:</div><div id="divFacturaFecha">&nbsp;</div>
		<div style="float: left;">Cliente:</div><div id="divFacturaClienteNombre"></div>
		<div style="float: left;">Moneda:</div><div id="divFacturaMonedaDescripcion">&nbsp;</div>
	</div>
	<div>
		<table id="tableFacturaLineas" border="0" cellspacing="0" cellpadding="0" style="border: solid 1px black;">
			<thead>
				<tr>
					<td><div>N&uacute;mero</div></td>
					<td><div>Detalle</div></td>
					<td><div>Importe unit.</div></td>
					<td><div>Cantidad</div></td>
					<td><div>Total</div></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
					<td><div>&nbsp;</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div>
		<div style="float: left;">Subtotal:</div><div id="divFacturaImporteSubtotal">&nbsp;</div>
		<div style="float: left;">IVA:</div><div id="divFacturaImporteIVA">&nbsp;</div>
		<div style="float: left;">Total:</div><div id="divFacturaImporteTotal">&nbsp;</div>
	</div>
	<div>
		<input type="submit" id="inputGrabarFactura" value="Grabar factura" onclick="javascript:inputGrabarFacturaOnClick(event)"/>
	</div>
</body>
</html>