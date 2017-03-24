import java.util.Stack;

public class QuickSort implements Sorter {
	private int[] list;
	private int startRange;
	private int endRange;
	private Stack<Integer> rightRangesStart = new Stack<>();
	private Stack<Integer> rightRangesEnd = new Stack<>();

	public void setList(int[] list) {
		this.list = list;
		this.startRange = 0;
		this.endRange = list.length-1;
	}

	private int pivot;
	private int dir;
	private int index = -1;
	private int indexLeft;
	private int indexRight;
	public boolean startCycle() {
		if (index != -1) {
			if (index - startRange <= 1) { // Switch naar rechts
				if (!rightRangesStart.empty()) {
					startRange = rightRangesStart.pop();
					endRange = rightRangesEnd.pop();
				} else {
					return true; // Stop
				}
			} else { // Switch of blijf links
				endRange = index - 1;
			}
		}
		pivot = list[startRange];
		dir = -1;
		indexLeft = startRange;
		indexRight = endRange;
		return false; // Doorgaan
	}

	public boolean runCycle() {
		if (indexLeft==indexRight) {
			index = indexLeft;
			list[index] = pivot;
			if (endRange - (index + 1) >= 1) {
				rightRangesStart.push(index + 1);
				rightRangesEnd.push(endRange);
			}
			return false;
		}

		if (dir == 1) { // Van links naar rechts, empty index rechts
			if (list[indexLeft] > pivot) {
				list[indexRight] = list[indexLeft]; // Value groter dan pivot, verplaatsen naar empty index rechts
				indexRight--;
				dir = -1;
			} else {
				indexLeft++;
			}
		} else { // Van rechts naar links, empty index links
			if (list[indexRight] <= pivot) {
				list[indexLeft] = list[indexRight]; // Value kleiner dan of gelijk aan pivot, verplaatsen naar empty index links
				indexLeft++;
				dir = 1;
			} else {
				indexRight--;
			}
		}

		return true; // Doorgaan
	}

	public int[] getList() {
		return list;
	}
}
