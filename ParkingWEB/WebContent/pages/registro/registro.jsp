<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registro</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/FacturaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/RegistroDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/VehiculoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./registro.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./registro.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div id="divButtonRegistrarEntrada" class="divButton"><input type="submit" id="inputRegistrarEntrada" value="Registrar entrada" onclick="javascript:inputRegistrarEntradaOnClick(event);"/></div>
		<div id="divButtonRegistrarSalida" class="divButton"><input type="submit" id="inputRegistrarSalida" value="Registrar salida" onclick="javascript:inputRegistrarSalidaOnClick(event);"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
		<div id="divButtonGenerarFactura" class="divButton"><input type="submit" id="inputGenerarFactura" value="Generar factura" onclick="javascript:inputGenerarFacturaOnClick(event);"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
		<div id="divButtonAgregarVehiculo" class="divButton"><input type="submit" id="inputAgregarVehiculo" value="Agregar veh&iacute;culo" onclick="javascript:inputAgregarVehiculoOnClick(event);"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Registro</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Factura</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
		<div id="divButtonTitleSingleSize" class="divButtonTitleBarTitle">Veh&iacute;culo</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormRegistro">
			<div class="divFormLabel">Matr&iacute;cula:</div>
			<div id="divMatricula"><input type="text" id="inputMatricula" onchange="javascript:inputMatriculaOnChange(event);"></div>
			<div class="divFormLabel">Ultimo registro:</div>
			<div id="divUltimoRegistro">&nbsp;</div>
			<div class="divFormLabel">Descripci&oacute;n:</div>
			<div id="divVehiculoDescripcion">&nbsp;</div>
			<div class="divFormLabel">Cliente:</div>
			<div id="divClienteNombre">&nbsp;</div>
		</div>
		<div id="divTableRegistros">
			<table id="tableRegistros" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td><div>Fecha</div></td>
						<td><div>Mat&iacute;cula</div></td>
						<!-- 
						<td><div>Veh&iacute;culo</div></td>
						<td><div>Cliente</div></td>
						-->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						<!-- 
						<td><div>&nbsp;</div></td>
						<td><div>&nbsp;</div></td>
						-->
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="divIFrameFactura" style="display:none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Factura</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this.parentNode.parentNode)">&nbsp;</div>
		</div>
		<iframe id="iFrameFactura" frameborder="0" src="/ParkingWEB/pages/factura/factura.jsp"></iframe>
	</div>
	<div id="divIFrameVehiculo" style="display:none;">
		<div class="divTitleBar">
			<div class="divTitleBarText" style="float:left;">Veh&iacute;culo</div>
			<div class="divTitleBarCloseButton" onclick="javascript:closePopUp(event, this.parentNode.parentNode)">&nbsp;</div>
		</div>
		<iframe id="iFrameVehiculo" frameborder="0" src="/ParkingWEB/pages/vehiculo/vehiculo_edit.jsp"></iframe>
	</div>
	<div id="divModalBackground">&nbsp;</div>
</body>
</html>