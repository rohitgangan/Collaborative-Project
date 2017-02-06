var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
  $scope.search=location.search.substr(8);
	$http.get("resources/js/products.js").then(function (response) {
      $scope.myData = response.data.records;
  });
});
