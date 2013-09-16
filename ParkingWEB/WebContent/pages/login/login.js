function inputAccederOnClick(event, element) {
	SeguridadDWR.login(
		$("#inputUsuario").val(),
		$("#inputContrasena").val(),
		{
			callback: function(data) {
				window.location = "/ParkingWEB";
			}, async: false
		}
	);
}