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
	var click = 0;
	
	// get Nodes from .json file
	$.getJSON('nodes.json', function(data) {
		var node = new Array;
		$.each(data.nodes, function(key, value) {
   			
   			// create each Node based on JSON
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
	          		
	          		// selecting start and end Nodes
	          		var selectedStart = $("#start").text();
    				var selectedEnd = $("#end").text();
    				if (click == 0) {
				    	if (selectedEnd != this.id) {
	    					$("#start").text(this.id);
				    		click = 1;
				    	}
				    	else {
    						alert("Start can't be the same as End");
    					}
			    	}
				    else {
				    	if (selectedStart != this.id) {
				    		$("#end").text(this.id);
				    		click = 0;
				    	}
				    	else {
				    		alert("End can't be the same as Start");
				    	}    	
    				}
    	    	}
      		}
    		}).attach();  		
   		}); 
   		
   		var nodeArray = new Array;
   		// Segments can only be drawn after each Node has been created  		
   		var segmentCount = 0;
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
		    	segmentCount++;
    		});    		
    		
   		});   				
	
		var openNodes = new Array;
		var closedNodes = new Array;
		var minDistanceToEnd = 0;
		
		// version of A* search
		function nodeSearch(currentNode, end) {
		
			closedNodes.push(currentNode);			
			openNodes.splice(openNodes.indexOf(currentNode), 1);
			var current = node[''+currentNode+''];
			current.visited();
			
			// total so far
			var currentTotal = current.total;
			
			var a = node[''+currentNode+''].segments;
			
			// Segments between Nodes
			$.each(a, function(key, value) {
    			var n = value.destination.id;   			 
    			
    			// Nodes might have two way relations (NodeA -> NodeB / NodeA <- NodeB)    			
				if (n != currentNode) {
					var alreadyClosed = false;
					var alreadyInArray = false;
					
					// check if found Node is already in Closed array
					for (i = 0; i < closedNodes.length; i++) {
						if (closedNodes[i] == n) {
							alreadyClosed = true;
							continue;							
						}
					}
					
					// check if found Node is already in Open array
					for (i = 0; i < openNodes.length; i++) {
						if (openNodes[i] == n) {							
							alreadyInArray = true;
							continue;							
						}
					}
					if (alreadyClosed == false) {
						// destination Node
						var destinationNode = node[''+n+''];
						
    					// destination total
    					var destinationTotal = destinationNode.total;
    					
    					// check if this path is faster that possible previous one
						var originTotal = value.time + currentTotal;
						if (destinationTotal == 0 || destinationTotal >= originTotal) {
							node[''+n+''].total = originTotal;
							node[''+n+''].cameFrom = currentNode;
							destinationNode.el.find(".total").text(originTotal);
						}
						// if destination is end
						if (n == end) {
    						if (minDistanceToEnd == 0 || minDistanceToEnd > originTotal) {
    							minDistanceToEnd = originTotal;
    						}    						
    					}
						
	    				if (alreadyInArray == false && n != end) {
		    				openNodes.push(n);	    				
		    			}
	    			}
	    				    			
	    	    }
    		});
    		window.console.log("open: "+openNodes);
   			window.console.log("closed: "+closedNodes); 
    		
    		if (openNodes.length > 0) {
    			// heuristic cost estimate
    			var minDistance = 0;
    			var nextToProcess = '';
    			for (o = 0; o < openNodes.length; o++) {
    				var nodeTotal = node[''+openNodes[o]+''].total;

    				if (minDistance == 0 || nodeTotal < minDistance) {
    					minDistance = nodeTotal;
    					nextToProcess = openNodes[o];
    				}
    				
    			}
    			// process the next Node
    			if (minDistance < minDistanceToEnd || minDistanceToEnd == 0) {
    	   			nodeSearch(nextToProcess, end);   			
    	   		}
    	   		else {
    	   			window.console.log("done");
    	   			reconstructPath(closedNodes[0], end);
    	   		}
	    	}
	    	else {
	    		if (minDistanceToEnd == 0) {
		    		window.console.log("Unreachable");
		    		alert("Unreachable");
		    	}
		    	else {
		    		reconstructPath(closedNodes[0], end);
		    	}	    		
	    	}	    	   	    		
  		}
  		
  		// reconstruct path
  		var path = new Array;
  		function reconstructPath(start, end) {
			
			$(".segment, .node").css("background", "#DDD");
			// push end node
			if (path.length == 0) {
				path.push(end);
			}
  			var previous = node[''+end+''].cameFrom;
  			path.push(previous);
  			if (previous != start) {
  				reconstructPath(start, previous)
  			}
  			else {
  				window.console.log(path);
  				for (i = path.length-1; i >= 0; i--) {
  					var nodeOnPath = node[''+path[i]+''];
  					nodeOnPath.onPath();
  				}
  				for (n = 0; n < segmentCount; n++) {
  					var c = $(".endpoint")[n];
	  				var ctx=c.getContext("2d");
					ctx.fillStyle="#DDD";
					ctx.fill();
  				}
  				for (p = path.length-1; p > 0; p--) {
	  				var thisNode = node[''+path[p]+''];
	  				var nextNode = node[''+path[p-1]+''].id;
  					$.each(thisNode.segments, function(i) {
  					
						if (thisNode.segments[i].destination.id == nextNode) {													
							this.el.css("background", "green");							
							var c = this.el.find('canvas.endpoint')[0];
							var ctx=c.getContext("2d");
							ctx.fillStyle="green";
							ctx.fill();							
						}  					
  					});
  					$("#result").append("<li>"+path[p]+"</li>");
  				}
  				$("#result").append("<li>"+path[0]+"</li>"); 			
  			}  			
  		}  		  		
  		
  		$("#search").click( function () {
  		 	var start = $("#start").text();
  		 	var end = $("#end").text();
  		 	if (start != '' && end != '') {
	  			nodeSearch(""+start+"", ""+end+"");
	  		}
	  		else {
	  			if (start == '') {
	  				alert("Choose Start and End Nodes");
	  			}
	  			else {
	  				alert("Choose End Node");
	  			}
	  		}
  		});
  	});    
  }  
   
  initialize();
  
}(jQuery, window));