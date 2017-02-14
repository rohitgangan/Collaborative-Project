
/*app.controller("ChatController",['$scope','ChatService',function($scope, ChatService) {*/
angular.module('chatApp.controllers').controller("ChatController", function($scope, ChatService) {
$scope.messages = [];
  $scope.message = "";
  $scope.max = 140;
  $scope.sendTo = 'everyone';
  $scope.username     = '';

  $scope.addMessage = function() {
		var destination = "/app/chat.message";
		
		if($scope.sendTo != "everyone") {
			destination = "/app/chat.private." + $scope.sendTo;
			$scope.messages.unshift({message: $scope.message, username: 'you', priv: true, to: $scope.sendTo});
		}
		
		ChatService.send(destination,$scope.message);
		$scope.message = '';
	};
	
	$scope.privateSending = function(username) {
		$scope.sendTo = (username != $scope.sendTo) ? username : 'everyone';
};

  ChatService.receive().then(null, null, function(message) {
    $scope.messages.push(message);
    var a=$scope.messages.length;
    console.log("no of mess"+a)
  });
});

app.directive('printMessage', function () {
    return {
    	restrict: 'A',
        template: '<span ng-show="message.priv">[private] </span><strong>{{message.username}}<span ng-show="message.to"> -> {{message.to}}</span>:</strong> {{message.message}}<br/>'
       
    };
});