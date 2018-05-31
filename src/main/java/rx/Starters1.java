package rx;

import io.reactivex.Observable;

public class Starters1 {
    public static void main(String[] args) {
        // Observers pushes data(emission) to observers
        final Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5);

        // subscribe to it with a observer, not rlly.. its just a consumer, real Observer observer has more methods to implement
        // however, its still an "observer"

        // just like streams, intermediary operators return a stream(intermediary ops are identity function with I,O type params)
        numbers.subscribe(System.out::println);
        numbers.map(number -> number * 10)
                .subscribe(System.out::println);

        // observers work with 3 event types
        // its best to conceptualize them as their own rivers or channels if you will, where emissions will stream or flow...
        /*
        onNext
            - kind of like next on an iterator or rather java stream on emitter's functions
            - it means stream an element down.. or pass an element down the stream
        onComplete
            - signals the end of the so called stream to all the "observers"
        onError
            - signals an error that happened downwards to the "stream" and the observer should handle it
            - if a retry operator(http://reactivex.io/documentation/operators/retry.html) handles the error
                by resubscribing, then the stream is closed and no more emissions are flowed down
         */

        /*
        onNext
            An Observable calls this method whenever the Observable emits an item.
            This method takes as a parameter the item emitted by the Observable.
        onCompleted
            An Observable calls this method after it has called onNext for the final time, if it has not encountered any errors.
        onError
            An Observable calls this method to indicate that it has failed to generate the expected data or has encountered some other error.
            It will not make further calls to onNext or onCompleted. The onError method takes as its parameter an indication of what caused the error.

        - http://reactivex.io/documentation/observable.html
         */

    }
}
