<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<script type="text/javascript" src="/ParkingWEB/dwr/engine.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/util.js"></script>
	<script type="text/javascript" src="/ParkingWEB/dwr/interface/SeguridadDWR.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/ParkingWEB/js/util.js"></script>
	<script type="text/javascript" src="./login.js"></script>
	<link rel="stylesheet" type="text/css" href="/ParkingWEB/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="./login.css"/>
</head>
<body>
	<div class="divMainWindow">
		<div class="divLoginForm">
			<div class="divTitleBar">
				<div class="divTitleBarText">Iniciar sesi&oacute;n</div>
			</div>
			<div class="divFormLabel">Usuario:</div>
			<div class="divFormInput"><input type="text" id="inputUsuario" onkeydown="javascript:inputUsuarioOnKeyDown(event, this)"/></div>
			<div class="divFormLabel">Contrase&ntilde;a:</div>
			<div class="divFormInput"><input type="password" id="inputContrasena" onkeydown="javascript:inputContrasenaOnKeyDown(event, this)"/></div>
			<div class="divButtons">
				<div style="float: left;width: 70px;"><input type="submit" id="inputAcceder" value="Acceder" onclick="javascript:inputAccederOnClick(event, this)"/></div>
				<div id="divError">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
</html>