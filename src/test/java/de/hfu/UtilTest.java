package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {
	@Test
	public void testIstErstesHalbjahr() {
		assertTrue(Util.istErstesHalbjahr(1));
		assertTrue(Util.istErstesHalbjahr(2));
		assertTrue(Util.istErstesHalbjahr(3));
		assertTrue(Util.istErstesHalbjahr(6));
		assertTrue(Util.istErstesHalbjahr(7));
		assertFalse(Util.istErstesHalbjahr(8));
		assertFalse(Util.istErstesHalbjahr(10));
		assertFalse(Util.istErstesHalbjahr(12));
		
		try {
			Util.istErstesHalbjahr(13);
			fail();
		}catch (IllegalArgumentException e) {
			
		}
		
		try {
			Util.istErstesHalbjahr(0);
			fail();
		}catch (IllegalArgumentException e) {
			
		}
		
	}//test ende
}
