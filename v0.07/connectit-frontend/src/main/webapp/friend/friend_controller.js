'use strict';
 
app.controller('FriendController', ['$scope', 'FriendService','$location',function($scope, FriendService,$location) {

	var self = this;
    self.friend={friendTableId:null};

    
     self.sendFriendRequest= function(friend, friendId){
    	 FriendService.sendFriendRequest(self.friend, friendId)
         .then(
         function(errResponse){
             console.error('Error while posting friend request');
         }
     );
    }
    
    
}]);
