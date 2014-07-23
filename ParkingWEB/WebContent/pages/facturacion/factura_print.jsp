<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="uy.com.parking.dwr.FacturaDWR" %>
<%@ page import="uy.com.parking.transferObjects.FacturaTO" %>
<%@ page import="uy.com.parking.transferObjects.FacturaLineaTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Factura</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/FacturaDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./factura_print.js"></script>
	<link rel="stylesheet" type="text/css" href="./factura_print.css"/>
</head>
<body>
<%
	FacturaTO facturaTO = new FacturaDWR().getById(new Long(request.getParameter("id")));

/*
	Double importeEstacionamiento = new Double(0);
	Double importeOtros = new Double(0);
	
	for (FacturaLineaTO facturaLineaTO : facturaTO.getFacturaLineas()) {
		if (facturaLineaTO.getServicio().getServicioTipo().getId().intValue() == 1) {
			importeEstacionamiento += facturaLineaTO.getImporteTotal();
		} else {
			importeOtros += facturaLineaTO.getImporteTotal();
		}
	}
*/

	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decimalFormat = new DecimalFormat("0.00");

	for (int i=0; i<2; i++) { 
%>
	<div class="divVia">
		<div class="divCabezal">&nbsp;</div>
		<div class="divFechaNumero">
			<div id="divFecha"><%= format.format(facturaTO.getFecha()) %></div>
			<div id="divNumero"><%= facturaTO.getNumero() %></div>
		</div>
		<div id="divNombre" class="divNombre">
			<%= facturaTO.getNombre().equals(facturaTO.getApellido()) ? 
					facturaTO.getNombre() : 
					(facturaTO.getNombre() + " " + facturaTO.getApellido()) %>
		</div>
		<div id="divDireccion" class="divDireccion">
			<%= facturaTO.getDomicilio() != null ? facturaTO.getDomicilio() : "&nbsp;" %>
		</div>
		<div class="divRUT">
			<div id="divDocumento"><%= facturaTO.getDocumento() %></div>
			<div id="divConsumidorFinal">&nbsp;</div>
			<div id="divTipoDocumento">Contado</div>
		</div>
		<div class="divFacturaLineas">
			<table id="tableFacturaLineas" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tdConcepto">&nbsp;</td>
						<td class="tdImporte">&nbsp;</td>
					</tr>
				</thead>
				<tbody>
	<% for (FacturaLineaTO facturaLineaTO : facturaTO.getFacturaLineas()) { %>
<!-- 
					<tr class="trFacturaLinea">
						<td>&nbsp;</td>
						<td id="tdEstacionamiento"><%= "" /* decimalFormat.format(importeEstacionamiento) */ %></td>
					</tr>
					<tr class="trFacturaLinea">
						<td>&nbsp;</td>
						<td id="tdOtros"><%= "" /* decimalFormat.format(importeOtros) */ %></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
 -->
 					<tr class="trFacturaLinea">
 						<td><%= facturaLineaTO.getDetalle() %></td>
 						<td class="tdEstacionamiento"><%= decimalFormat.format(facturaLineaTO.getImporteTotal()) %></td>
 					</tr>
	<% } %>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
				<tfoot>
					<tr class="trFacturaLinea">
						<td>&nbsp;</td>
						<td id="tdSubtotal"><%= decimalFormat.format(facturaTO.getImporteSubtotal()) %></td>
					</tr>
					<tr class="trFacturaLinea">
						<td>&nbsp;</td>
						<td id="tdIVA"><%= decimalFormat.format(facturaTO.getImporteIVA()) %></td>
					</tr>
					<tr class="trFacturaLinea">
						<td>&nbsp;</td>
						<td id="tdTotal"><%= decimalFormat.format(facturaTO.getImporteTotal()) %></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
<% 
	} 
%>
</body>
</html>