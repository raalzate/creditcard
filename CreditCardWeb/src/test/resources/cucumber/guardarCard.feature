Feature:Guardar tarjeta

@guardar
Scenario: guardar una tarjeta
	Given Dado que el usuario ingresa a la aplicacion para guardar una tarjeta
	When  El usuario da click en el boton nuevo
	Then debe registrar la tarjeta con el nombre "Tarjeta Master Card" con la cedula "1115069076", el monto de 500000 y en la fecha 2016-10-10
	Then debe visualizar una dialog con el mensaje "Se creo la tarjeta correctamente!."
