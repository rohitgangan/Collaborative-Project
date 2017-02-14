'use strict';
 
app.controller('FriendController', ['$scope', 'FriendService','$location',function($scope, FriendService,$location) {
	
	var self = this;
    self.friend={friendTableId:null};
    self.friendRequests=[];
    self.friends = [];
    self.allFriends = [];
    getAllFriendRequest();
    getAllFriends();
    listFriends();
    
    $scope.status=false;
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
    
    function getAllFriends(){
    	FriendService.getAllFriends()
    	.then(
    		function(d){
    			self.friends = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friends');
    		}
    	);
    }
    
    function listFriends(){
    	FriendService.listFriends()
    	.then(
    		function(d){
    			self.allFriends = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friend table');
    		}
    	);
    }
    
     self.sendFriendRequest= function(friend, friendId){
    	 FriendService.sendFriendRequest(self.friend, friendId)
         .then(
        		 $scope.status = true,
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
             
         }
         
     );
    	 location.reload();
    } 
     
}]);
