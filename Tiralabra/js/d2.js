(function($, window) {

  var stage,
      Node = window.Node,
      Segment = window.Segment,
      node,
      nodes,
      nodeArray,
      openNodes,
	  closedNodes,
	  segmentCount,
	  minDistanceToEnd,
	  path;

  var NODE_DIMENSIONS = {
    w: 30,
    h: 30
  };

  function initialize() {
    stage = $('#stage');
	var click = 0;
	
	// get Nodes from .json file
	$.getJSON('nodes.json', function(data) {
		node = new Array;
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
				    		$(".start").removeClass("bold");
				    		$(".end").addClass("bold");
				    		
				    	}
				    	else {
    						//alert("Start can't be the same as End");
    					}
			    	}
				    else {
				    	if (selectedStart != this.id) {
				    		$("#end").text(this.id);
				    		click = 0;
				    		$(".end").removeClass("bold");
				    		$(".start").addClass("bold");
				    	}
				    	else {
				    		//alert("End can't be the same as Start");
				    	}    	
    				}
    	    	}
      		}
    		}).attach();  		
   		}); 
   		
   		nodeArray = new Array;
   		// Segments can only be drawn after each Node has been created  		
   		segmentCount = 0;
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
	
		openNodes = new Array;
		closedNodes = new Array;
		minDistanceToEnd = 0;
	});    
  }  	
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
    	   			path = new Array;
    	   			reconstructPath(closedNodes[0], end);
    	   		}
	    	}
	    	else {
	    		if (minDistanceToEnd == 0) {
		    		window.console.log("Unreachable");
		    		alert("Unreachable");
		    	}
		    	else {
		    		path = new Array;
		    		reconstructPath(closedNodes[0], end);
		    	}	    		
	    	}	    	   	    		
  		}
  		
  		// reconstruct path  		
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
  	function randomize() {
  		for (x in node) {
    		nodex = node[''+x+''];
    	    nodex.el.on('webkitTransitionEnd transitionend', (function(n) {
	            return function() {
                	$(this).removeClass('animate');
                	$(this).off('webkitTransitionEnd transitionend');
                	_(n.segments).invoke('calculateRotation');
            	};
        	}(nodex)));
        	nodex.segments.map(function(n) {
            	n.el.addClass('animate');
            	n.el.on('webkitTransitionEnd transitionend', function() {
                	if ($(this).width() === 0) {
                    	$(this).addClass('hide');
                	} else {
                    	$(this).off('webkitTransitionEnd transitionend');
                    	$(this).removeClass('animate');
                	}
            	});
            	setTimeout(function() {
                	n.el.css({
                    	width: 0
                	});
            	}, 15);
        	});
        	var oldValue, newValue, randomValue,
        	random = Math.random();
        	
        	if (random <= 0.5) {
        		oldValue = nodex.translate.x;
        		randomValue = Math.round(Math.random()*50);
        		if ((oldValue - randomValue) < 0) {
        			nodex.translate.x = 0;
        		}
        		else {
        			nodex.translate.x = oldValue - randomValue;
        		}        		
        	}
        	else { 
        		oldValue = nodex.translate.x;
        		randomValue = Math.round(Math.random()*50);
        		if ((oldValue + randomValue) > 900) {
        			nodex.translate.x = 900;
        		}
        		else {
        			nodex.translate.x = oldValue + randomValue;
        		}         		
        	}
        	random = Math.random();
        	if (random <= 0.5) {
        		oldValue = nodex.translate.y;
        		randomValue = Math.round(Math.random()*50);
        		if ((oldValue - randomValue) < 0) {
        			nodex.translate.y = 0;
        		}
        		else {
        			nodex.translate.y = oldValue - randomValue;
        		}         		
        	}
        	else { 
        		oldValue = nodex.translate.y;
        		randomValue = Math.round(Math.random()*50);
        		if ((oldValue + randomValue) > 700) {
        			nodex.translate.y = 700;
        		}
        		else {
        			nodex.translate.y = oldValue + randomValue;
        		}        		
        	}
        	nodex.el.addClass('animate');
        	setTimeout((function(n) {
            	return function() {
                	n.setPosition();
                	n.onDragEnd();
            	};
        	}(nodex)), 400);	
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
  		$("#initialize").click( function () {
  			$("#stage, #result").html("");
  			$("#start").text("");
  		 	$("#end").text("");
  			initialize(); 	
  		});
  		$("#randomize").click( function () {
  			randomize(); 	
  		});
  	
   
  initialize();
  
}(jQuery, window));