package chapter01;

/**
 * @author Lwq
 * @create 2018-08-01 12:07
 * @desc    死锁
 **/
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        System.out.println("01拿到了A");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("01拿到了B");
                    }
                }
            }
        });
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    try {
                        System.out.println("02拿到了B");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("02拿到了A");
                    }
                }
            }
        });
        thread01.start();
        thread02.start();
    }
}
