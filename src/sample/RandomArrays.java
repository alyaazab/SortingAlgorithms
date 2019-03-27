package sample;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomArrays {

    public static int[] generateRandomArray(int size) {
        return generateRandomArray(size, 0, 1000);
    }

    public static int[] generateRandomArray(int size, int origin, int bound) {
        Random random = new Random();
        IntStream intStream = random.ints(size, origin, bound + 1);
        return intStream.toArray();
    }
}
