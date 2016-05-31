var module = angular.module("creditcard");


module.controller("saveController", function($scope, cardService, $location){	
	$scope.guardarCard = function(){
		
		var saveCard = cardService.saveCard(
				$scope.cedula, $scope.label, $scope.mount, $scope.dateCut);
		
		saveCard.success(function(data){
			alert("Se creo la tarjeta correctamente!.");
			$location.url("/");
		}).error(function(data, status, headers, config){
			alert("Error al guardar");
		});
	};
	
	$scope.regresar = function(){
		$location.url("/");
	};
});