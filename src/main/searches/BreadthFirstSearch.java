package src.main.searches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import src.main.Node;
import src.main.ProductionSystem;
import src.main.State;
import src.main.Strategy;

public class BreadthFirstSearch implements Strategy {

	private ProductionSystem prodSystem;
	private Queue<Node> nodeList = new ConcurrentLinkedQueue<>();
	private Set<State> visitedStates = new LinkedHashSet<>();

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
			nodeList.add(node);

			State goalState = new State("", leftTime.trim(), true);
			return treeSearch(goalState);

		}

		return null;
	}

	public Node treeSearch(State goalState) {

		while (!nodeList.isEmpty()) {
			Node node = nodeList.poll();

			if (!visitedStates.contains(node.getState())) {
				visitedStates.add(node.getState());
				
				if (node.getState().equals(goalState))
					return node;

				nodeList.addAll(prodSystem.expand(node));
			}

		}
		return null;
	}
    
	public static void main(String[] args) {
		List<Integer> times = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 13));

		BreadthFirstSearch breathFirstSearch = new BreadthFirstSearch();

		Node node = breathFirstSearch.search(times);

		System.out.println(node.getAction());
	}
}
