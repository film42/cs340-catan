//test("ClientModel", function(){
// 
// //alert("ClientModel Test");
//  jQuery.getJSON("model.json", function(data){
//    
//
//    var s = new catan.models.ClientModel(0, data);
//                
//    equal(s, null, "failed" );
//
//  });
//
//});
//
//
//test("chat", function(){
// 
// //alert("Chat Test");
//  jQuery.getJSON("model.json", function(data){
//
//   // alert(JSON.stringify(data.chat.lines));
//    var s = new catan.models.chat.Chat(data.chat);
//     
//     var len = s.getMessages().length;
//    var len1 = data.chat.lines.length;
//    ok(len1 == len,  "successed!");
//
//  });
//
//});
//
//test("log", function(){
// 
// //alert("Log Test");
//  
// jQuery.getJSON("model.json", function(data){
//
//    //alert(JSON.stringify(data.log.lines));
//    var s = new catan.models.chat.Log(data.log);
//    var len = s.getMessages().length;
//    var len1 = data.log.lines.length;
//    ok(len1 == len,  "successed!");
//
//  });
// 
//});
//
//test("turn", function(){
//  
//   //alert("Turn Test");
//
//  jQuery.getJSON("model.json", function(data){
//
//   // alert(JSON.stringify(data.turnTracker));
//    var s = new catan.models.Turn(data.turnTracker);
//    var phase =    data.turnTracker.status;
//    var playerId = data.turnTracker.currentTurn;
//    
//    ok(s.getCurrentTurn() == playerId,  "successed in getCurrentTurn()");
//    equal(s.getPhase(), phase, "successed in getPhase()");
//    ok(s.isPlayingPhase() == true, "successed in isPlayingPhase()");
//    ok(s.isDiscardingPhase() == false, "successed in isDiscardingPhase()");
//    ok(s.isRollingPhase() == false, "successed in isRollingPhase");
//    ok(s.isRobbingPhase() == false, "successed in isRobbing()");
//  });
//});

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

