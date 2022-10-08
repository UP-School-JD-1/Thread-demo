import java.util.concurrent.atomic.AtomicInteger;

public class App {
    // instrinsic lock (monito r)
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    private static Integer number = new Integer(5);
    public static int counter1 = 0;
    public static int counter2 = 0;

    public static void increment1() {  // 101
        synchronized (lock1) {
            number++;
            counter1++;


        }
    }
    public static void tempIncrement(){
        synchronized (lock1){

        }
    }

    public static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment1();

                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The counter is : " + counter1);
        System.out.println("The counter is : " + counter1);
    }

    public static void main(String[] args) {

        process();
    }
}


// ilk olarak sayıyı memory'den okur 0 t2 =0
// değeri arttırır   t1 =1   t2=1
// memory'e yazar
// değişken ile geri döndürür

