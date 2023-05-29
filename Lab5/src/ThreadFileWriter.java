import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreadFileWriter {
    private static volatile int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = createThread("Thread 1", 250);
        Thread thread2 = createThread("Thread 2", 500);
        Thread thread3 = createThread("Thread 3", 1000);

        thread1.start();
        thread2.start();
        thread3.start();

        // Обмеження часу виконання до значення counter == 240
        while (counter <= 240) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Зупинка потоків після досягнення обмеження
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    }

    private static Thread createThread(String name, int sleepTime) {
        return new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true))) {
                while (!Thread.currentThread().isInterrupted()) {
                    writer.println(name + " - " + System.currentTimeMillis() + " - Counter: " + counter);
                    counter++;
                    Thread.sleep(sleepTime);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
