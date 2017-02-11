package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
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

			List<String> moveTimes = getMoveAsSTring(move);

			List<String> leftList = currentState.convertToListOfString(currentState.getTimeForPeopleOnTheLeft());
			List<String> rightList = currentState.convertToListOfString(currentState.getTimeForPeopleOnTheRight());

			if (!currentState.isTorchLocation()) {
				leftList.remove(moveTimes.get(0));
				rightList.add(moveTimes.get(0));
			} else {
				rightList.remove(moveTimes.get(0));
				leftList.add(moveTimes.get(0));
			}
			
			if (moveTimes.size() > 1 && !currentState.isTorchLocation()) {
				leftList.remove(moveTimes.get(1));
				rightList.add(moveTimes.get(1));
			} else if (moveTimes.size() > 1) {
				rightList.remove(moveTimes.get(1));
				leftList.add(moveTimes.get(1));
			}
			State newState = new State(getNewTimes(leftList).trim(), getNewTimes(rightList).trim(), !currentState.isTorchLocation());


			newNode = new Node(newState);
			newNode.setParent(node);
			newNode.setMove(node.getMove() + "-" + move);
			newNode.setAction(node.getAction() + "-" + newState.toString());

		}
		return newNode;
	}

	public String getNewTimes(List<String> times) {
		String s = "";
		for (String time : times) {
			s += time + " ";
		}
		
		return s;
	}

	public boolean canMove(String state, String move) {
		List<String> moveTimes = getMoveAsSTring(move);
       Set<String> states = convertStringToList(state);
       
		if (moveTimes.size() < 2) {
			return states.contains(moveTimes.get(0));
		}
		return states.contains(moveTimes.get(0)) && states.contains(moveTimes.get(1));
	}
	
	private Set<String> convertStringToList (String s) {
		Set<String> list = new LinkedHashSet<>();
		Scanner scanner = new Scanner(s);

		while (scanner.hasNextInt()) {
		    list.add(scanner.next());
		}
		
		scanner.close();
		return list;
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
		List<Integer> times = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 13));

		ProductionSystem prod = new ProductionSystem(times);

		// for (String move : prod.getPossibleMoves()) {
		// System.out.println(move);
		// }

		State state = new State("1 2 3 5 8 13", "");
		Node node = new Node(state);
		for (Node n : prod.expand(node)) {
			System.out.println(n.getState());
			System.out.println(n.getAction());

		}

	}

}
