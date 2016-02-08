
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Runner {

    public static void main(String[] args) {

        int[] arr = generateRandomArray(1000);
        new ForkJoinPool().invoke(new QuickSortTask(arr));
        if (ArrayUtils.isSorted(arr)) {
            System.out.println("array is sorted");
        }
    }

    private static int[] generateRandomArray(int size) {

        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt();
        }
        return arr;
    }
}
