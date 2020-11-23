package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
	@Test
	public void testEnqueue() {
		Queue q = new Queue(3);
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		assertEquals(1,q.dequeue());
		assertEquals(2,q.dequeue());
		assertEquals(3,q.dequeue());
	}

	@Test
	public void testDequeue() {
		Queue q = new Queue(3);
		try {
			q.dequeue();
			fail();
		} catch(IllegalStateException e) {

		}
	}
}//end test
