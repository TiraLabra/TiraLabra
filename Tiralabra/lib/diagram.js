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

    var nodeA = new Node({
      title: 'Node A',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 150,
      y: 200,
      events: {
        click: function() {
          window.console.log(this);
        }
      }
    }).attach();

    var nodeB = new Node({
      title: 'Node B',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 400,
      y: 50
    }).attach();

    var nodeC = new Node({
      title: 'Node C',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 100,
      y: 500
    }).attach();

    var nodeD = new Node({
      title: 'Node D',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 350,
      y: 550
    }).attach();
    
    var nodeE = new Node({
      title: 'Node E',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 330,
      y: 370,
      events: {
        click: function() {
          window.console.log(this);
        }
      }
    }).attach();

    var nodeF = new Node({
      title: 'Node F',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 800,
      y: 50
    }).attach();

    var nodeG = new Node({
      title: 'Node G',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 500,
      y: 400
    }).attach();

    var nodeH = new Node({
      title: 'Node H',
      stage: stage,
      w: NODE_DIMENSIONS.w,
      h: NODE_DIMENSIONS.h,
      x: 650,
      y: 550
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeA,
      destination: nodeB
    }).attach();
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeB,
      destination: nodeA
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeA
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeA,
      destination: nodeC
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeA
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeB,
      destination: nodeC
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeB
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeD
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeD,
      destination: nodeC
    }).attach();
    
    
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeB,
      destination: nodeG
    }).attach();
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeG,
      destination: nodeB
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeE,
      destination: nodeG
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeG,
      destination: nodeE
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeG
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeG,
      destination: nodeC
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeC,
      destination: nodeB
    }).attach();

    new Segment({
      h: 5,
      stage: stage,
      origin: nodeG,
      destination: nodeD
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeD,
      destination: nodeG
    }).attach();
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeA,
      destination: nodeE
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeE,
      destination: nodeA
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeE,
      destination: nodeB
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeB,
      destination: nodeE
    }).attach();
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeD,
      destination: nodeE
    }).attach();
    
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeE,
      destination: nodeD
    }).attach();
/*
    new Segment({
      h: 5,
      stage: stage,
      origin: nodeD,
      destination: nodeC
    }).attach();
*/
var nodes = [nodeA, nodeB, nodeC, nodeD];

function randomizeLayout() {
    var node;
    var pos = [[300, 150], [350, 250], [350, 350], [150, 150]].sort(function() { return 0.5 - Math.random(); });
    for (var i = 0; i < nodes.length; i++) {
        node = nodes[i];
        node.el.on('webkitTransitionEnd transitionend', (function(n) {
            return function() {
                $(this).removeClass('animate');
                $(this).off('webkitTransitionEnd transitionend');
                _(n.segments).invoke('calculateRotation');
            };
        }(node)));
        node.segments.map(function(n) {
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
        node.translate.x = pos[i][0]+((Math.round(Math.random()*30))-30);
        node.translate.y = pos[i][1]+((Math.round(Math.random()*20))-20);
        node.el.addClass('animate');
        setTimeout((function(n) {
            return function() {
                n.setPosition();
            };
        }(node)), 400);
    }
}

$('#move').on('click', randomizeLayout);  
  
  
  }

  initialize();

}(jQuery, window));