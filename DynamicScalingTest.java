public class DynamicScalingTest {

    public static void main(String[] args) throws InterruptedException {

        int totalWork = 10_000_000;

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);

        long start1 = System.nanoTime();

        Thread singleThread = new Thread(new MathTask(0, totalWork));
        singleThread.start();
        singleThread.join();

        long end1 = System.nanoTime();
        System.out.println((end1 - start1) / 1_000_000);

        Thread[] threads = new Thread[cores];
        int chunkSize = totalWork / cores;

        long start2 = System.nanoTime();

        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            int end = (i == cores - 1) ? totalWork : start + chunkSize;

            threads[i] = new Thread(new MathTask(start, end));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long end2 = System.nanoTime();
        System.out.println((end2 - start2) / 1_000_000);
    }
}