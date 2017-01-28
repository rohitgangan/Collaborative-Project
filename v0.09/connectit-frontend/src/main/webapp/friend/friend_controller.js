'use strict';
 
app.controller('FriendController', ['$scope', 'FriendService','$location',function($scope, FriendService,$location) {

	var self = this;
    self.friend={friendTableId:null};
    self.friendRequests=[];
    
    getAllFriendRequest()
    
    function getAllFriendRequest(){
    	FriendService.getAllFriendRequest()
    	.then(
    		function(d){
    			self.friendRequests = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friendRequests');
    		}
    	);
    }
    
     self.sendFriendRequest= function(friend, friendId){
    	 FriendService.sendFriendRequest(self.friend, friendId)
         .then(
         function(response){
             console.error('posting friend request successfull');
         }
     );
    }
    
     self.acceptFriendRequest= function(userId){
    	 FriendService.acceptFriendRequest(userId)
         .then(
         function(response){
             console.error('accepting friend request successfull');
             location.reload();
         }
     );
    }
     
     self.rejectFriendRequest= function(userId){
    	 FriendService.rejectFriendRequest(userId)
         .then(
         function(response){
             console.error('rejecting friend request successfull');
             location.reload();
         }
     );
    } 
     
}]);
