<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Usuario</title>
	<script type="text/javascript">
		var id = <%= request.getParameter("id") != null ? request.getParameter("id") : "null" %>;
	</script>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/UsuarioDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/GrupoDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./usuario_edit.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./usuario_edit.css"/>
</head>
<body>
	<div class="divButtonBar">
		<div class="divButton"><input type="submit" id="inputGuardarUsuario" value="Guardar" onclick="javascript:inputGuardarOnClick(event)"/></div>
		<div class="divButton"><input type="submit" id="inputEliminarUsuario" value="Eliminar" onclick="javascript:inputEliminarOnClick(event)"/></div>
		<div class="divButtonBarSeparator">&nbsp;</div>
	</div>
	<div class="divButtonTitleBar">
		<div id="divButtonTitleDoubleSize" class="divButtonTitleBarTitle">Usuario</div>
		<div class="divButtonTitleBarSeparator">&nbsp;</div>
	</div>
	<div class="divMainWindow">
		<div class="divFormLabel">Login:</div><div id="divUsuarioLogin"><input type="text" id="inputUsuarioLogin"/></div>
		<div class="divFormLabel">Nombre:</div><div id="divUsuarioNombre"><input type="text" id="inputUsuarioNombre"/></div>
		<div class="divFormLabel">Contrase&ntilde;a:</div><div id="divUsuarioContrasena"><input type="password" id="inputUsuarioContrasena"/></div>
		<div class="divFormLabel">Nivel:</div><div id="divUsuarioGrupo"><select id="selectUsuarioGrupo"></select></div>
	</div>
</body>
</html>