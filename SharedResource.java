public class SharedResource {

    private int value;
    private boolean bChanged = false;

    public synchronized void set(int value) {
        while (bChanged) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.value = value;
        bChanged = true;
        notify();
    }

    public synchronized int get() {
        while (!bChanged) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        bChanged = false;
        notify();
        return value;
    }
}