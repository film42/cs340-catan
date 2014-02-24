/**
	This this contains interfaces used by the map and robber views
	@module catan.map
	@namespace map
*/

var catan = catan || {};
catan.map = catan.map || {};

catan.map.Controller = (function catan_controller_namespace() {
	
  var EdgeLoc = catan.map.View.EdgeLoc;
	var VertexLoc = catan.map.View.VertexLoc;
	var PortLoc = catan.map.View.PortLoc;
    
	/*var HexLocation = catan.models.hexgrid.HexLocation;
	var VertexLocation = catan.models.hexgrid.VertexLocation;
	var EdgeLocation= catan.models.hexgrid.EdgeLocation;
	var VertexDirection = catan.models.hexgrid.VertexDirection;
	var EdgeDirection= catan.models.hexgrid.EdgeDirection;   
	*/
	var MapController = (function main_controller_class() {
    
 		core.forceClassInherit(MapController,catan.core.BaseController);
        
		core.defineProperty(MapController.prototype,"robView");
		core.defineProperty(MapController.prototype,"modalView");
        
        /**
		 * @class MapController
		 * @constructor
		 * @param {MapView} view - The initialized map view
		 * @param {MapOverlay} modalView - The overlay to use for placing items on the board.
		 * @param {ClientModel} model - The client model
		 * @param {RobberOverlay} robView - The robber overlay to be used when the robber is being placed.  This is undefined for the setup round.
		 */
		function MapController(view, modalView, model, robView){
			catan.core.BaseController.call(this,view,model.getModel());
			this.setModalView(modalView);
			this.setRobView(robView);
			this.game = model;
			this.initialized = false;
			this.modalOn = false;
			this.soldier = false;
			this.robloc = {};
			this.roadbuild = false;
			this.firstroad = false;
			this.firstroadloc;
			this.game.addObserver(this, this.updateFromModel);
		}

		MapController.prototype.initFromModel = function(){
			this.ClientModel = this.game.getModel();
			this.populateHexes();
			this.populatePorts();
			this.populateNumbers();
		}

		MapController.prototype.updateFromModel = function(){
			if(!this.initialized){
				this.initFromModel();
				this.initialized = true;
			}
			this.ClientModel = this.game.getModel();
			this.populatePieces();
			this.doRobberPhase();
		}

		MapController.prototype.populateHexes = function populateHexes(){
			var map = this.ClientModel.getMap(); //do we have a game or a model here? getModel() if need be
			var hexgrid = map.getHexGrid(); 
			var hexes = hexgrid.getHexArray();
			for (var i = 0; i < hexes.length; i++){
				for(var j = 0; j < hexes[i].length; j++){
					var loc = hexes[i][j].getLocation();
					this.View.addHex({x:loc.getX(), y:loc.getY()}, hexes[i][j].getLandType().toLowerCase());
				}
			}
		}

		MapController.prototype.populatePorts = function populatePorts(){
			var map = this.ClientModel.getMap();
			var ports = map.getPorts();
			for(var i = 0; i < ports.length; i++){
				var loc = ports[i].getLocation();
				var portloc = new catan.map.View.PortLoc(loc.getX(), loc.getY(), catan.models.map.HexDirection[ports[i].orientation]);
				var type = ports[i].getType();
				if(type){
					this.View.addPort(portloc, type.toLowerCase());					
				}
				else{
					this.View.addPort(portloc, "three");
				}
			}
		}

		MapController.prototype.populateNumbers = function populateNumbers(){
			var map = this.ClientModel.getMap();
			var numbers = map.getNumbers();
			for (var number in numbers){
				var num = numbers[number];
				for (var loc in num){
					this.View.addNumber(num[loc], number);
				}
			}
		}

		MapController.prototype.populatePieces = function populatePieces(){
			var map = this.ClientModel.getMap();
			
			//placeRobber
			var robber = map.getRobber();
			if(robber){
				this.View.placeRobber({x:robber.getX(), y: robber.getY()});
			}else{
				console.warn("Trying to read an undefined Robber object");
			}

			//populate pieces on hexes
			var hexes = map.getHexGrid().getHexArray();
			for(var i = 0; i < hexes.length; i++){
				for(var j = 0; j < hexes[i].length; j++){
					this.populateEdges(hexes[i][j]);
					this.populateVertexes(hexes[i][j]);
				}
			}
		}

		MapController.prototype.populateEdges = function populateEdges(hex){
			var hexloc = hex.getLocation();
			var players = this.ClientModel.getPlayers();
			//edges 3-5 because MapView only accepts "SE", "SW" and "S". This is to to prevent overlap
			for (var i = 3; i <= 5; i++){
				var edge = hex.getEdgeNum(i);
				if(edge.isOccupied()){
					var edgeloc = new catan.map.View.EdgeLoc(hexloc.getX(), hexloc.getY(), catan.models.map.EdgeDirectionNum[i]);
					this.View.placeRoad(edgeloc, players[edge.getValue().getOwnerID()].getColor());
				}
			}
		}

		MapController.prototype.populateVertexes = function populateVertexes(hex){
			var hexloc = hex.getLocation();
			var players = this.ClientModel.getPlayers();
			//only do east and west as any vertex will be east or west of some hex. This prevents multiple placements.

			var popVer = function(self, dir){
				var vertex = hex.getVertex(dir);
				if(vertex.isOccupied()){
					var vertexloc = new catan.map.View.VertexLoc(hexloc.getX(), hexloc.getY(), dir);
					if(vertex.getValue().getBuildSite() == 1){
						self.View.placeSettlement(vertexloc, players[vertex.getValue().getOwnerID()].getColor());
					}
					else if(vertex.getValue().getBuildSite() == 2){
						self.View.placeCity(vertexloc, players[vertex.getValue().getOwnerID()].getColor());					
					}
				}
			}

			popVer(this, "E");
			popVer(this, "W");

		}

		MapController.prototype.doRobberPhase = function(){
			if(this.ClientModel.isMyTurn() && this.ClientModel.getTurn().isRobbingPhase() && !this.modalOn){
				this.modalView.showModal("robber");
				this.View.startDrop("robber");
				this.modalOn = true;				
			}
		}
        
        /**
		 This method is called by the Rob View when a player to rob is selected via a button click.
		 @param {Integer} orderID The index (0-3) of the player who is to be robbed
		 @method robPlayer
		*/
		MapController.prototype.robPlayer = function(orderID){
			if(this.soldier){
				this.game.playSoldier(orderID, this.robloc, function(){});
				this.robView.closeModal();
				this.modalView.closeModal();
				this.soldier = false;
			}
			else{
				this.game.robPlayer(orderID, this.robloc, function(){});
				this.robView.closeModal();
				this.modalView.closeModal();
				this.modalOn = false;
			}
		}
        
        /**
		 * Starts the robber movement on the map. The map should pop out and the player should be able
         * move the robber.  This is called when the user plays a "soldier" development card.
		 * @method doSoldierAction
		 * @return void
		**/		
		MapController.prototype.doSoldierAction = function(){    
			this.modalView.showModal("robber");
			this.View.startDrop("robber");
			this.soldier = true;
		}
        
		/**
		 * Pops the map out and prompts the player to place two roads.
         * This is called when the user plays a "road building" progress development card.
		 * @method startDoubleRoadBuilding
		 * @return void
		**/	
		MapController.prototype.startDoubleRoadBuilding = function(){
			this.roadbuild = true;
			this.startMove("road", true, false);
		}
		
        
        /**
		 * Pops the map out and prompts the player to place the appropriate piece
         * @param {String} pieceType - "road", "settlement", or "city
         * @param {boolean} free - Set to true in road building and the initial setup
         * @param {boolean} disconnected - Whether or not the piece can be disconnected. Set to true only in initial setup
		 * @method startMove
		 * @return void
		**/	
		MapController.prototype.startMove = function (pieceType,free,disconnected){
			//what to do with free and disconnected?
			this.modalView.showModal(pieceType);
			this.View.startDrop(pieceType, this.game.getCurrentPlayer().getColor());
			this.modalOn = false;
		}
        
		/**
		 * This method is called from the modal view when the cancel button is pressed. 
		 * It should allow the user to continue gameplay without having to place a piece. 
		 * @method cancelMove
		 * @return void
		 * */
		MapController.prototype.cancelMove = function(){
			this.modalView.closeModal();
			this.View.cancelDrop();
			this.modalOn = false;
			this.roadbuild = false;
			this.firstroad = false;
		}

		/**
		 This method is called whenever the user is trying to place a piece on the map. 
         It is called by the view for each "mouse move" event.  
         The returned value tells the view whether or not to allow the piece to be "dropped" at the current location.

		 @param {MapLocation} loc The location being considered for piece placement
		 @param {String} type The type of piece the player is trying to place ("robber","road","settlement","city")
		 @method onDrag
		 @return {boolean} Whether or not the given piece can be placed at the current location.
		*/
		MapController.prototype.onDrag = function (loc, type) {
			var map = this.ClientModel.getMap();
			var hexloc = new catan.models.map.HexLocation(loc.x, loc.y);
			switch(type.type){ //THIS IS WRONG ACCORDING TO THE GIVEN METHOD DOC, BUT THE VIEW IS GIVING WRONG INPUT....ARRGGHHH
				case "robber":
					return map.canPlaceRobber(hexloc);
					break;
				case "road":
					if(this.firstroad && this.roadbuild){
						var hexloc2 = new catan.models.map.HexLocation(this.firstroadloc.x, this.firstroadloc.y);
						return this.ClientModel.canPlayRoadBuilding(hexloc, loc.getDir(), hexloc2, this.firstroadloc.getDir());
					}
					return this.ClientModel.canPlaceRoad(hexloc, loc.getDir(), this.roadbuild);
					break;
				case "settlement":
					return this.ClientModel.canPlaceSettlement(hexloc, loc.getDir());
					break;
				case "city":
					return this.ClientModel.canPlaceCity(hexloc, loc.getDir());
					break;
			}
		}

		MapController.prototype.populateRobOverlay = function(hexloc){
			var hex = this.ClientModel.getMap().getHexGrid().getHex(hexloc);
			var playerInfo = [];
			for(var i = 0; i < 6; i++){
				var vertex = hex.getVertexNum(i);
				if(vertex.isOccupied()){
					var ownerID = vertex.getValue().getOwnerID();
					var player = this.ClientModel.getPlayerWithOrder(ownerID);
					if(ownerID != this.game.getCurrentPlayerOrder()){
						playerInfo[ownerID] = {};
						playerInfo[ownerID].color = player.getColor();
						playerInfo[ownerID].name = player.getName();
						playerInfo[ownerID].playerNum = ownerID;
						playerInfo[ownerID].cards = player.getResources().getTotalCount();
					}
				}
			}
			var densePI = playerInfo.filter(function(p){
				return p;
			});
			this.robView.setPlayerInfo(densePI);
		}

		/**
		 This method is called when the user clicks the mouse to place a piece.
         This method should close the modal and possibly trigger the Rob View.

		 @param {MapLocation} loc The location where the piece is being placed
		 @param {String} type The type of piece being placed ("robber","road","settlement","city")
		 @method onDrop
		*/
		MapController.prototype.onDrop = function (loc, type) {
			var map = this.ClientModel.getMap();
			var hexloc = new catan.models.map.HexLocation(loc.x, loc.y);

			switch(type.type){
				case "robber":
					if(map.canPlaceRobber(hexloc)){						
						this.robloc = hexloc;
						this.populateRobOverlay(hexloc);
						this.robView.showModal();
					}
					else{
						return;
					}
					break;
				case "road":
					if(this.roadbuild){
						if(this.firstroad){
							var hexloc2 = new catan.models.map.HexLocation(this.firstroadloc.x, this.firstroadloc.y);
							if(this.ClientModel.canPlayRoadBuilding(hexloc, loc.getDir(), hexloc2, this.firstroadloc.getDir())){
								this.game.playRoadBuilding(hexloc, loc.getDir(), hexloc2, this.firstroadloc.getDir());
								this.modalView.closeModal();
								this.roadbuild = false;
								this.firstroad = false;
							}
						}
						else{
							if(this.ClientModel.canPlaceRoad(hexloc, loc.getDir(), true)){
								this.firstroad = true;
								this.firstroadloc = loc;
								this.View.placeRoad(loc,this.game.getCurrentPlayer().getColor());
							}
						}
					}
					if(this.ClientModel.canPlaceRoad(hexloc, loc.getDir())){
						this.game.buildRoad(hexloc, loc.getDir(), function(){});
						this.modalView.closeModal();
						return;
					}
					else{
						return;
					}
					break;
				case "settlement":
					if(this.ClientModel.canPlaceSettlement(hexloc, loc.getDir())){
						this.game.buildSettlement(hexloc, loc.getDir(), function(){});
						this.modalView.closeModal();
						return;
					}
					else{
						return;
					}
					break;
				case "city":
					if(this.ClientModel.canPlaceCity(hexloc, loc.getDir())){
						this.game.buildCity(hexloc, loc.getDir(), function(){});
						this.modalView.closeModal();
						return;
					}
					else{
						return;
					}
					break;
			}
		}
        
		return MapController;
	} ());

	return MapController;

} ());

