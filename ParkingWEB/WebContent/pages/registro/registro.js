var vehiculo = null;

function inputMatriculaOnChange(event) {
	VehiculoDWR.getByMatricula(
		$("#inputMatricula").val(),
		{
			callback: function(data) {
				if (data != null) {
					vehiculo = data;
					
					$("#divDescripcion").text(data.descripcion);
				} else {
					vehiculo = {
						matricula: $("#inputMatricula").val()
					};
					
					$("#divDescripcion").text("Vehículo no registrado");
					$("#divButtonAgregarVehiculo").show();
				}
			}, async: false
		}
	);
}

function inputRegistroOnClick(event) {
	var registro = {
		fecha: new Date(),
		matricula: vehiculo.matricula,
		registroTipo: {
			id: 57
		},
		uact: 1,
		fact: new Date(),
		term: 1,
	};
	
	RegistroDWR.add(
		registro,
		{
			callback: function(data) {
				$("#inputMatricula").val("");
				$("#divDescripcion").text(".");
				$("#divButtonAgregarVehiculo").hide();
				
				$("#inputMatricula").focus();
			}, async: false
		}
	);
}

function inputAgregarVehiculoOnClick(event) {
	
}