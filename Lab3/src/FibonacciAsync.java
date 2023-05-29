import java.util.concurrent.CompletableFuture;

public class FibonacciAsync {
    public static void main(String[] args) {
        int n = 20; // Задане число n

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> fibonacci(n));

        // Вивід логу, що програма чекає закінчення асинхронного потоку
        System.out.println("Waiting for the asynchronous computation to complete...");

        // Очікування завершення асинхронного потоку та отримання результату
        int result = future.join();

        // Виведення отриманого значення
        System.out.println("Fibonacci number at position " + n + ": " + result);
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}