var servicioPrecio = null;

$(document).ready(function() {
	ServicioTipoDWR.list(
		{
			callback: function (data) {
				for (var i=0; i<data.length; i++) {
					$("#selectServicioTipo").append(
						"<option value='" + data[i].id + "'>" + data[i].descripcion + "</option>"
					);
				}
			}, async: false
		}
	);
	
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
						$("#selectServicioTipo").val(data.servicio.servicioTipo.id);
						$("#selectMoneda").val(data.moneda.id);
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
		servicioPrecio.servicio.servicioTipo = {
			id: $("#selectServicioTipo").val()
		};
		servicioPrecio.moneda = {
			id: $("#selectMoneda").val()
		};
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
				id: $("#selectMoneda").val()
			},
			servicio: {
				descripcion: $("#inputServicioDescripcion").val(),
				servicioTipo: {
					id: $("#selectServicioTipo").val()
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