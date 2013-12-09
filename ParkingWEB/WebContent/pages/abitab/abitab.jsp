<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String message = (String) request.getAttribute("message");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Generaci&oacute;n de archivo ABITAB</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/GeneracionArchivoABITABDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/CobranzaMovimientoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/global.js"></script>
	<script type="text/javascript" src="./abitab.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./abitab.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" id="inputGenerarArchivo" value="Generar archivo" onclick="javascript:inputGenerarArchivoOnClick(event, this)"/></div>
		<div class="divButton"><input type="submit" id="inputImportarArchivo" value="Importar archivo" onclick="javascript:inputImportarArchivoOnClick(event, this)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Archivos</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Archivo:</div>
		<form id="formSubirArchivo" method="post" action="/ParkingWEB/Upload" enctype="multipart/form-data">
			<div id="divSubirArchivo"><input type="file" id="inputArchivo" name="inputArchivo" onchange="javascript:inputArchivoOnChange(event)"/></div>
		</form>
<%
	if (message != null) {
%>
		<div class="divFormLabel">&nbsp;</div>
		<div><%= message %></div>
<% 
	} 
%>
	</div>
</body>
</html>