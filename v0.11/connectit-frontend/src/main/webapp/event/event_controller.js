'use strict'

app.controller('EventController', ['$scope', 'EventService','$http','$location',function($scope, EventService,$http,$location) {
   
	
	

	
	var self = this;
    self.event={eventId:null,eventName:'',eventDetails:''};
    self.events=[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.viewEvent = viewEvent;
    
    
 
    fetchAllEvents();
 
    function fetchAllEvents(){
    	EventService.fetchAllEvents()
            .then(
            function(d) {
                self.events = d;
            },
            function(errResponse){
                console.error('Error while fetching Events');
            }
        );
    }
    
    function fetchEvent(EventId){
    	EventService.fetchEvent(EventId)
            .then(
            function(d) {
                self.viewEvent = d;
            },
            function(errResponse){
                console.error('Error while fetching Event');
            }
        );
    }
    
    
    
    function createEvent(event){
    	EventService.createEvent(event)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while creating Event');
            }
        );
    }
    
    
    
    function updateEvent(event, eventId){
        EventService.updateEvent(event, eventId)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while updating event');
            }
        );
    }
 
    function deleteEvent(eventId){
        EventService.deleteEvent(eventId)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while deleting event');
            }
        );
    }
 
    
    
    function submit() {
        if(self.event.eventId===null){
            console.log('Saving New Event', self.event);
            createEvent(self.event);
        }else{
            updateEvent(self.event, self.event.eventId);
            console.log('Event updated with id ', self.event.eventId);
        }
        reset();
    }
 
    function edit(eventId){
        console.log('id to be edited', eventId);
        for(var i = 0; i < self.events.length; i++){
            if(self.events[i].eventId === eventId) {
                self.event = angular.copy(self.events[i]);
                break;
            }
        }
    }
    
    function viewEvent(eventId){
    	$http.get('http://localhost:8082/connectit/event/'+eventId).then
    	console.log('event to view ', eventId);
         for(var i = 0; i < self.events.length; i++){
             if(self.events[i].eventId === eventId) {
                 self.eventview = angular.copy(self.events[i]);
                 break;
                 
             }
         }

    }
    
    function remove(eventId){
        console.log('id to be deleted', eventId);
        if(self.event.blogId === eventId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteEvent(eventId);
    }
 
 
    function reset(){
    	self.event={eventId:null,eventName:'',eventDetails:''};
        $scope.eventForm.$setPristine(); //reset Form
    }
    
   
 
}]);