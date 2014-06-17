package fi.jleh.reittiopas.quadtree;

import java.util.Collections;
import java.util.List;

import fi.jleh.reittiopas.datastructures.DefaultList;
import fi.jleh.reittiopas.model.QuadtreePoint;

/**
 * Quadtree node class.
 *
 */
public class QuadTree {

	private static final int MAX_NODE_CAPACITY = 4;
	
	private BoundingBox boundingBox;
	
	private QuadTree northWest;
	private QuadTree northEast;
	private QuadTree southWest;
	private QuadTree southEast;
	
	private List<QuadtreePoint> points;
	
	/**
	 * Creates a new quadtree node with given bounding box.
	 * @param boundingBox
	 */
	public QuadTree(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
		this.points = new DefaultList<QuadtreePoint>();
	}

	/**
	 * Inserts point to the tree.
	 * Returns true if point is inserted.
	 * @param point
	 * @return
	 */
	public boolean insert(QuadtreePoint point) {
		// Check if point can be inserted
		if (!boundingBox.containsPoint(point)) {
			return false;
		}
		
		// Add point if node is not full
		if (points.size() < MAX_NODE_CAPACITY) {
			points.add(point);
			
			return true;
		}
		
		// Node is full so we have to divide it (if not yet done)
		if (northWest == null) {
			subdivide();
		}
		
		// Check where point can be added
		if (northWest.insert(point)) return true;
		if (northEast.insert(point)) return true;
		if (southWest.insert(point)) return true;
		if (southEast.insert(point)) return true;
		
		return false;
	}
	
	/*
	 * Subdivide node into a four child nodes.
	 */
	private void subdivide() {
		northWest = new QuadTree(new BoundingBox(boundingBox.getMinX(), boundingBox.getCenterX(),
				boundingBox.getMaxY(), boundingBox.getCenterY()));
		northEast = new QuadTree(new BoundingBox(boundingBox.getCenterX(), boundingBox.getMaxX(),
				boundingBox.getMaxY(), boundingBox.getCenterY()));
		southWest = new QuadTree(new BoundingBox(boundingBox.getMinX(), boundingBox.getCenterX(),
				boundingBox.getCenterY(), boundingBox.getMinY()));
		southEast = new QuadTree(new BoundingBox(boundingBox.getCenterX(), boundingBox.getMaxX(),
				boundingBox.getCenterY(), boundingBox.getMinY()));
	}
	
	public List<QuadtreePoint> queryRange(BoundingBox queryBox) {
		// This node is not in range. Return empty list
		if (!queryBox.intersects(this.boundingBox)) {
			return Collections.emptyList();
		}
		
		// Collect points that are in range
		List<QuadtreePoint> list = getPoints(queryBox);
		
		// There is no more children
		if (northWest == null)
			return list;
		
		list.addAll(northWest.queryRange(queryBox));
		list.addAll(northEast.queryRange(queryBox));
		list.addAll(southWest.queryRange(queryBox));
		list.addAll(southEast.queryRange(queryBox));
		
		return list;
	}
	
	private List<QuadtreePoint> getPoints(BoundingBox queryBox) {
		List<QuadtreePoint> list = new DefaultList<QuadtreePoint>();
		
		for (QuadtreePoint point : this.points) {
			if (queryBox.containsPoint(point)) {
				list.add(point);
			}
		}
		
		return list;
	}
	
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	@Override
	public String toString() {
		return "QuadTree [boundingBox=" + boundingBox + "]";
	}
}
