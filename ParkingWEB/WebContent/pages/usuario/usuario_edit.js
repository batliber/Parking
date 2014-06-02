var usuario = null;

$(document).ready(function() {
	$("#inputEliminarUsuario").prop("disabled", true);
	
	if (id != null) {
		UsuarioDWR.getById(
			id,
			{
				callback: function(data) {
					usuario = data;
					
					$("#inputUsuarioLogin").val(data.login);
					$("#inputUsuarioNombre").val(data.nombre);
					
					$("#inputEliminarUsuario").prop("disabled", false);
				}, async: false
			}
		);
	}
});

function inputGuardarOnClick(event) {
	if (id != null) {
		usuario.login = $("#inputUsuarioLogin").val();
		usuario.nombre = $("#inputUsuarioNombre").val();
		usuario.contrasena = $("#inputUsuarioContrasena").val();
		
		usuario.fact = new Date();
		
		UsuarioDWR.update(
			usuario,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	} else {
		usuario = {
			login: $("#inputUsuarioLogin").val(),
			nombre: $("#inputUsuarioNombre").val(),
			contrasena: $("#inputUsuarioContrasena").val(),
			uact: 1,
			fact: new Date(),
			term: 1,
		};
		
		UsuarioDWR.add(
			usuario,
			{
				callback: function(data) {
					$("#inputEliminarUsuario").prop("disabled", false);
				}, async: false
			}
		);
	}
}

function inputEliminarOnClick(event) {
	if ((id != null) && confirm("Se eliminará el Usuario")) {
		usuario.fechaBaja = new Date();
		usuario.contrasena = $("#inputUsuarioContrasena").val();
		
		usuario.fact = new Date();
		
		UsuarioDWR.update(
			usuario,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}