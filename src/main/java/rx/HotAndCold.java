package rx;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;


import static java.util.concurrent.TimeUnit.*;

public class HotAndCold {
    public static void main(String[] args) throws InterruptedException {
        // everything we did was cold... only when we subscribed did the observable start emitting stuff...
        // it also emits from start to complete/error -- important
        // also once passed in a cold way, the emission travels down, maintaining its form or its later mutated
        // form(not mutated from the original feed, but rather from a operator)

        // cold observable
        // again it runs the subscribe handler every time a subscriber subscribes
        final Observable<Long> pinger = Observable.interval(100, MILLISECONDS);
        pinger.subscribe(i -> {
            if (i < 3) {
                System.out.println("First subscriber pinging: " + i  + " in thread " + Thread.currentThread().getName());
            }
        });
        Thread.sleep(100);
        pinger.subscribe(i -> {
            if (i < 3) {
                System.out.println("  Second subscriber pinging: " + i + " in thread " + Thread.currentThread().getName() );
            }
        });
        Thread.sleep(400);


        System.out.println("--------------------------------------------------------------");
        // hot ones... an easy way to make a hot observable is by making a ConnectableObservable using the publish method
        // and triggering opening its stream by running the connect method...
        // since it doesn't rerun the subscribe handler, if you jumped in the stream to late, you won't be given the emissions
        // that flow down it
        final ConnectableObservable<Long> ping = Observable.interval(100, MILLISECONDS).publish();
        ping.subscribe(i -> System.out.println("First subscriber pinging: " + i + " in thread " + Thread.currentThread().getName()));
        ping.connect();
        Thread.sleep(300);
        ping.subscribe(i -> System.out.println("    Second subscriber pinging:" + i + " in thread " + Thread.currentThread().getName() ));
        Thread.sleep(500);
        // notice how the second subscriber doesn't start by pinging 0, it starts from when it jumped in the stream
        // this further enhances leverages observer pattern where if you just subscribe in time, then you just get notified
    }
}
