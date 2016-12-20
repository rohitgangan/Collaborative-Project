
   <div ng-controller="BlogController as blogCtrl">
       <h1> Spring MVC 4 REST + AngularJS </h1>
	<form name="blogForm" method="POST">
	    <table>
		<tr><td colspan="2">
		  <div ng-if="blogCtrl.flag != 'edit'">
		     <h3> Add New Blog </h3> 
		  </div>
		  <div ng-if="blogCtrl.flag == 'edit'">
		     <h3> Update Blog for ID: {{ blogCtrl.blog.blogId }} </h3> 
		  </div> </td>
		</tr>
		<tr>
		      <td>Blog Name: </td> <td><input type="text" name="blogName" ng-model="blogCtrl.blog.blogName" required/> 
         	      <span ng-show="blogForm.blogName.$error.required" class="msg-val">Name is required.</span> </td>
		</tr>
		<tr>
		      <td>Content: </td> <td> <input type="text" name="blogContent" ng-model="blogCtrl.blog.blogContent" required/> 
	              <span ng-show="blogForm.blogContent.$error.required" class="msg-val">Content is required.</span> </td>
		</tr>
		<tr>
		     <td colspan="2"> <span ng-if="blogCtrl.flag=='created'" class="msg-success">Blog successfully added.</span>
		     <span ng-if="blogCtrl.flag=='failed'" class="msg-val">Blog already exists.</span> </td>
		</tr>
	        <tr><td colspan="2">
	            <div ng-if="blogCtrl.flag != 'edit'">
		       <input  type="submit" ng-click="blogCtrl.addBlog()" value="Add Blog"/> 
		       <input type="button" ng-click="blogCtrl.reset()" value="Reset"/>
		    </div>
		    <div ng-if="blogCtrl.flag == 'edit'">
		       <input  type="submit" ng-click="blogCtrl.updateBlogDetail()" value="Update Blog"/> 	
			   <input type="button" ng-click="blogCtrl.cancelUpdate()" value="Cancel"/>				   
		    </div> </td>
		</tr>
		<tr>
		     <td colspan="2"> <span ng-if="blogCtrl.flag=='deleted'" class="msg-success">Blog successfully deleted.</span>
		</tr>
	    </table>     
	</form>
         <table>
	      <tr><th>ID </th> <th>Name</th> <th>Location</th></tr>
	      <tr ng-repeat="row in blogCtrl.blogs">
	         <td><span ng-bind="row.blogId"></span></td>
	         <td><span ng-bind="row.blogName"></span></td>
	         <td><span ng-bind="row.blogContent"></span></td>
	         <td>
		    <input type="button" ng-click="blogCtrl.deleteBlog(row.blogId)" value="Delete"/>
		    <input type="button" ng-click="blogCtrl.editBlog(row.blogId)" value="Edit"/>
		    <span ng-if="blogCtrl.flag=='updated' && row.blogId==blogCtrl.updatedId" class="msg-success">Blog successfully updated.</span> </td> 
	      </tr>	
	</table>
	</div>
       
	
 </body>
</html> 