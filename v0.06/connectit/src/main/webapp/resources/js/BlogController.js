app.factory('Blog', ['$resource', function ($resource) {
    return $resource('http://localhost:8082/connectit/blog/:bId', {bId: '@blogId'},
	{
		updateBlog: {method: 'PUT'}
	}
    );
}]);
app.controller('BlogController', ['$scope', 'Blog', function($scope, Blog) {
    var ob = this;
    ob.blogs=[];
    ob.blog = new Blog(); 
    ob.fetchAllBlogs = function(){
        ob.blogs = Blog.query();
    };
    ob.fetchAllBlogs();
    ob.addBlog = function(){
	console.log('Inside save');
	if($scope.blogForm.$valid) {
	  ob.blog.$save(function(blog){
	     console.log(blog); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllBlogs();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editBlog = function(id){
	    console.log('Inside edit');
            ob.blog = Blog.get({ bId: id}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateBlogDetail = function(){
	console.log('Inside update');
	if($scope.blogForm.$valid) {
    	   ob.blog.$updateBlog(function(blog){
    		console.log(blog); 
		ob.updatedId = blog.blogId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllBlogs();
           });
	}
    };	
    ob.deleteBlog = function(id){
	    console.log('Inside delete');
	    ob.blog = Blog.delete({ bId: id}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllBlogs(); 
	    });
    };		
    ob.reset = function(){
    	ob.blog = new Blog();
        $scope.blogForm.$setPristine();
    };	
    ob.cancelUpdate = function(id){
	    ob.blog = new Blog();
	    ob.flag= '';	
   	    ob.fetchAllBlogs();
    };    
}]);