package rx;

import io.reactivex.Observable;

public class ExceptionsAndThreads {
    public static void main(String[] args) {
        //notice here that the error does not terminate the main thread
        // although it throws an exception in main thread.. it is handled and execution continues, since its not thrown
        System.out.println(Thread.currentThread().getName());
        try {
            doRangeWithException();
        } catch (Exception e) {
            System.out.println("this never gets called");
        }


        System.out.println("prints");
        int i = 1 / 0;
        System.out.println("Doesn't print");
    }

    private static void doRangeWithException() {
        Observable.range(5, 3)
                .subscribe(i -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println( i / 0);
                });
    }
}
