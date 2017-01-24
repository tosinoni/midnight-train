package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MidnightTrainDemo {

	public static String[] populateModel() {
		return null;
	}

	public static int getValidNumberInput(JFrame frame, String message) {
		int number = -1;

		while (number < 1) {
			String input = JOptionPane.showInputDialog(frame, message);

			if (NumberUtils.isDigits(input)) {
				int num = Integer.parseInt(input);
				if (num > 0) {
					number = num;
				}
			}
		}
		return number;
	}

	public static List<Integer> getTimesRequiredForMove(JFrame frame, int num) {

		List<Integer> timesList = new ArrayList<>();
		while (timesList.size() != num) {
			timesList.clear();
			String message = "Please enter " + num + " numbers for the times separating them with comma";
			
			String input = JOptionPane.showInputDialog(frame,message);
			String[] times = input.replaceAll("^[,\\s]+", "").split("[,\\s]+");

			for (int i = 0; i < times.length; i++) {
				if (NumberUtils.isDigits(times[i])) {
					timesList.add(Integer.parseInt(times[i]));
				}
			}
		}
		return timesList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Midnight Train Puzzle Demo");
		JOptionPane.showMessageDialog(frame,
				"The task is to move certain number of people accross a bridge "
						+ "in the night in the minimum time",
				"Midnight Train Puzzle Demo", JOptionPane.INFORMATION_MESSAGE);

		String message = "How many people do you want to move across the bridge?";
		int numOfPeople = getValidNumberInput(frame, message);
		List<Integer> times = getTimesRequiredForMove(frame, numOfPeople);

		// initialize the mvc
		MidnightTrain model = new MidnightTrain();

		model.setNumOfPeople(numOfPeople);
		MidnightTrainView view = new MidnightTrainView();
		MidnightTrainController controller = new MidnightTrainController(model,
				view);

	}

}
