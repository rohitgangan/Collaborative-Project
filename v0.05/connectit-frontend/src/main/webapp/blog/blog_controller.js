'use strict';
 
app.controller('BlogController', ['$scope', 'BlogService', 'textAngularManager','$http','$location',function($scope, BlogService,textAngularManager,$http,$location) {
   
	
	$scope.version = textAngularManager.getVersion();
    $scope.versionNumber = $scope.version.substring(1);
    $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE9+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';

	
	var self = this;
    self.blog={blogId:null,blogName:'',blogContent:''};
    self.blogs=[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.viewBlog = viewBlog;
    
    
 
    fetchAllBlogs();
 
    function fetchAllBlogs(){
        BlogService.fetchAllBlogs()
            .then(
            function(d) {
                self.blogs = d;
            },
            function(errResponse){
                console.error('Error while fetching blogs');
            }
        );
    }
    
    function fetchBlog(blogId){
        BlogService.fetchBlog(blogId)
            .then(
            function(d) {
                self.viewBlog = d;
            },
            function(errResponse){
                console.error('Error while fetching blog');
            }
        );
    }
    
    
    
    function createBlog(blog){
        BlogService.createBlog(blog)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while creating blog');
            }
        );
    }
    
    
    
    function updateBlog(blog, blogId){
        BlogService.updateBlog(blog, blogId)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while updating blog');
            }
        );
    }
 
    function deleteBlog(blogId){
        BlogService.deleteBlog(blogId)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while deleting Blog');
            }
        );
    }
 
    
    
    function submit() {
        if(self.blog.blogId===null){
            console.log('Saving New Blog', self.blog);
            createBlog(self.blog);
        }else{
            updateBlog(self.blog, self.blog.blogId);
            console.log('Blog updated with id ', self.blog.blogId);
        }
        reset();
    }
 
    function edit(blogId){
        console.log('id to be edited', blogId);
        for(var i = 0; i < self.blogs.length; i++){
            if(self.blogs[i].blogId === blogId) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }
        }
    }
    
    function viewBlog(blogId){
    	$http.get('http://localhost:8082/connectit/blog/'+blogId).then
    	console.log('blog to view ', blogId);
         for(var i = 0; i < self.blogs.length; i++){
             if(self.blogs[i].blogId === blogId) {
                 self.blogview = angular.copy(self.blogs[i]);
                 break;
                 
             }
         }

    }
    
    function remove(blogId){
        console.log('id to be deleted', blogId);
        if(self.blog.blogId === blogId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBlog(blogId);
    }
 
 
    function reset(){
        self.blog={blogId:null,blogName:'',blogContent:''};
        $scope.blogForm.$setPristine(); //reset Form
    }
    
   
 
}]);