package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProductionSystem {

	private List<String> possibleMoves = new ArrayList<>();

	public ProductionSystem(List<Integer> times) {
		setPossibleMoves(times);
	}

	public Set<Node> expand(Node node) {
		Set<Node> nodes = new LinkedHashSet<>();

		State state = node.getState();
		String timeForPeople = state.getTimeForPeopleOnTheLeft();

		if (state.isTorchLocation()) {
			timeForPeople = state.getTimeForPeopleOnTheRight();
		}

		for (String move : possibleMoves) {
			if (canMove(timeForPeople, move)) {
				nodes.add(createNode(node, move));
			}
		}
		return nodes;
	}

	public Node createNode(Node node, String move) {
		Node newNode = null;

		if (node != null) {
			State currentState = node.getState();

			List<String> newTimes = getNewTimes(currentState.getTimeForPeopleOnTheLeft(),
					currentState.getTimeForPeopleOnTheRight(), move);
			State newState = new State(newTimes.get(0).trim(), newTimes.get(1).trim(), !currentState.isTorchLocation());

			if (currentState.isTorchLocation()) {
				newTimes = getNewTimes(currentState.getTimeForPeopleOnTheRight(),
						currentState.getTimeForPeopleOnTheLeft(), move);
				newState = new State(newTimes.get(1).trim(), newTimes.get(0).trim(), !currentState.isTorchLocation());
			}


			newNode = new Node(newState);
			newNode.setParent(node);
			newNode.setAction(node.getAction() + " $" + move);

		}
		return newNode;
	}

	public List<String> getNewTimes(String leftTime, String rightTime, String move) {
		List<String> moveTimes = getMoveAsSTring(move);
		String newLeftTime = leftTime.replace(moveTimes.get(0), "");
		
		if (moveTimes.size() > 1) {
			newLeftTime = newLeftTime.replace(moveTimes.get(1), "");
		} 
		
		String newRightTime = rightTime + " " + move;

		return Arrays.asList(newLeftTime, newRightTime);
	}

	public boolean canMove(String state, String move) {
		List<String> moveTimes = getMoveAsSTring(move);
      
		if (moveTimes.size() < 2) {
			return state.contains(moveTimes.get(0));
		}
		return state.contains(moveTimes.get(0)) && state.contains(moveTimes.get(1));
	}

	public List<String> getMoveAsSTring(String time) {
		return Arrays.asList(time.replaceAll("[^-?0-9]+", " ").trim().split(" "));
	}


	public void setPossibleMoves(List<Integer> times) {
		if (times != null) {
			for (int i = 0; i < times.size(); i++) {
				int time = times.get(i);
				possibleMoves.add(Integer.toString(time));

				for (int j = i + 1; j < times.size(); j++) {
					possibleMoves.add(Integer.toString(time) + " " + Integer.toString(times.get(j)));
				}
			}
		}
	}

	public List<String> getPossibleMoves() {
		return possibleMoves;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> times = new ArrayList<>(Arrays.asList(1, 2, 5, 8));

		ProductionSystem prod = new ProductionSystem(times);

		// for (String move : prod.getPossibleMoves()) {
		// System.out.println(move);
		// }

		State state = new State("2 5 8", "1", true);
		Node node = new Node(state);
		for (Node n : prod.expand(node)) {
			System.out.println(n.getState());
			System.out.println(n.getAction());

		}

	}

}
