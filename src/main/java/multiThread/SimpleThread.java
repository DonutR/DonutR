package multiThread;

public class SimpleThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new SimpleThread())).start();
    }

    public static class HelloThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello from a thread!");
        }

        public static void main(String args[]) {
            (new HelloThread()).start();
        }

    }

}
