import java.util.Arrays;
import java.util.Random;

public class ArrayMultiplication {
    public static void main(String[] args) {
        int[] input1 = generateRandomArray(10000);
        int[] input2 = generateRandomArray(10000);

        // Синхронний варіант
        long time1 = System.currentTimeMillis();
        int[] resultSync = multiplyArraysSync(input1, input2, 1);
        System.out.printf("sync : %s\n", System.currentTimeMillis() - time1);

        // Варіант з parallel stream
        long time2 = System.currentTimeMillis();
        int[] resultParallel = multiplyArraysParallel(input1, input2, 1);
        System.out.printf("parallel : %s\n", System.currentTimeMillis() - time2);
    }

    public static int[] generateRandomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(101); // Випадкове число від 0 до 100
        }
        return array;
    }

    public static int[] multiplyArraysSync(int[] input1, int[] input2, int sleep) {
        int[] result = new int[input1.length];
        for (int i = 0; i < input1.length; i++) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result[i] = input1[i] * input2[i];
        }
        return result;
    }

    public static int[] multiplyArraysParallel(int[] input1, int[] input2, int sleep) {
        int[] result = new int[input1.length];
        Arrays.parallelSetAll(result, i -> {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return input1[i] * input2[i];
        });
        return result;
    }
}
