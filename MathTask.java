public class MathTask implements Runnable {

    private final int start;
    private final int end;

    public MathTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        long sum = 0;

        for (int i = start; i < end; i++) {
            for (int j = 0; j < 100; j++) {
                sum += (long) i * i * i + (long) i * j;
            }
        }

        System.out.println(sum);
    }
}