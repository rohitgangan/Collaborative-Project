'use strict';
 
app.controller('BlogCommentController', ['$scope', 'BlogCommentService', 'textAngularManager','$location',function($scope, BlogCommentService,textAngularManager,$location) {
  
	var self = this;
    self.blogComment={commentId:null,blogComment:''};
    self.blogComments=[];
   
    self.submit = submit;
  
    self.remove = remove;
    self.reset = reset;
    self.refresh = refresh;
   
 
    
   
 
     self.fetchAllBlogComments = function(){
        BlogCommentService.fetchAllBlogComments()
            .then(
            function(d) {
                self.blogComments = d;
            },
            function(errResponse){
                console.error('Error while fetching comments');
            }
        );
    }
    
 
    
    function createBlogComment(blogComment){
        BlogCommentService.createBlogComment(blogComment)
            .then(
            self.fetchAllBlogComments,
            function(errResponse){
                console.error('Error while posting comments');
            }
        );
    }
 
    function deleteBlogComment(commentId){
        BlogCommentService.deleteBlogComment(commentId)
            .then(
            self.fetchAllBlogComments,
            function(errResponse){
                console.error('Error while deleting comment');
            }
        );
    }
 
    function submit() {
        if(self.blogComment.commentId===null){
            console.log('Posting New comment', self.blogComment);
            createBlogComment(self.blogComment);
        }
        reset();
    }
 
    
    
  /*  function viewBlog(blogId){
    	 console.log('blog to view ', blogId);
         for(var i = 0; i < self.blogs.length; i++){
             if(self.blogs[i].blogId === blogId) {
                 self.blogview = angular.copy(self.blogs[i]);
                 break;
             }
         }
    }*/
    
    function remove(commentId){
        console.log('comment id to be deleted', commentId);
        if(self.blogComment.commentId === commentId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBlogComment(commentId);
    }
 
 
    function reset(){
        self.blogComment={commentId:null,blogComment:''};
        $scope.blogCommentForm.$setPristine(); //reset Form
    }
    
    function refresh(){
    	
    	location.reload();
    } 
    
 
}]);