test("ClientModel", function(){
 
 alert("ClientModel Test");
  jQuery.getJSON("model.json", function(data){
    

    var s = new catan.models.ClientModel(0, data);
                
    equal(s, null, "failed" );

  });

});


test("chat", function(){
 
 alert("Chat Test");
  jQuery.getJSON("model.json", function(data){

   // alert(JSON.stringify(data.chat.lines));
    var s = new catan.models.chat.Chat(data.chat);
     
     var len = s.getMessages().length;
    var len1 = data.chat.lines.length;
    ok(len1 == len,  "successed!");

  });

});

test("log", function(){
 
 alert("Log Test");
  
 jQuery.getJSON("model.json", function(data){

    //alert(JSON.stringify(data.log.lines));
    var s = new catan.models.chat.Log(data.log);
    var len = s.getMessages().length;
    var len1 = data.log.lines.length;
    ok(len1 == len,  "successed!");

  });
 
});

test("turn", function(){
  
   alert("Turn Test");

  jQuery.getJSON("model.json", function(data){

   // alert(JSON.stringify(data.turnTracker));
    var s = new catan.models.Turn(data.turnTracker);
    var phase =    data.turnTracker.status;
    var playerId = data.turnTracker.currentTurn;
    
    ok(s.getCurrentTurn() == playerId,  "successed in getCurrentTurn()");
    equal(s.getPhase(), phase, "successed in getPhase()");
    ok(s.isPlayingPhase() == true, "successed in isPlayingPhase()");
    ok(s.isDiscardingPhase() == false, "successed in isDiscardingPhase()");
    ok(s.isRollingPhase() == false, "successed in isRollingPhase");
    ok(s.isRobbingPhase() == false, "successed in isRobbing()");
  });
});



