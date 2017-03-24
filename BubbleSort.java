import java.util.Random;
import java.util.Arrays;
import javafx.application.Application;

public class BubbleSort implements Sorter {
	private int[] list;

	private int index = 0;
	private boolean moved = false;

	public void setList(int[] list) {
		this.list = list;
	}

	private int cycles = 0;

	public boolean startCycle() {
		if (!moved && index > 0) {
			return true;
		}
		index = 0;
		moved = false;
		cycles++;
		return false;
	}

	public boolean runCycle() {
		if (index >= list.length-cycles) {
			return false;
		}

		int tmp = 0;

		int i = index;
		int n = i+1;
		index++;

		if (list[i] > list[n]) {
			tmp = list[n];
			list[n] = list[i];
			list[i] = tmp;
			moved = true;
		}

		if (index >= list.length-cycles) {
			return false;
		}

		return true;
	}

	public int[] getList() {
		return list;
	}

	public static void main(String[] args) {
		int[] l = Main.getList();

		BubbleSort s = new BubbleSort();
		s.setList(l);

		Main.setSorter(s);

		Application.launch(Window.class, args);
	}
}
