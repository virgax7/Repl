package rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    }
}
