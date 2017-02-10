package src.main.heuristics;

import java.util.List;

import src.main.State;

public class HeuristicByAverage extends Heuristic {

	private HeuristicByCounting heuristicByCounting;
	private HeuristicByDistance HeuristicByDistance;

	public HeuristicByAverage() {
		heuristicByCounting = new HeuristicByCounting();
		HeuristicByDistance = new HeuristicByDistance();
	}

	@Override
	public int evaluate(State initialState, State goalState) {
		estimate = (heuristicByCounting.evaluate(initialState, goalState)
				+ HeuristicByDistance.evaluate(initialState, goalState)) / 2;

		return estimate;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}
}
