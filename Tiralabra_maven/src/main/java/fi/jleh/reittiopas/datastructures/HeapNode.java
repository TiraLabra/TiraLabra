package fi.jleh.reittiopas.datastructures;

public class HeapNode<T> {

	private double score;
	private T object;

	public HeapNode(double score, T object) {
		this.score = score;
		this.object = object;
	}
	
	public double getScore() {
		return score;
	}

	public T getObject() {
		return object;
	}

	public int compareTo(HeapNode<T> other) {
		if (other.getScore() < this.score)
			return 1;
		else if (other.getScore() > this.score)
			return -1;
		else
			return 0;
	}
}
