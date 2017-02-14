'use strict'
app.factory('EventService', ['$http', '$q' ,function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/event/';
    var EVENT_SERVICE_URI = 'http://localhost:8082/connectit/eventMember/'
 
    var factory = {
        fetchAllEvents: fetchAllEvents,
        fetchEvent:fetchEvent,
        createEvent: createEvent,
        updateEvent:updateEvent,
        deleteEvent:deleteEvent,
        addMember:addMember,
        listMembers:listMembers
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
    
    function listMembers(eventId){
    	var deferred = $q.defer();
        $http.get(EVENT_SERVICE_URI+eventId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching event members');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchEvent(eventId){
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+eventId)
            .then(
            function (response) {
                
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching particular event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
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
    
    function addMember(member, eventId){
   	 var deferred = $q.defer();
        $http.post(EVENT_SERVICE_URI+eventId, member)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating event member');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
   }
    
    function updateEvent(event, eventId) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+eventId, event)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteEvent(eventId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+eventId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);