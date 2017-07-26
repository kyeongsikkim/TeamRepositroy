<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name=viewport content="width=device-width initial-scale=1 user-scalable=no">
		<!-- CSS -->
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/css/clock.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/css/rssfeed.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/css/schedule.css" rel="stylesheet" type="text/css" />
		<!-- JS -->
		<script type="text/javascript" src="http://code.responsivevoice.org/responsivevoice.js"></script>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/annyang.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/startannyang.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/responsivevoice.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/checkscript.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/clock.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rssfeed.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/js/controller.js" type="text/javascript"></script>
		<!-- Rss execute -->
		<script>
			$(function() {
				requestNews();
				setInterval(requestNews, 600000);
			});
		</script>
		<script type="text/javascript">
		      // Client ID and API key from the Developer Console
		      var CLIENT_ID = '860148976497-anbq37go0m1tlfh0tdl47lcevfe25qds.apps.googleusercontent.com';
		
		      // Array of API discovery doc URLs for APIs used by the quickstart
		      var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];
		
		      // Authorization scopes required by the API; multiple scopes can be
		      // included, separated by spaces.
		      var SCOPES = "https://www.googleapis.com/auth/calendar.readonly";
		
		
		      /**
		       *  On load, called to load the auth2 library and API client library.
		       */
		      function handleClientLoad() {
		        gapi.load('client:auth2', initClient);
		      }
		
		      /**
		       *  Initializes the API client library and sets up sign-in state
		       *  listeners.
		       */
		      function initClient() {
		        gapi.client.init({
		          discoveryDocs: DISCOVERY_DOCS,
		          clientId: CLIENT_ID,
		          scope: SCOPES
		        }).then(function () {
		          // Listen for sign-in state changes.
		          gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);
		
		          // Handle the initial sign-in state.
		          updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
		        });
		      }
		
		      /**
		       *  Called when the signed in status changes, to update the UI
		       *  appropriately. After a sign-in, the API is called.
		       */
		      function updateSigninStatus(isSignedIn) {
		        if (isSignedIn) {
		          listUpcomingEvents();
		        } else {
		        }
		      }
		
		      /**
		       *  Sign in the user upon button click.
		       */
		      function handleSignIn() {
		        gapi.auth2.getAuthInstance().signIn();
		      }
		
		      /**
		       *  Sign out the user upon button click.
		       */
		      
		      function handleSignoutClick(event) {
		        gapi.auth2.getAuthInstance().signOut();
		      }
		
		      /**
		       * Append a pre element to the body containing the given message
		       * as its text node. Used to display the results of the API call.
		       *
		       * @param {string} message Text to be placed in pre element.
		       */
		      function appendDiv(message) {
		      	$("#schedule").append(message);
		      	$("#schedule").append("<br/>");
		      }
		
		      /**
		       * Print the summary and start datetime/date of the next ten events in
		       * the authorized user's calendar. If no events are found an
		       * appropriate message is printed.
		       */
		      function listUpcomingEvents() {
		        gapi.client.calendar.events.list({
		          'calendarId': 'primary',
		          'timeMin': (new Date()).toISOString(),
		          'showDeleted': false,
		          'singleEvents': true,
		          'maxResults': 5,
		          'orderBy': 'startTime'
		        }).then(function(response) {
		          var events = response.result.items;
		
		          if (events.length > 0) {
		            for (i = 0; i < events.length; i++) {
		              var event = events[i];
		              var when = new Date(Date.parse(event.start.dateTime));
		              when = when.toLocaleString();
		              when = when.substring(0, when.length-3);
		              if (!when) {
		                when = event.start.date;
		              }
		              appendDiv(event.summary + ' (' + when + ')')
		            }
		          } else {
		            appendDiv('No upcoming events found.');
		          }
		        });
		      }
	    </script>
	    <script async defer src="https://apis.google.com/js/api.js"
	      onload="this.onload=function(){};handleClientLoad()"
	      onreadystatechange="if (this.readyState === 'complete') this.onload()">
	    </script>
		<!-- Style -->
		<style>
			body {
				background-color: black;
				overflow: hidden;
			}
			/*.col-md-4 {
				border:1px solid white;
			}
			.col-md-3 {
				border:1px solid white;
			}
			.col-md-5 {
			 	border:1px solid white;
			}*/
		</style>
	</head>
	<body onload="handleSignIn()">
		<div>
			<div class="row" style="margin-top:2vh;">
				<div class="col-md-4"></div>
				<div class="col-md-3"></div>
				<div class="col-md-5">
					<div class="clock">
					<div id="Date"></div>
					<ul class="clockul">
					    <li id="hours" class="clockli"></li>
					    <li id="point" class="clockli">:</li>
					    <li id="min" class="clockli"></li>
					    <li id="sec" class="clocklis"></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-3"></div>
				<div class="col-md-5">
					<div id="rssheader"><img src="<%=application.getContextPath()%>/resources/images/newspaper.png" style="display:inline;width:2vw;height:2vw;margin:0.3vw 0.3vw 0.6vw 0.3vw;">Today's Headline</div>
					<div id="rssfeed"></div>
				</div>
			</div>
			<div class="row" style="margin-top:1.5vh;">
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div id="scheduleheader"><img src="<%=application.getContextPath()%>/resources/images/calendar.png" style="display:inline;width:2vw;height:2vw;margin:0.3vw 0.3vw 0.6vw 0.3vw;">Schedule</div>
					<div id="schedule"></div>
				</div>
			</div>
			<div id="content"></div>
		</div>
	</body>
</html>