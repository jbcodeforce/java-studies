package jbcodeforce.react;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorSimpleTest {
	
	/**
	 * Flux has 0 to n elements. Can be created by using factory like just()
	 */
    @Test public void testStringAsFlux() {
    	// create a sequence of string
    	Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
    	// 	Subscribe and trigger the sequence, use lambda to print the received strings
    	seq1.subscribe( s -> System.out.println(s));
    	
    	List<String> iterable = Arrays.asList("banana", "apple", "nuts");
    	Flux<String> seq2 = Flux.fromIterable(iterable);
    	seq2.subscribe( s -> System.out.println(s));
    	
    	Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
    	numbersFromFiveToSeven.subscribe( n -> System.out.println(n));
    }
    
    @Test public void testMono() {
    	Mono<String> noData = Mono.empty(); 

    	Mono<String> data = Mono.just("dataMono");
    	data.subscribe( d -> System.out.println(d));
    }
    
    /**
     * Use two lambda expressions: one for the content we expect,  one for errors
     */
    @Test public void testErrorHandler() {
    	Flux<Integer> f = Flux.range(1, 5)
    			 		  .map(i -> {if (i <=3 ) return i;
    			 		  throw new RuntimeException("Got to 4"); 
    			 		  });
    	f.subscribe(i -> System.out.println(i),
    			    e -> System.err.println(e),
    			    () -> System.out.println("Done"));
    }
    
    /**
     * Use three lambda expressions: one for the content we expect,  one for errors
     * and one once the subscription complete:
     */
    @Test public void testDoneHandler() {
    	Flux<Integer> f = Flux.range(1, 5);
    	f.subscribe(i -> System.out.println(i),
    			    e -> System.err.println(e),
    			    () -> System.out.println("Done"));
    	// Error signals and completion signals are both terminal events and are exclusive of one another
    }
    
    /**
     * Generate a flux via a generator function. Start with 0. sink is the event emitted. Here it uses
     * the state to generate a string " 3 x i = 3*i"
     * when reaching test it stops emitting   
     */
    @Test public void testGenerateFluxSynchronously() {
    	Flux<String> flux = Flux.generate(
    		    () -> 0, 
    		    (state, sink) -> {
    		      sink.next("3 x " + state + " = " + 3*state); 
    		      if (state == 10) sink.complete(); 
    		      return state + 1; 
    		    });
    	flux.subscribe(s -> System.out.println(s));
    }
}
