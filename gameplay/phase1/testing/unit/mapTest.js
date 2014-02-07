//This file is for testing the map class and the underlying classes.
var catan = catan || {};
catan.models = catan.models || {};
catan.models.map = catan.models.map || {};

test("map.CanBuildSettlement",function(){
  if(catan.models)
    console.log("good");
  else
    console.log("bad");
  
  var map = new catan.models.map.Map(modelJson.map);
  var ports = map.getPorts();
  ok(ports.length == modelJson.map.ports.length, "port list size");
  var hexLoc = new catan.models.map.HexLocation(2,0);
  ok(map.canBuildSettlement(0, hexLoc, "NW", false),"true: not setup phase");
  ok(!map.canBuildSettlement(0, hexLoc, "W", false), 
      "false: too close to another settlement");
  ok(!map.canBuildSettlement(0, hexLoc, "E", false), 
      "false: no road nearby");
  ok(map.canBuildSettlement(0, hexLoc, "E", true), 
      "true: Setup phase");
  ok(!map.canBuildSettlement(0, hexLoc, "SW", false), 
      "false: Occupied");
  
  hexLoc = new catan.models.map.HexLocation(3,0);
  ok(!map.canBuildSettlement(0, hexLoc, "E", false), 
      "false: Ocean Vertex");
  
  hexLoc = new catan.models.map.HexLocation(4,0);
  ok(!map.canBuildSettlement(0, hexLoc, "E", false), 
      "false: Non-existent hex");
});

test("Map.canBuildCity",function(){
  var map = new catan.models.map.Map(modelJson.map);
  var hexLoc = new catan.models.map.HexLocation(1,1);
  ok(map.canBuildCity(0,hexLoc,"W"), "True");
  ok(!map.canBuildCity(0,hexLoc,"NW"), "False: no settlement");
  ok(!map.canBuildCity(0,hexLoc,"E"), "False: alr  var resources = catan.model.ResourceList(0,0,0,0,2)eady a city");
});

test("Map.canBuildRoad",function(){
  var map = new catan.models.map.Map(modelJson.map);
  var hexLoc = new catan.models.map.HexLocation(1,1);
  ok(map.canBuildRoad(0,hexLoc,"N",false), "True");
  ok(!map.canBuildRoad(0,hexLoc,"S",false), "False: no nearby road")
  ok(map.canBuildRoad(0,hexLoc,"S",true), "True: setup near settlement");
  var hexLoc = new catan.models.map.HexLocation(0,0);
  ok(!map.canBuildRoad(0,hexLoc,"N",true), "False: setup not near settlement");
  ok(!map.canBuildRoad(0,hexLoc,"SE",false), "False: only enemy road nearby");
  ok(!map.canBuildRoad(0,hexLoc,"S",false), "False: occupied");
});

test("Map.canMaritimeTrade",function(){
  var map = new catan.models.map.Map(modelJson.map);
  var resources = catan.models.ResourceList(0,0,0,2,0);
  ok(map.canMaritimeTrade(0,resources), "True: Wheat Trade");
  var resources = catan.models.ResourceList(0,0,0,3,0)
  ok(map.canMaritimeTrade(0,resources), "False: no 3:1 port");
  var resources = catan.models.ResourceList(0,0,0,0,2)
  ok(map.canMaritimeTrade(0,resources), "False: wrong resource");
  
});



