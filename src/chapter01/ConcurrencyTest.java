package chapter01;

/**
 * @author Lwq
 * @create 2018-08-01 9:38
 * @desc    串行和并发的速度比较
 **/
public class ConcurrencyTest {
    private static final long count = 10000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0 ; i < count ; i++){
            a+=5;
        }
        int b = 0;
        for(long i = 0; i < count ; i++){
            b+=5;
        }
        long end = System.currentTimeMillis();
        System.out.println("serial: " + (end-start) + "ms, b=" + b + ", a=" + a);
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i = 0 ; i < count ; i++){
                    a+=5;
                }
            }
        });
        thread.start();
        int b = 0;
        for(long i = 0; i < count ; i++){
            b+=5;
        }
        thread.join();
        long end = System.currentTimeMillis();
        System.out.println("concurrency: " + (end-start) + "ms, b=" + b);
    }
}
