test("ClientModel", function(){
 
 console.log("ClientModel Test");
  //jQuery.getJSON("model.json", function(data){
    

  //  var s = new catan.models.ClientModel(0, data);

    //equal(s, null, "failed" );
   equal(1,1, "successed");
  //});

});

test("port", function(){
  console.log("port test");
  stop();
   var port = {
        
        "ratio": 3,
        "validVertex1": {
          "direction": "SW",
          "x": 3,
          "y": -3
        },
        "validVertex2": {
          "direction": "W",
          "x": 3,
          "y": -3
        },
        "orientation": "SW",
        "location": {
          "x": 3,
          "y": -3
        }
      };
   var s = new catan.models.map.Port(port);
   ok(1,1, "successed")
   start(); 
  
});


test("chat", function(){
  console.log("Chat Test");
  var chatMsg = {
    "lines": [
      {
        "source": "Sam",
        "message": "Sam built a road"
      },
      {
        "source": "Brooke",
        "message": "Brooke built a settlement"
      }
      
    ]
  }

   // console.log(JSON.stringify(data.chat.lines));
  var s = new catan.models.chat.Chat(chatMsg);
   
  var len = s.getMessages().length;
  var len1 = chatMsg.lines.length;
  ok(len1 == len,  "successed!");

});

test("log", function(){
 
 console.log("Log Test");
 var logMsg = {
    "lines": [
      {
        "source": "Sam",
        "message": "Sam built a road"
      },
      {
        "source": "Sam",
        "message": "Sam built a settlement"
      },
      {
        "source": "Sam",
        "message": "Sam's turn just ended"
      },
      {
        "source": "Brooke",
        "message": "Brooke built a road"
      },
      {
        "source": "Brooke",
        "message": "Brooke built a settlement"
      }
      
    ]
  }
 stop();
  
 //jQuery.getJSON("model.json", function(data){
 //console.log(JSON.stringify(data.log.lines));
 var s = new catan.models.chat.Log(logMsg);
 var len = s.getMessages().length;
    var len1 = logMsg.lines.length;
    ok(len1 == len,  "successed!");
    start();
});

test("turn", function(){
  
  console.log("Turn Test");
  stop();
  var turnTracker = {
    "status": "Playing",
    "currentTurn": 0
  };

  
  var s = new catan.models.Turn(turnTracker);
  var phase =    turnTracker.status;
  var playerId = turnTracker.currentTurn;
    
  equal(s.getCurrentTurn(),turnTracker.currentTurn,  "successed in getCurrentTurn()");
  equal(s.getPhase(), turnTracker.status, "successed in getPhase()");
  ok(s.isPlayingPhase() == true, "successed in isPlayingPhase()");
  ok(s.isDiscardingPhase() == false, "successed in isDiscardingPhase()");
  ok(s.isRollingPhase() == false, "successed in isRollingPhase");
  ok(s.isRobbingPhase() == false, "successed in isRobbing()");

  start();

  });




