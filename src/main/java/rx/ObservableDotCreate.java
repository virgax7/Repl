package rx;

import io.reactivex.Observable;

public class ObservableDotCreate {
    public static void main(String[] args) {
        /*
        create an Observable from scratch by means of a function
        - http://reactivex.io/documentation/operators/create.html
         */

        final Observable<Integer> numbers = Observable.create(emitter -> {
            // emitter does onNext to pass emission
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        });

        // subscribe to it.. notice the lazyness(this is good)
        numbers.subscribe(System.out::println);
        numbers.subscribe(emission -> System.out.println(emission * 10));

        // you can use the onComplete function to signal end of stream
        final Observable<String> names = Observable.create(emitter -> {
            emitter.onNext("Paul");
            emitter.onNext("Helen");
            emitter.onComplete();
        });

        // Action is just a pseudo Runnable that rxjava guys thought was a better name I guess
        // also doOnComplete is like a intermediate operator, while subscribe is more like
        // the terminal operator... if you are thinking in terms of java 8 streams
        names.doOnComplete(() -> System.out.println("Hey! names emissions are no more"))
                .subscribe(System.out::println);

        // notice that the Action onComplete isn't called -- i.e no sysout of "Hey! namesWith.... more"
        // also emission 3 is not passed down since stream has closed off due to error
        final Observable<Integer> namesWithException = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2 / 0);
            emitter.onNext(3);
            emitter.onComplete();
        });
        namesWithException.subscribe(System.out::println,
                        error -> System.out.println("Oh no an error! " + error),
                        () -> System.out.println("Hey! namesWithException emissions are no more"));

        // you can catch exceptions and send things to the error channel
        // however, this still closes the channel onNext flows through... but we can resume next(reopen another channel
        // that you can flow regular emissions from)
        final Observable<Integer> namesWithExceptionAndCatch = Observable.create(emitter -> {
            try {
                emitter.onNext(50);
                emitter.onNext(2 / 0);
                emitter.onNext(58);
                emitter.onComplete();
            } catch (Exception e) {
                // we can emit errors with onError.. this will let the error observer () -> Sout("Oh no!..) take care of the error emission
                // however error emission isn't really a great term, errors notification is probably a better word choice
                // we can conceptualize this as a channel and our channel handler () -> Sout("Oh no!...) will handle
                emitter.onError(e);
                System.out.println("caught an error! " + e);
            }
        });

        namesWithExceptionAndCatch
                // finish out the 58 from here and 60
                .onExceptionResumeNext(Observable.just(58, 60))
                .subscribe(System.out::println,
                            error -> System.out.println("Oh no an error! " + error),
                            () -> System.out.println("Hey! namesWithException emissions are no more"));

        // lets try that with retry operator.. i.e a error handler which tries resubscribing

        // more comments from official docs
        /*
        A more complete subscribe call example looks like this:

        def myOnNext     = { item ->  do something useful with item  };
        def myError      = { throwable ->  react sensibly to a failed call  };
        def myComplete   = { clean up after the final response  };
        def myObservable = someMethod(itsParameters);
        myObservable.subscribe(myOnNext, myError, myComplete);
        // go on about my business

        By convention, in this document, calls to onNext are usually called “emissions” of items,
        whereas calls to onCompleted or onError are called “notifications.”

        - http://reactivex.io/documentation/observable.html
        */




    }
}
