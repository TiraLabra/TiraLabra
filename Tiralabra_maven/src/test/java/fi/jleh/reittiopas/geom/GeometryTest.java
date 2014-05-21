package fi.jleh.reittiopas.geom;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.jleh.reittiopas.model.QuadtreePoint;
import fi.jleh.reittiopas.quadtree.BoundingBox;
import fi.jleh.reittiopas.quadtree.QuadTree;

public class GeometryTest {

	@Test
	public void boundingBoxContainsPointTest() {
		BoundingBox boundingBox = new BoundingBox(25, 60, 1000);
		
		System.out.println(boundingBox);
		
		QuadtreePoint point1 = new SimplePoint(24.95, 59.999);
		QuadtreePoint point2 = new SimplePoint(27.95, 50.999);
		
		assertTrue(boundingBox.containsPoint(point1));
		assertFalse(boundingBox.containsPoint(point2));
	}
	
	@Test
	public void boundingBoxChecksIntersection() {
		BoundingBox boundingBox1 = new BoundingBox(25, 60, 10000);
		BoundingBox boundingBox2 = new BoundingBox(25.1, 60.1, 10000);
		BoundingBox boundingBox3 = new BoundingBox(29, 67.5, 100);
		
		System.out.println(boundingBox1);
		System.out.println(boundingBox2);
		
		assertTrue(boundingBox1.intersects(boundingBox2));
		assertFalse(boundingBox1.intersects(boundingBox3));
	}
	
	@Test
	public void boundigBoxBoundsConstructorTest() {
		BoundingBox box = new BoundingBox(24.0077, 25.9922, 60.8993, 59.1006);
		QuadtreePoint point1 = new SimplePoint(24.95, 59.999);

		assertTrue(box.containsPoint(point1));
	}
	
	@Test
	public void testAddingPointsToQuadtree() {
		// Create box and add some points.
		// All points should be successfully added to data structure. 
		BoundingBox startArea = new BoundingBox(25, 60, 100000);
		QuadTree quadTree = new QuadTree(startArea);
		
		assertTrue(quadTree.insert(new SimplePoint(24.95, 59.99)));
		assertTrue(quadTree.insert(new SimplePoint(24.97, 59.95)));
		assertTrue(quadTree.insert(new SimplePoint(24.95, 59.93)));
		assertTrue(quadTree.insert(new SimplePoint(24.92, 59.92)));
		assertTrue(quadTree.insert(new SimplePoint(24.93, 59.98)));
		assertTrue(quadTree.insert(new SimplePoint(24.94, 60.0001)));
		assertTrue(quadTree.insert(new SimplePoint(24.95, 60.002)));
		assertTrue(quadTree.insert(new SimplePoint(24.96, 60.05)));
		assertTrue(quadTree.insert(new SimplePoint(24.97, 60.06)));
		assertTrue(quadTree.insert(new SimplePoint(24.98, 60.07)));
		assertTrue(quadTree.insert(new SimplePoint(24.99, 60.03)));
		assertTrue(quadTree.insert(new SimplePoint(25.08, 60.00)));
		assertTrue(quadTree.insert(new SimplePoint(25.07, 60.01)));
		assertTrue(quadTree.insert(new SimplePoint(25.06, 60.02)));
		assertTrue(quadTree.insert(new SimplePoint(25.04, 60.01)));
		assertTrue(quadTree.insert(new SimplePoint(25.03, 60.02)));
		assertTrue(quadTree.insert(new SimplePoint(25.02, 60.0)));
		assertTrue(quadTree.insert(new SimplePoint(25.01, 59.99)));
	}
	
	@Test
	public void testQuadTreeSearch() {
		// Create data structure and add some data
		BoundingBox startArea = new BoundingBox(25, 60, 100000);
		QuadTree quadTree = new QuadTree(startArea);
		
		// Create points that should be included in result
		QuadtreePoint point1 = new SimplePoint(25.08, 60.00);
		QuadtreePoint point2 = new SimplePoint(25.07, 60.01);
		QuadtreePoint point3 = new SimplePoint(25.04, 60.01);
		QuadtreePoint point4 = new SimplePoint(25.02, 60.0);
		
		quadTree.insert(new SimplePoint(24.95, 59.99));
		quadTree.insert(new SimplePoint(24.97, 59.95));
		quadTree.insert(new SimplePoint(24.95, 59.93));
		quadTree.insert(new SimplePoint(24.92, 59.92));
		quadTree.insert(point1);
		quadTree.insert(new SimplePoint(24.93, 59.98));
		quadTree.insert(new SimplePoint(24.94, 60.0001));
		quadTree.insert(new SimplePoint(24.95, 60.002));
		quadTree.insert(new SimplePoint(24.96, 60.05));
		quadTree.insert(new SimplePoint(24.97, 60.06));
		quadTree.insert(new SimplePoint(24.98, 60.07));
		quadTree.insert(new SimplePoint(24.99, 60.03));
		quadTree.insert(point2);
		quadTree.insert(new SimplePoint(25.06, 60.02));
		quadTree.insert(point3);
		quadTree.insert(new SimplePoint(25.03, 60.02));
		quadTree.insert(point4);
		quadTree.insert(new SimplePoint(25.01, 59.99));
		
		// Create query box
		BoundingBox queryBox = new BoundingBox(25.015, 25.9, 60.015, 59.9);
		List<QuadtreePoint> result = quadTree.queryRange(queryBox);
		
		assertEquals(4, result.size());
		
		assertTrue(result.contains(point1));
		assertTrue(result.contains(point2));
		assertTrue(result.contains(point3));
		assertTrue(result.contains(point4));
	}
}
