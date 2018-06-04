package rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Disposing {
    public static void main(String[] args) throws InterruptedException {
        // we need to close threads that running on and on and taking up resources once we don't need them
        // in our case, that'll be the infinite streams or huge streams that flow more than we need it to

        final Observable<Long> interval = Observable.interval(100, MILLISECONDS);
        final Disposable disposable = interval.subscribe(System.out::println);

        sleep(300);

        // after this it gets disposed... the stream is closed from the source
        disposable.dispose();

        sleep(300);

        // recall ObserverOverriding where we wrote this piece of code
        // toggle below to dispose, this will stop the flow of emissions at even better hitting onNext of the current emission
        //                disposable.dispose();

        // lets do that again

        final Observer<Long> observer = new Observer<>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(final Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(final Long s) {
                disposable.dispose();
                System.out.println("printing the next emission : " + s);
            }

            @Override
            public void onError(final Throwable throwable) {
                System.out.println("oops an error happened " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("Observable's emissions are all done");
            }
        };

        // will dispose after the first one
        interval.subscribe(observer);
        sleep(100);

        // if you got a lot of disposables, you can dispose them all
        final Disposable subscribe1 = interval.subscribe(System.out::println);
        final Disposable subscribe2 = interval.subscribe(System.out::println);

        sleep(300);
        new CompositeDisposable(subscribe1, subscribe2).dispose();
        sleep(300);
    }
}
