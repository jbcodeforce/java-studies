package jbcodeforce.http.client;

import org.junit.Assert;
import org.junit.Test;

public class TestRestCall {
	
	private class Todo {
		public int userId;
		public int id;
		public String title;
		public boolean completed;
		
		public Todo() {
			
		}
	}

	@Test
	public void testGetOnPublicAPI() {
		RestClient c = new RestClient("https","jsonplaceholder.typicode.com");
		try {
			String rep=c.executeGetMethod("/todos/1", null);
			System.out.println(rep);
			Todo t = RestClient.getJsonParser().fromJson(rep, Todo.class);
			Assert.assertTrue(1 == t.userId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
