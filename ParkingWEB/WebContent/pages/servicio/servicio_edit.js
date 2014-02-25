var servicioPrecio = null;

$(document).ready(function() {
	MonedaDWR.list(
		{
			callback: function (data) {
				for (var i=0; i<data.length; i++) {
					$("#selectMoneda").append(
						"<option value='" + data[i].id + "'>" + data[i].descripcion + "</option>"
					);
				}
			}, async: false
		}
	);
	
	if (id != null) {
		ServicioPrecioDWR.getById(
			id,
			{
				callback: function(data) {
					if (data != null)  {
						servicioPrecio = data;
						
						$("#inputServicioDescripcion").val(data.servicio.descripcion);
						$("#selectMoneda").val(data.moneda.nombre);
						$("#inputServicioPrecioPrecio").val(data.precio);
					}
				}, async: false
			}
		);
	}
});

function inputGrabarServicioPrecioOnClick(event) {
	if (id != null) {
		servicioPrecio.servicio.descripcion = $("#inputServicioDescripcion").val();
		servicioPrecio.uact = 1;
		servicioPrecio.fact = new Date();
		servicioPrecio.term = 1;
		
		if (servicioPrecio.precio != $("#inputServicioPrecioPrecio").val()) {
			servicioPrecio.validoHasta = new Date();
			
			ServicioPrecioDWR.update(
				servicioPrecio,
				{
					callback: function(data) {
						
					}, async: false
				}
			);
			
			servicioPrecio.id = null;
			servicioPrecio.validoHasta = null;
			
			servicioPrecio.precio = $("#inputServicioPrecioPrecio").val();
			
			ServicioPrecioDWR.add(
				servicioPrecio,
				{
					callback: function(data) {
						
					}, async: false
				}
			);
		} else {
			ServicioPrecioDWR.update(
				servicioPrecio,
				{
					callback: function(data) {
						
					}, async: false
				}
			);
		}
	} else {
		servicioPrecio = {
			precio: $("#inputServicioPrecioPrecio").val(),
			moneda: {
				id: 1
			},
			servicio: {
				descripcion: $("#inputServicioDescripcion").val(),
				servicioTipo: {
					id: 1
				},
				uact: 1,
				fact: new Date(),
				term: 1
			},
			uact: 1,
			fact: new Date(),
			term: 1
		};
		
		ServicioPrecioDWR.add(
			servicioPrecio,
			{
				callback: function(data) {
					
				}, async: false
			}
		);
	}
}