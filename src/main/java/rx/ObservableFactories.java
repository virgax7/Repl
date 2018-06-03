package rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ObservableFactories {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // we seen this one before
//        Observable.interval(100, MILLISECONDS)
//                .subscribe(System.out::println);
//

        // like java 8 , Intstream.range, but there is a difference

        // prints 1, 2
        Observable.range(1, 2)
                .subscribe(System.out::println);
        // prints 1
        IntStream.range(1, 2)
                .forEach(System.out::println);


        // see... unlike Intstream.range, Observable.range has a start value, and then how many times it should
        // send an emissions incremented from the previous val
        // prints 5, 6, 7
        Observable.range(5, 3)
                .subscribe(System.out::println);

        System.out.println("-------------------------------------");

        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Integer> returnFiveLater = executorService.submit(() -> {
            Thread.sleep(1000);
            return 5;
        });

        System.out.println(returnFiveLater.get());
        System.out.println(returnFiveLater.get());

        /*
        just some note i ran across

        By default, rx is single threaded, meaning that an Observable and
         the chain of operators that we can apply to it will notify
          its observers on the same thread on which subscribe method is called.
            -http://www.baeldung.com/rxjava-schedulers
         */

        Observable.fromFuture(returnFiveLater).subscribe(System.out::println);

        executorService.shutdown();

        System.out.println("--------------------------------------------");

        // defer is useful for making new parameterized streams
        MutableInt item1 = new MutableInt(1);
        Observable<Integer> numbas = Observable.defer(() -> Observable.just(item1.getValue()));

        numbas.subscribe(System.out::println); // prints 1
        item1.increment();
        numbas.subscribe(System.out::println); // prints 2
        // the parametized states(item1,2) have changed and now the emissions reflect that since we used defer to build a subscriber

        // however if you don't this will happen
        Observable<Integer> numbasRegular = Observable.just(item1.getValue());
        item1.increment();
        numbasRegular.subscribe(System.out::println); // prints 2

        // you can handle exceptions from the subscriber, even if the error happened in the observable
        // essentially, we are passing the error to the error channel where our handler handles it with oh no! got an error
        Observable.fromCallable(() -> 2 / 0)
                .subscribe(System.out::println, e -> System.out.println("oh no! got an error : " + e));
    }
}

