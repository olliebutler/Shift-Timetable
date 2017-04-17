/***************************************************************************************
*    Title: React.js and Spring Data REST
*    Author: Greg Turnquist
*    Date: 17/4/17
*    Code version: 1.4.2
*    Availability: https://github.com/spring-guides/tut-react-and-spring-data-rest/tree/master/security
*
***************************************************************************************/


var SockJS = require('sockjs-client'); (1)
require('stompjs'); (2)

function register(registrations) {
	var socket = SockJS('/Timetable'); (3)
	var stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		registrations.forEach(function (registration) { (4)
			stompClient.subscribe(registration.route, registration.callback);
		});
	});
}

module.exports.register = register;