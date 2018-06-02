package rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


public class ObserverOverriding {
    public static void main(String[] args) {
        final Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5);

        final Observer<String> observer = new Observer<>() {
            @Override
            public void onSubscribe(final Disposable disposable) {
                // toggle below to dispose, this will stop the flow of emissions at even better hitting onNext of the current emission
//                disposable.dispose();
                System.out.println(disposable.isDisposed());
            }

            @Override
            public void onNext(final String s) {
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

        numbers.map(number -> "" + number)
                .subscribe(observer);

        System.out.println("--------------------------------------------------------------");

        numbers.map(number -> "" + number / 0)
                .subscribe(observer);


        System.out.println("----------- Lambda Version -----------------------------------");

        // kind of wondering why they didn't use the jdk18 consumer and just a regular Runnable
        // instead of a pseudo Runnable called Action.. wouldn't that make things more flexible???
        final Consumer<String> onNextFunction = System.out::println;
        final Action onCompleteFunction = () -> System.out.println("Observable's emissions are all done");
        final Consumer<Throwable> onError = throwable -> System.out.println("oops an error happened " + throwable);

        // recall from earlier how we implemented something similar to
        /*
                A more complete subscribe call example looks like this:
        def myOnNext     = { item ->  do something useful with item  };
        def myError      = { throwable ->  react sensibly to a failed call  };
        def myComplete   = { clean up after the final response  };
        def myObservable = someMethod(itsParameters);
        myObservable.subscribe(myOnNext, myError, myComplete);
        - http://reactivex.io/documentation/observable.html
         */

        numbers.map(number -> "" + number).subscribe(onNextFunction, onError, onCompleteFunction);
    }
}
