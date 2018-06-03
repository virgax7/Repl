package rx;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleMaybe {
    public static void main(String[] args) {
        //--------------- single like its name implies, gives off one OR zero emission
        // this is similar to Mono, in spring reactor

        Single.create(singleEmitter -> {
            singleEmitter.onSuccess(1);
            singleEmitter.onSuccess(2);
        }).subscribe(System.out::println);
        // the above will only still print 1

        //--------------- Maybe is similar to Optional(java 8), in that it can have no value or a value
        Maybe.just(7).subscribe(System.out::println);
        Maybe.empty().subscribe(System.out::println, System.out::println, () -> System.out.println("maybe.empty complete"));

        // Observable.firstElement returns Maybe
        final Maybe<Integer> integerMaybe = Observable.just(1).firstElement();
        integerMaybe.subscribe(System.out::println);
    }
}


































