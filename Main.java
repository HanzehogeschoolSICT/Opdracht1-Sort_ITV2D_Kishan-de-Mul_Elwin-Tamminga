import javafx.application.Application;

import java.util.Random;

public class Main {
	public static Sorter sorter;
	private static final int length = 50;
	private static final int N = 100;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Main (InsertionSort | BubbleSort | QuickSort)");
			System.exit(1);
		} else {
			int[] l = getList();

			switch (args[0]) {
				case "InsertionSort":
					sorter = new InsertionSort();
					break;
				case "BubbleSort":
					sorter = new BubbleSort();
					break;
				case "QuickSort":
					sorter = new QuickSort();
					break;
				default:
					System.err.println("Invalid argument: " + args[0]);
					System.exit(1);
			}

			sorter.setList(l);
			Application.launch(Window.class, args);
		}
	}

	private static int[] getList() {
		Random rand = new Random();
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[i] = rand.nextInt(N);
		}

		return list;
	}
}
