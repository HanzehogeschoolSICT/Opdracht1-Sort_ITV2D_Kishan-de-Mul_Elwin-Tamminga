import java.util.Random;

public class Main {
	public static Sorter sorter;
	public static void setSorter(Sorter s) {
		sorter = s;
	}

	private static final int length = 50;
	private static final int N = 100;

	public static int[] getList() {
		Random rand = new Random();
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[i] = rand.nextInt(N);
		}

		return list;
	}
}
