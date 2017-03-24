import java.util.Random;
import java.util.Arrays;
import javafx.application.Application;

public class InsertionSort implements Sorter {
	private int[] list;

	public void setList(int[] list) {
		this.list = list;
	}

	private int cycles = 0;

	public boolean startCycle() {
		if (cycles >= list.length) {
			return true;
		}
		cycles++;
		index = cycles;
		return false;
	}

	private int index = 0;

	public boolean runCycle() {
		if (index < 1) {
			return false;
		}

		int tmp = 0;
		int i = index;

		if (list[i-1] > list[i]) {
			tmp = list[i];
			list[i] = list[i-1];
			list[i-1] = tmp;
		} else {
			return false;
		}

		index--;

		if (index < 1) {
			return false;
		}

		return true;
	}

	public int[] getList() {
		return list;
	}

	public static void main(String[] args) {
		int[] l = Main.getList();

		InsertionSort s = new InsertionSort();
		s.setList(l);

		Main.setSorter(s);

		Application.launch(Window.class, args);
	}
}
