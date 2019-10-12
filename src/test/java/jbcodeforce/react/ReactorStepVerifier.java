package jbcodeforce.react;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactorStepVerifier {

	public <T> Flux<T> appendBoomError(Flux<T> source) {
		  return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
		}
	
	/**
	 * expect this Flux to first emit foo, then emit bar, and then produce an error with the message, boom.
	 * StepVerifier helps to express expectations about the next signals to occur  
	 */
	@Test
	public void testAppendBoomError() {
		  Flux<String> source = Flux.just("foo", "bar"); 
          // StepVerifier builder wraps and verifies a Flux
		  StepVerifier.create( 
		    appendBoomError(source)) 
		    .expectNext("foo") 
		    .expectNext("bar")
		    .expectErrorMessage("boom") 
		    .verify(); 
	}

}
