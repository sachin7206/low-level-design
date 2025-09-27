package entities;

public class Timer {
    private Thread thread;
    private boolean running;

    public void start(int duration, Runnable callback) {
        running = true;
        thread = new Thread(() -> {
            try {
                Thread.sleep(duration * 1000L); // Convert seconds to milliseconds
                if(running) {
                    callback.run();
                }
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted");
            }
        });
        thread.start();
    }

    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }
}
