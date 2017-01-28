'use strict';
 
app.controller('BlogLikeController', ['$scope', 'BlogLikeService','$location',function($scope, BlogLikeService,$location) {
  
	var self = this;
    self.blogLike={blogLikeId:null};
    self.blogLikes=[];
    self.submit = submit;
    self.remove = remove;
    self.reset = reset;
    self.refresh = refresh;
   
 
    
   
 
     self.fetchAllBlogLikes = function(){
        BlogLikeService.fetchAllBlogLikes()
            .then(
            function(d) {
                self.blogLikes = d;
                var count = Object.keys(self.blogLikes).length;
                console.log(count);
            },
            function(errResponse){
                console.error('Error while fetching likes');
            }
        );
    }
    
 
    
    function createBlogLike(blogLike){
        BlogLikeService.createBlogLike(blogLike)
            .then(
            self.fetchAllBlogLikes,
            function(errResponse){
                console.error('Error while posting likes');
            }
        );
    }
 
    function deleteBlogLike(blogLikeId){
        BlogCommentService.deleteBlogLike(blogLikeId)
            .then(
            self.fetchAllBlogLikes,
            function(errResponse){
                console.error('Error while deleting like');
            }
        );
    }
 
    function submit() {
        if(self.blogLike.blogLikeId===null){
            console.log('Posting New liket', self.blogLike);
            createBlogLike(self.blogLike);
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
    
    function remove(blogLikeId){
        console.log('like id to be deleted', blogLikeId);
        if(self.blogLike.blogLikeId === blogLikeId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBlogLike(blogLikeId);
    }
 
 
    function reset(){
        self.blogLike={blogLikeId:null};
        $scope.blogLikeForm.$setPristine(); //reset Form
    }
    
    function refresh(){
    	
    	location.reload();
    } 
    
 
}]);