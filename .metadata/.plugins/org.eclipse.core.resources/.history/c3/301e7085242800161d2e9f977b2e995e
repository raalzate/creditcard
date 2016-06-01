angular.module("creditcard",["ngRoute"]).
config(function($routeProvider){
	$routeProvider.when("/", {
		controller:'listController',
		templateUrl:'list.html'
	})
	.when("/save", {
		controller:'saveController',
		templateUrl:'save.html'
	})
	.otherwise({redirectTo:'/'});
});