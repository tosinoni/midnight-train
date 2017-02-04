package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch implements Strategy{
    private Stack<Node> nodeList = new Stack<>();
	private List<State> visitedStates = new ArrayList<>();
	private ProductionSystem prodSystem;

	
	@Override
	public Node search(List<Integer> times) {
		// TODO Auto-generated method stub
		if (times != null) {

			prodSystem = new ProductionSystem(times);
			String leftTime = "";
			for (int time : times)
				leftTime += time + " ";

			State state = new State(leftTime, "");
			Node node = new Node(state);
			nodeList.push(node);

			State goalState = new State("", leftTime.trim(), true);
			return treeSearch(goalState);
		}
		return null;
	}
	
	private Node treeSearch(State goalState) {
		// TODO Auto-generated method stub
		while (!nodeList.isEmpty()) {
			Node node = nodeList.pop();

			if (!visitedStates.contains(node.getState())) {
				visitedStates.add(node.getState());
				if (node.getState().equals(goalState))
					return node;
				
				for (Node n : prodSystem.expand(node)) nodeList.push(n);
				
			}

		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> times = new ArrayList<>(Arrays.asList(1, 2, 5, 8));

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

		Node node = depthFirstSearch.search(times);

		System.out.println(node.getAction());
	}


}
