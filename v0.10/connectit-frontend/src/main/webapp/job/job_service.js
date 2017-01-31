'use strict'
app.factory('JobService', ['$http', '$q' ,function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/';
 
    var factory = {
        fetchAllJobs: fetchAllJobs,
        fetchJob:fetchJob,
        createJob: createJob,
        applyJob:applyJob,
        jobRequests:jobRequests,
        acceptJobApplication:acceptJobApplication,
        rejectJobApplication:rejectJobApplication
       
    };
 
    return factory;
    
    function fetchAllJobs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'getAllJobs')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching jobs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function jobRequests(){
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'getAllJobApplications')
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while fetching job applications');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
    }
    
    function fetchJob(jobId){
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'job/'+jobId)
            .then(
            function (response) {
                
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching particular job');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createJob(job){
    	var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'createJob', job)
            .then(
            		function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while creating Job');
                        deferred.reject(errResponse);
                    }
            );
        return deferred.promise;
    }
    
    function applyJob(jobApplication,jobId){
    	var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'apply/'+jobId,jobApplication)
            .then(
            		function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while applying Job');
                        deferred.reject(errResponse);
                    }
            );
        return deferred.promise;
    }
    
    function acceptJobApplication(userId,jobId){
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'acceptJobApplication/'+userId+'/'+jobId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while accepting Job Application');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function rejectJobApplication(userId, jobId){
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'rejectJobApplication/'+userId+'/'+jobId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while rejecting Job Application');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);