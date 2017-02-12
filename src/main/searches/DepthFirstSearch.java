package src.main.searches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import src.main.Node;
import src.main.ProductionSystem;
import src.main.State;
import src.main.Strategy;

public class DepthFirstSearch implements Strategy{
    private Stack<Node> nodeList = new Stack<>();
	private Map<String, Node> visitedStates = new LinkedHashMap<>();
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

			if (visitedStates.get(node.getState().toString()) == null) {
				visitedStates.put(node.getState().toString(), node);
				if (node.getState().equals(goalState))
					return node;
				
				for (Node n : prodSystem.expand(node, visitedStates)) nodeList.push(n);
				
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
