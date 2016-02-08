import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {

    private final int[] arrayForSorted;
    private final int   left;
    private final int   right;

    public QuickSortTask(int[] arrayForSorted) {

        this(arrayForSorted, 0, arrayForSorted.length - 1);
    }

    private QuickSortTask(int[] arrayForSorted, int left, int right) {

        this.arrayForSorted = arrayForSorted;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {

        int pivotIndex = partition(arrayForSorted, left, right);
        ForkJoinTask t1 = null;

        if (left < pivotIndex)
            t1 = new QuickSortTask(arrayForSorted, left, pivotIndex).fork();
        if (pivotIndex + 1 < right)
            new QuickSortTask(arrayForSorted, pivotIndex + 1, right).invoke();

        if (t1 != null)
            t1.join();

    }

    public static int partition(int[] a, int left, int right) {

        // chose middle value of range for our pivot
        int pivotValue = a[middleIndex(left, right)];

        --left;
        ++right;

        while (true) {
            do
                ++left;
            while (a[left] < pivotValue);

            do
                --right;
            while (a[right] > pivotValue);

            if (left < right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            } else {
                return right;
            }
        }
    }

    // calculates middle index without integer overflow
    private static int middleIndex(int left, int right) {

        return left + (right - left) / 2;
    }
}
