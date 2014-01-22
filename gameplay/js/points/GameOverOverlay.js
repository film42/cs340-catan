/**
    This is the namespace for the intitial game round
    @module catan.misc
    @namespace misc
*/

var catan = catan || {};
catan.misc = catan.misc || {};

catan.misc.GameFinishedView = (function(){
	var BasicOverlay = catan.misc.BasicOverlay;
	var Images = catan.definitions.MiscImages;
	
	var StaticImage = catan.definitions.DisplayElement.BasicElements.StaticImage;
	var Label = catan.definitions.DisplayElement.BasicElements.Label;
	
	core.forceClassInherit(GameFinishedView,BasicOverlay);
	core.defineProperty(GameFinishedView.prototype,"WinImage");
	core.defineProperty(GameFinishedView.prototype,"LoseImage");
	core.defineProperty(GameFinishedView.prototype,"Message");
	
	/**
	 * A view to display the results of the game; inherits from misc.BaseOverlay
	 * @class GameFinishedView
	 * @constructor
	 * @extends misc.BaseOverlay
	 */
	function GameFinishedView(){
		BasicOverlay.call(this,"Game Finished","game-finished");
	}
	
	/**
	 * @method setWinner
	 * @param {String} name the name of the winner
	 * @param {Boolean} isYou whether the client player is the winner
	 */
	 GameFinishedView.prototype.setWinner = function(name, isYou){
		
		if(isYou){
			this.getLoseImage().setAttribute("class","overlay-image back");
			this.getWinImage().setAttribute("class","overlay-image front");
		}
		else{
			this.getLoseImage().setAttribute("class","overlay-image front");
			this.getWinImage().setAttribute("class","overlay-image back");
		}
			
		var header = this.getHeader();
		if(header != undefined)
			header.className = header.className + " " + name;
		
		var message = generateMessage(name,isYou);
		this.getMessage().textContent = message;
				

	}
	
	GameFinishedView.prototype.generateBody = function(){
		var label = document.createElement("label");
		this.setMessage(label);
		
		var topDiv = document.createElement("div");
			topDiv.appendChild(label);	
			
		var imgDiv = document.createElement("div");
			imgDiv.setAttribute("class", "overlay-image centered");

		var winImg = new StaticImage("winner","overlay-image back");
		this.setWinImage(winImg);
		
		var loseImg = new StaticImage("loser","overlay-image front");
		this.setLoseImage(loseImg);
		
			imgDiv.appendChild(winImg);
			imgDiv.appendChild(loseImg);
			topDiv.appendChild(imgDiv);

		return topDiv;	
	}
	
	GameFinishedView.prototype.generateFooter = function(){
		var button = document.createElement('button');

			button.setAttribute('class','button-area full short');
			button.setAttribute('data-dismiss','modal');
			button.textContent = ("OK");
			button.onclick = function(){
					window.location = "joinGame.html"
				}
		var topDiv = document.createElement('div');
			topDiv.appendChild(button);
		return topDiv;
	}
	
	function generateMessage(name, isYou){
		if (isYou){
			return "Congratulations! You won!"
		} else {
			return name + " won! Better luck next time.";
		}		
	}
	
	return GameFinishedView;
}())

