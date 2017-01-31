'use strict'
app.factory('EventService', ['$http', '$q' ,function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/event/';
 
    var factory = {
        fetchAllEvents: fetchAllevents,
        fetchEvent:fetchEvent,
        createEvent: createEvent,
        
    };
    
    return factory;
    
    function fetchAllEvents(){
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchEvent(){
    	
    }
    
    function createEvent(event){
    	 var deferred = $q.defer();
         $http.post(REST_SERVICE_URI, event)
             .then(
             function (response) {
                 deferred.resolve(response.data);
             },
             function(errResponse){
                 console.error('Error while creating event');
                 deferred.reject(errResponse);
             }
         );
         return deferred.promise;
    }
}]);