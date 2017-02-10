package src.main.heuristics;

import src.main.State;

public class Heuristic {

	protected int estimate;
	
	public int evaluate (State initialState, State goalState) {
		return estimate;
	}
	public int getEstimate() {
		return estimate;
	}
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
