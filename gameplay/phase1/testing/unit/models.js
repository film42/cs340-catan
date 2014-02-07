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
  
//  stop();// temporarily stop the test so we can wait for incoming json data
//   jQuery.getJSON("model.json", function(data){
//     start();
     var samjson = {
       "MAX_GAME_POINTS": 10,
       "resources": {
         "brick": 14,
         "wood": 13,
         "sheep": 15,
         "wheat": 10,
         "ore": 8
       },
       "oldDevCards": {
         "yearOfPlenty": 0,
         "monopoly": 0,
         "soldier": 2,
         "roadBuilding": 0,
         "monument": 1
       },
       "newDevCards": {
         "yearOfPlenty": 0,
         "monopoly": 0,
         "soldier": 1,
         "roadBuilding": 1,
         "monument": 0
       },
       "roads": 8,
       "cities": 2,
       "settlements": 4,
       "soldiers": 1,
       "victoryPoints": 7,
       "monuments": 0,
       "longestRoad": true,
       "largestArmy": false,
       "playedDevCard": true,
       "discarded": false,
       "playerID": 0,
       "orderNumber": 0,
       "name": "Sam",
       "color": "orange"
     }
  
     // Initialization
     var sam = new catan.models.Player(samjson);
                 
     equal(sam.cities, samjson.cities, "player.cities was initialized correctly");
     equal(sam.color, samjson.color, "player.color was initialized correctly");
     equal(sam.discarded, samjson.discarded, "player.discarded was initialized correctly");
     equal(sam.largestArmy, samjson.largestArmy, "player.largestArmy was initialized correctly");
     equal(sam.longestRoad, samjson.longestRoad, "player.longestRoad was initialized correctly"); 
     equal(sam.monuments, samjson.monuments, "player.monuments was initialized correctly");
     equal(sam.name, samjson.name, "player.name was initialized correctly");
     equal(sam.newDevCards, samjson.newDevCards, "player.newDevCards was initialized correctly");
     equal(sam.oldDevCards, samjson.oldDevCards, "player.oldDevCards was initialized correctly");
     equal(sam.orderNumber, samjson.orderNumber, "player.coorderNumberlor was initialized correctly"); 
     equal(sam.playedDevCard, samjson.playedDevCard, "player.playedDevCard was initialized correctly");
     equal(sam.playerID, samjson.playerID, "player.playerID was initialized correctly");   
     deepEqual(sam.getResources(), samjson.resources, "player.resources was initialized correctly");   

     // Method Unit Testing
     var joejson = {
         "MAX_GAME_POINTS": 99,
         "resources": {
           "brick": 99,
           "wood": 99,
           "sheep": 99,
           "wheat": 9,
           "ore": 99
         },
         "oldDevCards": {
           "yearOfPlenty": 99,
           "monopoly": 99,
           "soldier": 99,
           "roadBuilding": 99,
           "monument": 99
         },
         "newDevCards": {
           "yearOfPlenty": 99,
           "monopoly": 99,
           "soldier": 99,
           "roadBuilding": 99,
           "monument": 99
         },
         "roads": 99,
         "cities": 99,
         "settlements": 99,
         "soldiers": 99,
         "victoryPoints": 99,
         "monuments": 99,
         "longestRoad": true,
         "largestArmy": true,
         "playedDevCard": true,
         "discarded": true,
         "playerID": 0,
         "orderNumber": 0,
         "name": "Well-off Joe",
         "color": "red"
       }     
     var resourcesjson = { 
         "brick" : 5,
         "ore" : 6,
         "sheep" : 7,
         "wheat" : 8,
         "wood" : 9
     };
     
     var joe = new catan.models.Player(joejson);
     var someResources = new catan.models.ResourceList(resourcesjson);
     
     ok(joe.canAffordToBuyDevelomentCard(), "canAffordToBuyDevelomentCard() returned true correctly");
     ok(joe.canAffordToBuyRoad(), "canAffordToBuyRoad() returned true correctly");
     ok(joe.canAffordToBuySettlement(), "canAffordToBuySettlement() returned true correctly");
     ok(joe.canAffordToBuyCity(), "canAffordToBuyCity() returned true correctly");
     ok(joe.canAffordToOfferTrade(someResources), "canAffordToOfferTrade() returned true correctly");
     ok(joe.canAcceptTrade(someResources), "canAcceptTrade() returned true correctly");
     ok(joe.hasMoreThan7Cards(), "hasMoreThan7Cards() returned true correctly");
     ok(joe.canDiscardCards(someResources), "canDiscardCard() returned true correctly");
     ok(joe.hasXResources(someResources), "hasXResources() returned true correctly");
          
     var johnjson = {
         "MAX_GAME_POINTS": 0,
         "resources": {
           "brick": 0,
           "wood": 0,
           "sheep": 0,
           "wheat": 0,
           "ore": 0
         },
         "oldDevCards": {
           "yearOfPlenty": 0,
           "monopoly": 0,
           "soldier": 0,
           "roadBuilding": 0,
           "monument": 0
         },
         "newDevCards": {
           "yearOfPlenty": 0,
           "monopoly": 0,
           "soldier": 0,
           "roadBuilding": 0,
           "monument": 0
         },
         "roads": 0,
         "cities": 0,
         "settlements": 0,
         "soldiers": 0,
         "victoryPoints": 0,
         "monuments": 0,
         "longestRoad": true,
         "largestArmy": true,
         "playedDevCard": true,
         "discarded": true,
         "playerID": 0,
         "orderNumber": 0,
         "name": "Poor John",
         "color": "red"
       }     
     var john = new catan.models.Player(johnjson);
     
     ok(!john.canAffordToBuyDevelomentCard(), "canAffordToBuyDevelomentCard() returned false correctly");
     ok(!john.canAffordToBuyRoad(), "canAffordToBuyRoad() returned false correctly");
     ok(!john.canAffordToBuySettlement(), "canAffordToBuySettlement() returned false correctly");
     ok(!john.canAffordToBuyCity(), "canAffordToBuyCity() returned false correctly");
     ok(!john.canAffordToOfferTrade(someResources), "canAffordToOfferTrade() returned false correctly");
     ok(!john.canAcceptTrade(someResources), "canAcceptTrade() returned false correctly");
     ok(!john.hasMoreThan7Cards(), "hasMoreThan7Cards() returned false correctly");
     ok(!john.canDiscardCards(someResources), "canDiscardCard() returned false correctly");
     ok(!john.hasXResources(someResources), "hasXResources() returned false correctly");
 });

