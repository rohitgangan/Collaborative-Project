'use strict';
 
app.controller('ForumController', ['$scope', 'ForumService', 'textAngularManager','$http','$location',function($scope, ForumService,textAngularManager,$http,$location) {
   
	
	$scope.version = textAngularManager.getVersion();
    $scope.versionNumber = $scope.version.substring(1);
    $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE9+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';

	
	var self = this;
    self.forum={forumId:null,forumName:'',forumContent:''};
    self.forums=[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.viewForum = viewForum;
    
    
 
    fetchAllForums();
 
    function fetchAllForums(){
        ForumService.fetchAllForums()
            .then(
            function(d) {
                self.forums = d;
            },
            function(errResponse){
                console.error('Error while fetching Forums');
            }
        );
    }
    
    function fetchForum(forumId){
    	ForumService.fetchForum(forumId)
            .then(
            function(d) {
                self.viewForum = d;
            },
            function(errResponse){
                console.error('Error while fetching Forum');
            }
        );
    }
    
    
    
    function createForum(forum){
    	ForumService.createForum(forum)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while creating Forum');
            }
        );
    }
    
    
    
    function updateForum(forum, forumId){
    	ForumService.updateForum(forum, forumId)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while updating forums');
            }
        );
    }
 
    function deleteForum(forumId){
    	ForumService.deleteForum(forumId)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while deleting Forum');
            }
        );
    }
 
    
    
    function submit() {
        if(self.forum.forumId===null){
            console.log('Saving New Forum', self.forum);
            createForum(self.forum);
        }else{
            updateForum(self.forum, self.forum.forumId);
            console.log('Forum updated with id ', self.forum.forumId);
        }
        reset();
    }
 
    function edit(forumId){
        console.log('id to be edited', forumId);
        for(var i = 0; i < self.forums.length; i++){
            if(self.forums[i].forumId === forumId) {
                self.forum = angular.copy(self.forums[i]);
                break;
            }
        }
    }
    
    function viewForum(forumId){
    	$http.get('http://localhost:8082/connectit/forum/'+forumId).then
    	console.log('forum to view ', forumId);
         for(var i = 0; i < self.forums.length; i++){
             if(self.forums[i].forumId === forumId) {
                 self.forumview = angular.copy(self.forums[i]);
                 break;
                 
             }
         }

    }
    
    function remove(forumId){
        console.log('id to be deleted', forumId);
        if(self.forum.forumId === forumId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteForum(forumId);
    }
 
 
    function reset(){
        self.forum={forumId:null,forumName:'',forumContent:''};
        $scope.forumForm.$setPristine(); //reset Form
    }
    
   
 
}]);