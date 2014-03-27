(function($, window) {

  var stage,
      Node = window.Node,
      Segment = window.Segment;

  var NODE_DIMENSIONS = {
    w: 30,
    h: 30
  };

  function initialize() {
    stage = $('#stage');
	
	$.getJSON('nodes.json', function(data) {
		var node = new Array;
		$.each(data.nodes, function(key, value) {
   			
   			
   			node[key] = new Node({
      		title: value.title,
      		id: key,
	 		stage: stage,
	        w: NODE_DIMENSIONS.w,
  	        h: NODE_DIMENSIONS.h,
      		x: value.location.x,
      		y: value.location.y,
      		events: {
        		click: function() {
	          		window.console.log(this);
    	    	}
      		}
    		}).attach();    		
    		
			
   		}); 
   		
   		var nodeArray = new Array;
   		// Segments can only be drawn after each Node has been created  		
   		
   		$.each(data.nodes, function(key, value) {
   			nodeArray[key] = key; 			
   			$.each(value.neighbours, function(subkey, subvalue) {

	    		new Segment({
      				h: 5,
		      		stage: stage,
      				origin: node[key],
		      		destination: node[subkey],
		      		time: subvalue
		    	}).attach();
		    	
    		});    		
    		
   		});
   				
	
		var openNodes = new Array;
		var closedNodes = new Array;
		var i = 3;
		function nodeSearch(currentNode) {
		
			closedNodes.push(currentNode);
			openNodes.splice(openNodes.indexOf(currentNode), 1);
			var current = node[''+currentNode+''];
				current.visited();
				// total so far
				var currentTotal = current.total;
			var a = node[''+currentNode+''].segments;
			$.each(a, function(key, value) {
    			var n = value.destination.id;
    			 
    			
				if (n != currentNode) {
					var alreadyClosed = false;
					var alreadyInArray = false;
					for (i = 0; i < closedNodes.length; i++) {
						if (closedNodes[i] == n) {
							alreadyClosed = true;							
						}
					}
					for (i = 0; i < openNodes.length; i++) {
						if (openNodes[i] == n) {							
							alreadyInArray = true;							
						}
					}
					if (alreadyClosed == false) {
						// destination Node
						var destinationNode = node[''+n+''];
    					// destination total
    					var destinationTotal = destinationNode.total;
    					
						var originTotal = value.time + currentTotal;
						if (destinationTotal == 0 || destinationTotal >= originTotal) {
							node[''+n+''].total = originTotal;
							destinationNode.el.find(".total").text(originTotal);
						}
						
						
						//alert(value.time);
	    				if (alreadyInArray == false) {
		    				openNodes.push(n);	    				
		    			}
	    			}
	    				    			
	    	    }
    		});
    		window.console.log("open: "+openNodes);
   			window.console.log("closed: "+closedNodes); 
    		
    		while (openNodes.length > 0) {
    			nodeSearch(openNodes[0]);
	    	}   	    		
  		}
  		
  		$("#start").click( function () {
  			nodeSearch('nodeA');
  		});
  	});    
  }
  
   
  initialize();
  
  	
	
	
}(jQuery, window));