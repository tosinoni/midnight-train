package src.main.heuristics;

import java.util.Collections;
import java.util.List;

import src.main.State;

public class HeuristicByCounting extends Heuristic{

	/**
	* distance summation to goal. take the sum of goal and subtract from what is on right side
	 */
	@Override 
	public int evaluate(State initialState, State goalState) {
		estimate =  sum(initialState.getListOfTimeOnLeft());
		return estimate;
	}
	
	public int sum(List<Integer> list) {
	     int sum = 0; 

	     for (int i : list)
	         sum = sum + i;

	     return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heuristic heuristic = new HeuristicByCounting();
		State initial = new State("1 2 5 8", "");

		State goalState = new State("", "1 2 5 8", true);
		
		System.out.println(heuristic.evaluate(initial, goalState));
	}

}
