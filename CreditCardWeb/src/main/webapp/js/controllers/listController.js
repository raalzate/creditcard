var module = angular.module("creditcard");


module.controller("listController", function($scope, listService, $location){	
	$scope.buscarCards = function(){
		var cards = listService.getCards($scope.cedula);
		
		cards.success(function(data){
			$scope.listCards = data;
		}).error(function(data, status, headers, config){
			alert(headers("internalServerError"));
		});
	};
	
	$scope.nuevoCard = function(){
		$location.url("/save");
	};
});