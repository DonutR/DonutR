package multiThread;


public class SynchronizationStudy {


    public static class Shape {
        public static synchronized void add(Point s, int x) {
            s.sum += x;
        }
    }

    public static class Point extends Shape {
        int sum = 0;

        public void addDummy(Point p, int x) {
            this.add(p, 1);
        }

        public int getSum() {
            return sum;
        }
    }

    public final static int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {
        final Point p = new Point();
        final Point p2 = new Point();

        Thread t1 = new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < ITERATIONS; i++) {
                    p.addDummy(p2, 1);
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < ITERATIONS; i++) {
                    p.addDummy(p2, 1);
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        System.out.println(p.getSum()); // should equal 200000
        System.out.println(p2.getSum()); // should equal 200000
    }

}
