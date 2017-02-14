'use strict';
 
app.controller('ForumChatController', ['$scope', 'ForumChatService','$location',function($scope, ForumChatService,$location) {
  
	var self = this;
    self.chatForum={chatForumId:null,chatContent:''};
    self.chatForums=[];
   
    self.submit = submit;
  
    self.remove = remove;
    self.reset = reset;
    self.refresh = refresh;
   
 
    
   
 
     self.fetchAllForumChats = function(){
        ForumChatService.fetchAllForumChats()
            .then(
            function(d) {
                self.chatForums = d;
            },
            function(errResponse){
                console.error('Error while fetching chats');
            }
        );
    }
    
 
    
    function createForumChat(chatForum){
        ForumChatService.createForumChat(chatForum)
            .then(
            self.fetchAllForumChats,
            function(errResponse){
                console.error('Error while posting chats');
            }
        );
    }
 
    function deleteForumChat(chatForumId){
        ForumChatService.deleteForumChat(chatForumId)
            .then(
            self.fetchAllForumChats,
            function(errResponse){
                console.error('Error while deleting chat');
            }
        );
    }
 
    function submit() {
        if(self.chatForum.chatForumId===null){
            console.log('Posting New chat', self.chatForum);
            createForumChat(self.chatForum);
        }
        reset();
    }
 
    
    function remove(chatForumId){
        console.log('chat id to be deleted', chatForumId);
        if(self.chatForum.chatForumId === chatForumId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteForumChat(chatForumId);
    }
 
 
    function reset(){
        self.chatForum={chatForumId:null,chatContent:''};
        //$scope.chatForumForm.$setPristine(); //reset Form
    }
    
    function refresh(){
    	
    	location.reload();
    } 
    
 
}]);