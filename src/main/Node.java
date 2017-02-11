package src.main;

public class Node {

	private int index;

	private int estimateCost;

	private int exactCost;
	private State state;
	private String move;
	private Node parent;

	private String action;

	public Node(State state) {
		this.state = state;
		this.action = "";
		this.move = "";
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(int estimateCost) {
		this.estimateCost = estimateCost;
	}

	public int getExactCost() {
		return exactCost;
	}

	public void setExactCost(int exactCost) {
		this.exactCost = exactCost;
	}

	public int getTotalCost() {
		return exactCost + estimateCost;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		else if (obj == null || obj.getClass() != this.getClass())
			return false;

		Node node = (Node) obj;

		return (this.state.equals(node.state)) && (this.action == node.action) && (this.parent.equals(node.parent))
				&& (this.index == node.index);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
