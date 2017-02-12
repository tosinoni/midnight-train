package src.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.heuristics.HeuristicByAverage;
import src.main.heuristics.HeuristicByCounting;
import src.main.heuristics.HeuristicByDistance;
import src.main.searches.AstarSearch;
import src.main.searches.BreadthFirstSearch;
import src.main.searches.DepthFirstSearch;

public class MidnightTrainDemo {

	public static List<Integer> getSearchTimes(String input) {
		String[] times = input.replaceAll("[^-?0-9]+", " ").split(" ");
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < times.length; i++) {
			list.add(Integer.parseInt(times[i]));
		}

		return list;
	}

	public static boolean isDigitValid(String num) {
		try {
			return Integer.parseInt(num) >= 0 && Integer.parseInt(num) >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isTimeValid(String input, int num) {
		String[] times = input.replaceAll("[^-?0-9]+", " ").split(" ");

		if (times.length != num)
			return false;
		for (int i = 0; i < times.length; i++) {
			if (!isDigitValid(times[i])) {
				return false;
			}
		}

		return true;
	}

	public static void printSearchOptions(Scanner sc, String input) {
		System.out.println("1. Breadth First Search");
		System.out.println("2. Depth First Search");
		System.out.println("3. A star Search");

		System.out.println("Please enter the search you want to perform: ");
		Node node;
		String search = sc.nextLine();

		List<Integer> times = getSearchTimes(input);
		if (search.equals("1")) {
			BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
			node = breadthFirstSearch.search(times);
		} else if (search.equals("2")) {
			DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
			node = depthFirstSearch.search(times);
		} else {
			node = printHeuristicSearchOptions(sc, times);
		}

		if (node != null && node.getAction() != null) {
			String[] parts = node.getAction().trim().split("\\-");
			String[] moves = node.getMove().trim().split("\\-");

			for (int i = 0; i < parts.length; i++) {

				if (parts[i] != null && moves[i] != null)
					printState(parts[i], moves[i]);
			}
		}
	}

	public static void printState(String state, String move) {
		String[] parts = state.split("\\|");

		if (parts.length > 1) {

			System.out.println(" ********************************* moved person " + move
					+ " ****************************");

			System.out.print("Left: " + parts[0]);

			if (parts[1].equals("R") || parts[2].equals("R")) {
				System.out
						.print(" ===========================================>>> ");
			} else {
				System.out
						.print(" <<<===========================================");
			}

			System.out.print("Right: " + parts[1]);

			System.out.println("\n");
		}

	}

	public static Node printHeuristicSearchOptions(Scanner sc,
			List<Integer> times) {
		System.out.println("1. Heuristic By Sum Of People On Right Side");
		System.out.println("2. Heuristic By Sum Of people On the Left Side");
		System.out.println("3. Heuristic By Average");

		System.out
				.println("Please enter the heuristic search you want to do: ");

		String heuristic = sc.nextLine();
		AstarSearch astarSearch;
		if (heuristic.equals("1")) {
			astarSearch = new AstarSearch(new HeuristicByCounting());
		} else if (heuristic.equals("2")) {
			astarSearch = new AstarSearch(new HeuristicByDistance());
		} else {
			astarSearch = new AstarSearch(new HeuristicByAverage());
		}

		return astarSearch.search(times);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean repeat = true;
		int numOfPeople;
		while (repeat) {
			System.out
					.println("Please enter the number of people you want to move across the bridge: ");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if (isDigitValid(input)) {
				numOfPeople = Integer.parseInt(input);
				boolean timesValid = false;

				while (!timesValid) {
					System.out.println("Please enter the time for each of the "
							+ numOfPeople + " people: ");
					sc = new Scanner(System.in);
					input = sc.nextLine();
					timesValid = isTimeValid(input, numOfPeople);
				}

				String time = input;

				boolean searchRequested = true;
				while (searchRequested) {
					System.out.println("1. Yes");
					System.out.println("2. No");
					System.out.println("Do you want to perform a search? : ");

					input = sc.nextLine();

					if (input.equals("1"))
						printSearchOptions(sc, time);
					else
						searchRequested = false;
				}

				System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.println("Do you want to Exit? : ");
				
				input = sc.nextLine();
				if (input.equals("1")){
					sc.close();
					repeat = false;
				}
			}
		}
	}

}
