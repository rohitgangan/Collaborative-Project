'use strict';
 
app.controller('ForumMemberController', ['$scope', 'ForumMemberService','$location','$localStorage',function($scope, ForumMemberService,$location,$localStorage) {
	 $scope.currentProfile=$localStorage.myProfile;
	 $scope.isExist = true;
	var self = this;
    self.forumMember={forumMemberId:null};
    self.forumMembers=[];
    
    self.submit = submit;
  
    self.remove = remove;
    self.reset = reset;
    self.refresh = refresh;
    self.memberExist = memberExist;
    self.loadList = loadList;
   
    

     function fetchAllForumMembers(){
        ForumMemberService.fetchAllForumMembers()
            .then(
            function(d) {
                self.forumMembers = d;
                for(var key in self.forumMembers){
                	if (self.forumMembers[key].userId !=  $scope.currentProfile.userId) {
                		$scope.isExist = false;
                    }
                }
                console.log(self.forumMembers)
            },
            function(errResponse){
                console.error('Error while fetching members');
            }
        );
    }
    
     function loadList(){
    	 fetchAllForumMembers();
     }
    
    function createForumMember(forumMember){
        ForumMemberService.createForumMember(forumMember)
            .then(
            		$scope.isExist = true,
            		fetchAllForumMembers,
            	
            function(errResponse){
                console.error('Error while posting forums');
            }
        );
    }
 
    function deleteForumMember(forumMemberId){
        ForumChatService.deleteForumMember(forumMemberId)
            .then(
            fetchAllForumMembers,
            function(errResponse){
                console.error('Error while deleting member');
            }
        );
    }
 
    function submit() {
        if(self.forumMember.forumMemberId===null){
            console.log('Posting New member', self.forumMember);
            createForumMember(self.forumMember);
        }
        reset();
    }
 
    
    function remove(ForumMemberId){
        console.log('chat id to be deleted', forumMemberId);
        if(self.forumMember.forumMemberId === forumMemberId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteForumChat(forumMemberId);
    }
 
 
    function reset(){
        self.forumMember={forumMemberId:null};
         //reset Form
    }
    
    function refresh(){
    	
    	location.reload();
    } 
    
    function memberExist(){
 
    
    for(var key in self.forumMembers){
    	if (self.forumMembers[key].userId !=  $scope.currentProfile.userId) {
    		$scope.isExist = false;
        }
    }
   
    
    }
}]);