package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class State {

	// the time it takes for the first person to cross the bridge
	private String timeForPeopleOnTheLeft;

	// the time it takes for the second person to cross the bridge
	private String timeForPeopleOnTheRight;

	// true for when the torch is across the bridge and false otherwise
	private boolean torchLocation;

	public State(String timeForPerson1, String timeForPerson2) {
		this(timeForPerson1, timeForPerson2, false);
	}

	public State(String timeForPeopleOnTheLeft, String timeForPeopleOnTheRight,
			boolean torchLocation) {
		this.timeForPeopleOnTheLeft = timeForPeopleOnTheLeft;
		this.timeForPeopleOnTheRight = timeForPeopleOnTheRight;
		this.torchLocation = torchLocation;
	}

	public String getTimeForPeopleOnTheLeft() {
		return reArrangeTime(timeForPeopleOnTheLeft);
	}

	public void setTimeForPeopleOnTheLeft(String timeForPerson1) {
		this.timeForPeopleOnTheLeft = timeForPerson1;
	}

	public String getTimeForPeopleOnTheRight() {
		return reArrangeTime(timeForPeopleOnTheRight);
	}

	public void setTimeForPeopleOnTheRight(String timeForPerson2) {
		this.timeForPeopleOnTheRight = timeForPerson2;
	}

	public boolean isTorchLocation() {
		return torchLocation;
	}

	public void setTorchLocation(boolean torchLocation) {
		this.torchLocation = torchLocation;
	}

	private List<Integer> convertStringToList(String s) {
		List<Integer> list = new ArrayList<Integer>();
		Scanner scanner = new Scanner(s);

		while (scanner.hasNextInt()) {
			list.add(scanner.nextInt());
		}

		scanner.close();
		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + timeForPeopleOnTheLeft.hashCode();
		result = prime * result + timeForPeopleOnTheRight.hashCode();
		result = prime * result + Boolean.hashCode(torchLocation);

		return result;
	}

	public List<String> convertToListOfString(String s) {
		List<String> list = new ArrayList<>();
		Scanner scanner = new Scanner(s);

		while (scanner.hasNextInt()) {
			list.add(scanner.next());
		}

		scanner.close();
		return list;
	}

	public List<Integer> getListOfTimeOnLeft() {
		return convertStringToList(timeForPeopleOnTheLeft);
	}

	public List<Integer> getListOfTimeOnRight() {
		return convertStringToList(timeForPeopleOnTheRight);
	}

	public String reArrangeTime(String s) {
		String[] numbers = s.trim().split("\\D+");
		Arrays.sort(numbers, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Integer.valueOf(s1).compareTo(Integer.valueOf(s2));
			}
		});

		String returnString = "";
		for (int i = 0; i < numbers.length; i++)
			returnString += numbers[i] + " ";

		return returnString.trim();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		else if (obj == null || obj.getClass() != this.getClass())
			return false;

		State state = (State) obj;

		return (this.getTimeForPeopleOnTheLeft().equals(state
				.getTimeForPeopleOnTheLeft()))
				&& (this.getTimeForPeopleOnTheRight().equals(state
						.getTimeForPeopleOnTheRight()))
				&& (this.torchLocation == state.torchLocation);
	}

	public String toString() {
		String location = "L";

		if (torchLocation)
			location = "R";
		return getTimeForPeopleOnTheLeft() + "|" + getTimeForPeopleOnTheRight()
				+ "|" + location;
	}

}
