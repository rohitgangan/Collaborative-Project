'use strict'
app.controller('JobController',['JobService','$scope','$http','$location',function(JobService,$scope,$http,$location){

	var self=this;

self.job={jobId:null,title:'',qualification:'',description:''};
self.jobs=[];
self.jobApplication={jobApplicationId:null};
self.jobApplications=[];
self.submit = submit;
self.apply = apply;
self.edit = edit;
self.reset = reset;
self.viewJob = viewJob;
$scope.allJobs=self.jobs;

fetchAllJobs();
fetchAllJobRequests();

function fetchAllJobs(){
	 JobService.fetchAllJobs()
     .then(
     function(d) {
         self.jobs = d;
     },
     function(errResponse){
         console.error('Error while fetching jobs');
     }
 );
}

function fetchAllJobRequests(){
	 JobService.jobRequests()
    .then(
    function(d) {
        self.jobApplications = d;
    },
    function(errResponse){
        console.error('Error while fetching job applications');
    }
);
}

function createJob(job){
    JobService.createJob(job)
        .then(
        fetchAllJobs,
        function(errResponse){
            console.error('Error while creating job');
        }
    );
}



function updateBlog(job, jobId){
    JobService.updateJob(job, jobId)
        .then(
        fetchAllJobs,
        function(errResponse){
            console.error('Error while updating job');
        }
    );
}

function submit() {
    if(self.job.jobId===null){
        console.log('Saving New job', self.job);
        createJob(self.job);
    }else{
        updateJob(self.job, self.job.jobId);
        console.log('Blog updated with id ', self.job.jobId);
    }
    reset();
}

function edit(jobId){
    console.log('id to be edited', jobId);
    for(var i = 0; i < self.jobs.length; i++){
        if(self.jobs[i].jobId === jobId) {
            self.job = angular.copy(self.jobs[i]);
            break;
        }
    }
}

function reset(){
    self.job={jobId:null,title:'',qualification:'',description:''};
    $scope.jobForm.$setPristine(); //reset Form
}

function viewJob(jobId){
	$http.get('http://localhost:8082/connectit/job/'+jobId).then
	console.log('job to view ', jobId);
     for(var i = 0; i < self.jobs.length; i++){
         if(self.jobs[i].jobId === jobId) {
             self.jobview = angular.copy(self.jobs[i]);
             break;
             
         }
     }

}



function refresh(){
	
	location.reload();
} 

function apply(jobApplication,jobId){
	 
	JobService.applyJob(self.jobApplication,jobId)
	 
     .then(
    		 console.log('creating new application',self.jobApplication),		 
     function(errResponse){
         console.error('Error while creating job application');
     }
 );
}

self.acceptJobApplication= function(userId,jobId){
	 JobService.acceptJobApplication(userId,jobId)
    .then(
    		console.log('accepting Job Application successfull'),
    		location.reload(),
    function(response){
        console.error('accepting Job Application successfull');
        
    }
);
}

self.rejectJobApplication= function(userId,jobId){
	 JobService.rejectJobApplication(userId,jobId)
    .then(
    		console.log('rejecting JobApplication successfull'),
    		location.reload(),
    function(response){
        console.error('rejecting JobApplication successfull');
        
    }
);
}

}]);