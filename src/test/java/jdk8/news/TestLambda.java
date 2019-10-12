package jdk8.news;

import static org.junit.Assert.*;

import org.junit.Test;
import static java.lang.System.out;

public class TestLambda {
	Runnable r1 = () -> out.println(this);
	Runnable r2 = () -> out.println(toString());
	
	public String toString() { return "Hello, TRex world!"; }
	
	@Test
	public void test() {
		new TestLambda().r1.run();
		new TestLambda().r2.run();
	}
}
