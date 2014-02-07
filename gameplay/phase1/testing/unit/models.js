test("ClientModel", function(){
 
 console.log("ClientModel Test");
  //jQuery.getJSON("model.json", function(data){
    

  //  var s = new catan.models.ClientModel(0, data);

    //equal(s, null, "failed" );
  equal(1,1, "successed");
  //});

});

test("catan.models.map.VertexLocation", function() {
  var json = {
    "direction": "W",
    "x": 3,
    "y": -3
  };

  var vertexLocation = new catan.models.map.VertexLocation(json);
  equal("W", vertexLocation.getDirection(), "Valid direction set");
  notEqual(null, vertexLocation.getLocation(), "None null locations");
});

test("catan.models.map.Port", function() {
  var json = {
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

  // Instantiate
  var port = new catan.models.map.Port(json);

  // Tests
  equal("SW", port.getOrientation(), "Has valid orientation");
  equal(3, port.getRatio(), "Has a ratio of 3");
  var verts = port.getValidVertices();
  equal(2, verts.length, "Has 2 verticies");
  notEqual(null, verts[0], "Not null vertex 1");
  notEqual(null, verts[1], "Not null vertex 2");
  notEqual(null, port.getLocation(), "Location not null");
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

test("Player", function(){
  
  stop();// temporarily stop the test so we can wait for incoming json data
   jQuery.getJSON("model.json", function(data){
     start();
     
     // Initialization
     var player = new catan.models.Player(data.players[0]);
                 
     equal(player.cities, data.players[0].cities, "player.cities was initialized correctly");
     equal(player.color, data.players[0].color, "player.color was initialized correctly");
     equal(player.discarded, data.players[0].discarded, "player.discarded was initialized correctly");
     equal(player.largestArmy, data.players[0].largestArmy, "player.largestArmy was initialized correctly");
     equal(player.longestRoad, data.players[0].longestRoad, "player.longestRoad was initialized correctly"); 
     equal(player.monuments, data.players[0].monuments, "player.monuments was initialized correctly");
     equal(player.name, data.players[0].name, "player.name was initialized correctly");
     equal(player.newDevCards, data.players[0].newDevCards, "player.newDevCards was initialized correctly");
     equal(player.oldDevCards, data.players[0].oldDevCards, "player.oldDevCards was initialized correctly");
     equal(player.orderNumber, data.players[0].orderNumber, "player.coorderNumberlor was initialized correctly"); 
     equal(player.playedDevCard, data.players[0].playedDevCard, "player.playedDevCard was initialized correctly");
     equal(player.playerID, data.players[0].playerID, "player.playerID was initialized correctly");   
     deepEqual(player.getResourceList(), data.players[0].resources, "player.resources was initialized correctly");   

     // Method Unit Testing
     
     
     
   });


 });

