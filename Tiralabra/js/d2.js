(function($, window) {

  var stage,
      Node = window.Node,
      Segment = window.Segment;

  var NODE_DIMENSIONS = {
    w: 50,
    h: 50
  };

  function initialize() {
    stage = $('#stage');
	
	$.getJSON('nodes.json', function(data) {
		var node = new Array;
		$.each(data.nodes, function(key, value) {
   			
   			
   			node[key] = new Node({
      		title: value.title,
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
   		
   		$.each(data.nodes, function(key, value) {
   			 			
   			$.each(value.neighbours, function(subkey, subvalue) {

	    		new Segment({
      				h: 5,
		      		stage: stage,
      				origin: node[key],
		      		destination: node[subkey],
		      		time: node[subkey]
		    	}).attach();
		    	
    		});    		
    		
   		})
   		 		
	});    
  }
   
  initialize();

}(jQuery, window));