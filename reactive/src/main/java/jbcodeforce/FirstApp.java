package jbcodeforce;


import rx.Observable;
import rx.Single;

/**
 * 
 *
 */
public class FirstApp 
{
    public static void main(String[] args) {
        Single<String> single = Observable.just("Hello RxJava")
        .toSingle()
        .doOnSuccess(System.out::print)
        .doOnError(e -> {
            throw new RuntimeException(e.getMessage());
        });
      single.subscribe();
    }
}
