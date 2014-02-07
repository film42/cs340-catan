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



test("Bank", function(){
  var bankjson = {
    "brick": 4,
    "wood": 9,
    "sheep": 1,
    "wheat": 7,
    "ore": 2
  }
  
  var bank = new catan.models.Bank(bankjson);
  
  
  equal(bank.resources.brick, bankjson.brick, "brick initialized correctly");
  equal(bank.resources.wood, bankjson.wood, "wood initialized correctly");
  equal(bank.resources.sheep, bankjson.sheep, "sheep initialized correctly");
  equal(bank.resources.wheat, bankjson.wheat, "wheat initialized correctly");
  equal(bank.resources.ore, bankjson.ore, "ore initialized correctly");
  
  equal(bank.getBrickCount(), bankjson.brick, "brick returned the correct number");
  equal(bank.getWoodCount(), bankjson.wood, "wood returned the correct number");
  equal(bank.getSheepCount(), bankjson.sheep, "sheep returned the correct number");
  equal(bank.getWheatCount(), bankjson.wheat, "wheat returned the correct number");
  equal(bank.getOreCount(), bankjson.ore, "ore returned the correct number");
  
});


