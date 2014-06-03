var usuario = null;

$(document).ready(function() {
	$("#inputEliminarUsuario").prop("disabled", true);
	
	GrupoDWR.list(
		{
			callback: function(data) {
				for (var i=0; i<data.length; i++) {
					$("#selectUsuarioGrupo").append(
						"<option value='" + data[i].id + "'>" + data[i].descripcion + "</option>"
					);
				}				
			}, async: false
		}
	);
	
	if (id != null) {
		UsuarioDWR.getById(
			id,
			{
				callback: function(data) {
					usuario = data;
					
					$("#inputUsuarioLogin").val(data.login);
					$("#inputUsuarioNombre").val(data.nombre);
					
					var nivel = 0;
					var id = 0;
					for (var i=0; i<data.grupos.length; i++) {
						if (data.grupos[i].nivel > nivel) {
							nivel = data.grupos[i].nivel;
							id = data.grupos[i].id;
						}
					}
					
					$("#selectUsuarioGrupo").val(id);
					
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
		
		usuario.grupos = [ { id: $("#selectUsuarioGrupo").val() } ];
		
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
			grupos: [ { id: $("#selectUsuarioGrupo").val() } ],
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