package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point id;
	private Node prev;
	private int costFromStart; // cost from start
	private int costToTarget; // cost to target
	private int combinedCost; // combined cost of both
	private ArrayList<Node> neighbors = new ArrayList<>();
	

	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}

	public Node(Point id, Node prev, int costFromStart, int costToTarget) {
		this.id = id;
		this.prev = prev;
		this.costFromStart = costFromStart;
		this.costToTarget = costToTarget;
		combinedCost = costFromStart + costToTarget;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public int getCostFromStart() {
		return costFromStart;
	}

	public void setCostFromStart(int costFromStart) {
		this.costFromStart = costFromStart;
	}

	public Point getId() {
		return id;
	}

	public int getCostToTarget() {
		return costToTarget;
	}

	public int getCombinedCost() {
		return combinedCost;
	}

	public String toString() {
		return "Node [id=" + id + ", prev=" + prev + ", costFromStart=" + costFromStart + ", costToTarget="
				+ costToTarget + ", combinedCost=" + combinedCost + ", neighbors=" + neighbors + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean apu = false;
		Node other = (Node) obj;
		if (this.id.getX() == other.getId().getX() && this.id.getY() == other.getId().getY()) {
			apu = true;
		}
		return apu;
	}

}
