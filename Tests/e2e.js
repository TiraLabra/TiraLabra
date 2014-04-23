describe('Pathfinder', function() {
  
  
    browser.ignoreSynchronization = true;
    var prot = protractor.getInstance();
    var driver = prot.driver;    

  
	it('should not execute search if Start and End Nodes are not selected', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		
  		element(by.id("search")).click();
  		var error = element(by.id("error"));
  		expect(error.getText()).toBe("Choose Start and End Nodes");
  		prot.sleep(2000);
  	});
  	xit('should not execute search if End Node is not selected', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		element(by.xpath("//div[@id='stage']//div[.='Node B']")).click();
  		element(by.id("search")).click();
  		var error = element(by.id("error"));
  		expect(error.getText()).toBe("Choose End Node");
  		prot.sleep(2000);
  	});
	it('should not run new search without initialization', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		element(by.xpath("//div[@id='stage']//div[.='Node B']")).click();
    	element(by.xpath("//div[@id='stage']//div[.='Node G']")).click();
    	element(by.id("search")).click();
    	prot.sleep(2000);
    	var search = prot.findElement(By.id("search")).isEnabled();
    	expect(search).toBe(false);
  	});
  	
  	it('should not ramdomize layout without initialization after a search', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		element(by.xpath("//div[@id='stage']//div[.='Node B']")).click();
    	element(by.xpath("//div[@id='stage']//div[.='Node G']")).click();
    	element(by.id("search")).click();
    	prot.sleep(2000);
    	var randomize = prot.findElement(By.id("randomize")).isEnabled();
    	expect(randomize).toBe(false);
  	});
  	
  	it('destination should not be reachable', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		element(by.xpath("//div[@id='stage']//div[.='Node B']")).click();
    	element(by.xpath("//div[@id='stage']//div[.='Node L']")).click();
    	element(by.id("search")).click();
    	prot.sleep(2000);
    	var error = prot.findElement(By.id("error"));
    	expect(error.getText()).toBe('Unreachable');
  	});
  	
  	it('function randomize should change nodes location', function() {
  		
  		browser.get("http://localhost/~el/Tiralabra/");
  		
    		var location = prot.findElement(By.xpath("//div[@id='stage']//div[.='Node A']")).getCssValue("top");
    		console.log(location);
    		expect(location).toBe('200px');
    		element(by.id("randomize")).click();
    		prot.sleep(4000);
    		var newLocation = prot.findElement(By.xpath("//div[@id='stage']//div[.='Node A']")).getCssValue("top");
    		expect(newLocation).not.toBe('200px');
  	});
  	
  	it('should execute test case without errors', function() {
        
    	browser.get("http://localhost/~el/Tiralabra/");    
	    element(by.xpath("//div[@id='stage']//div[.='Node B']")).click();
    	element(by.xpath("//div[@id='stage']//div[.='Node G']")).click();    	
	    element(by.id("search")).click();
    	prot.sleep(3000);    	
    	var result = element(by.xpath("/html/body/div/div[7]/span"));
    		expect(result.getText()).toBe('71');
    	var list = element.all(by.css('#result li'));	
    		expect(list.count()).toBe(3);
   	});
   	
   	
});
