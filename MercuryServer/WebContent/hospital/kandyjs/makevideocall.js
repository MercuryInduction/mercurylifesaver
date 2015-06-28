/**
 * New node file
 */
 $(function() {
        var callId, username;
      
        // Create audio objects to play incoming calls and outgoing calls sound
      var $audioRingIn = $('<audio>', { loop: 'loop', id: 'ring-in' });
      var $audioRingOut = $('<audio>', { loop: 'loop', id: 'ring-out' });
      
      // Load audio source to DOM to indicate call events
      var audioSource = {
        ringIn: [
          { src: 'https://kandy-portal.s3.amazonaws.com/public/sounds/ringin.mp3', type: 'audio/mp3' },
          { src: 'https://kandy-portal.s3.amazonaws.com/public/sounds/ringin.ogg', type: 'audio/ogg' }
          ],
        ringOut: [
          { src: 'https://kandy-portal.s3.amazonaws.com/public/sounds/ringout.mp3', type: 'audio/mp3' },
          { src: 'https://kandy-portal.s3.amazonaws.com/public/sounds/ringout.ogg', type: 'audio/ogg' }]
      };
      
      audioSource.ringIn.forEach(function(entry) {
        var $source = $('<source>').attr('src', entry.src);
        $audioRingIn.append($source);
      });
      
      audioSource.ringOut.forEach(function(entry) {
        var $source = $('<source>').attr('src', entry.src);
        $audioRingOut.append($source);
      });
      
        /** setup(config) intializes kandy
          @param <object> config
        */
        kandy.setup({
          //kandyApiUrl: &#39;&#39;,
          remoteVideoContainer: $('#incoming-video')[0],
          localVideoContainer: $('#outgoing-video')[0],
          // listeners registers events to handlers
          // You can handle all Kandy Events by registering it here
          listeners: {
            callinitiated: onCallInitiate,
            callinitiatefailed: onCallInitiateFail,
            oncall: onCall,
            callended: onCallTerminate,
            callendedfailed: onCallEndedFailed
          }
        });
      
        /** UIState is a custom piece of code that shuffles between UI states
          eg:: If user is authenticated, the relevant DOM elements are brought to screen
          and the rest are hidden. Using this method is NOT recommended!
      */
      
      var username, UIState = {};
      
      UIState.authenticated = function() {
    	  alert("authenticate");
//        $('#login-form').addClass('hidden');
//        $('#logged-in').removeClass('hidden');
//        $('.username').text(username);
      };
      
      UIState.unauthenticated = function() {
    	  alert("unauthenticate");
//        $('#login-form').removeClass('hidden');
//        $('#logged-in').addClass('hidden');
//        $('.username').text('');
      };
      
      UIState.initial = function() {
        console.log('initial');
      
        $audioRingIn[0].pause();
        $audioRingOut[0].pause();
      
        $('#call-form p, #incoming-call p, #call-connected p').text('');
        $('#incoming-call, #call-connected, .call-terminator, #resume-call-btn').addClass('hidden');
        $('#call-form, .call-initializer').removeClass('hidden')
      };
        // Event handler for login form button
      var userArray = [];
      $('#login-btn').on('click', function(e) {
        e.preventDefault();
      
        // Values extracted from login form
//        username = $('#username').val();
//        var apiKey = $('#api_key').val();
//        var password = $('#password').val();
        var apiKey="DAK1728a510d9e4439bb4f1fc7fd53acfab";
   	 var kandyusername="user2@mercuryinduction.gmail.com";
   	 var kandypassword="Induction@25";
        /** login(domainApiId, userName, password,success,failure)
            logs in user to Kandy Platform
            @params <string> domainApiId, <string> userName, <string> password, <function> success/failure
        */
        kandy.login(apiKey, kandyusername, kandypassword,function(msg){
      
               userArray.push(kandyusername);
               kandy.getLastSeen(userArray);
               UIState.authenticated();
           },	
           function(msg){
               UIState.unauthenticated();
               alert('Login Failed!');
           });
      });// Event handler for logout button
      $('#logout-btn').on('click', function(e) {
        e.preventDefault();
        /** logout(success) logs a user out of the Kandy Platform
            @param <function> success - Callback handler for
            successful logout
        */
        kandy.logout(function() {
          userArray.push(username);
          kandy.getLastSeen(userArray);
          UIState.unauthenticated();
        });
      });
        // Event handler for callinitiate
      function onCallInitiate(call) {
        callId = call.getId();
      
        $audioRingIn[0].pause();
        $audioRingOut[0].play();
      
        $('#username-calling').text('Calling ' + $('#user_to_call').val());
        UIState.callinitialized();
      }
      
      // Event handler for callinitiatefail event
      function onCallInitiateFail() {
        console.debug('call initiate fail');
      
        $audioRingOut[0].pause();
        UIState.initial();
        alert('call failed');
      }
      
      UIState.callinitialized = function() {
        console.log('callinitialized');
      
        $('.call-initializer').addClass('hidden');
      };// Event handler for initiate call button
      $('#initialize-call-btn').on('click', function() {
        var username = $('#user_to_call').val();
      
        /** makeCall( userName, cameraOn ) : Void
            Initiates a call to another Kandy user over web
            @params <string> userName, <boolean> cameraOn
        */
        kandy.call.makeCall(username, true);
      });
        // Event handler for callincoming event
            function onCallIncoming(call, isAnonymous) {
              $audioRingIn[0].play();
              callId = call.getId();
            
              if (!isAnonymous) {
                $('#username-incoming').text(call.callerName + ' Calling!');
              } else {
                $('#username-incoming').text('Anonymous Calling');
              }
            
              UIState.callincoming();
            }
            // Event handler for oncallanswered event
            function onCallAnswer(call) {
              callId = call.getId();
            
              $audioRingOut[0].pause();
              $audioRingIn[0].pause();
      
            }
            
            // Event handler for callansweredfailed event
            function onCallAnsweredFailed(call) {
              console.debug('callanswerfailed');
              callId = null;
            }
            
            // Event handler for callrejected event
            function onCallRejected() {
              console.debug('callrejected');
              callId = null;
              $audioRingIn[0].pause();
              UIState.callrejected();
              alert('Call Rejected');
            }
            
            // Event handler for callrejectfailed event
            function onCallRejectFailed() {
              console.debug('callrejectfailed');
              alert('Call Decline Failed');
            }
      // Event handler for oncall event
      function onCall(call) {
        console.debug('oncall');
        $audioRingOut[0].pause();
        UIState.oncall();
      }
      
      // Event handler for callended event
      function onCallTerminate(call) {
        console.debug('callended');
        callId = null;
      
        $audioRingOut[0].play();
        $audioRingIn[0].pause();
      
        UIState.initial();
      }
      
      // Event handler for callendedfailed event
      function onCallEndedFailed() {
        console.debug('callendfailed');
        callId = null;
      }
      
      $('#hold-call-btn').on('click', function() {
        kandy.call.holdCall(callId);
        UIState.holdcall();
      });
      
      $('#resume-call-btn').on('click', function() {
        kandy.call.unHoldCall(callId);
        UIState.resumecall();
      });
      
      // Event handler for call end button
      $('#end-call-btn').on('click', function() {
        kandy.call.endCall(callId);
        UIState.initial();
      });
      
      UIState.oncall = function() {
        console.log('oncall');
      
        $('#incoming-call, #call-form').addClass('hidden');
        $('#call-connected').removeClass('hidden');
      };
      
      UIState.holdcall = function() {
        console.log('holdcall');
      
        $('#hold-call-btn').addClass('hidden');
        $('#resume-call-btn').removeClass('hidden');
      };
      
      UIState.resumecall = function() {
        console.log('resumecall');
      
        $('#hold-call-btn').removeClass('hidden');
        $('#resume-call-btn').addClass('hidden');
      };
      
      });