/**
    This is the namespace to hold the base classes
    @module catan.misc
    @namespace misc
*/

var catan = catan || {};
catan.misc = catan.misc || {};

catan.misc.WaitOverlay = (function (){
        
        var BasicOverlay = catan.misc.BasicOverlay;
        var Images = catan.definitions.MiscImages;
        
        /**
         * This class implements a placeholder overlay to enforce a wait period.
         * It inherits from misc.BaseOverlay.
         * It does not call any controller methods.
		 * @class WaitOverlay
		 * @extends misc.BaseOverlay
		 * @constructor
		 */
        var WaitOverlay = (function(){
        			
			core.forceClassInherit(WaitOverlay,BasicOverlay);
            
			function WaitOverlay(title){
				var title = title || "Waiting for Trade to Go Through"
				BasicOverlay.call(this,title);
			};
            
			WaitOverlay.prototype.generateBody = function(){
				  
				var divContainer = document.createElement("div");
				divContainer.setAttribute("class","text-center");
				
				var img = document.createElement("img");
				img.setAttribute("src",Images.prefix + Images.waitImage);
				img.setAttribute("class","overlay-image");
				
				divContainer.appendChild(img);
	 
				return divContainer;
			};
		   
			return WaitOverlay;
		}());
        
        return WaitOverlay;
}());

