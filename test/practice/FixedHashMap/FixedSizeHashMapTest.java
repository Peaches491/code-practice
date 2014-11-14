package practice.FixedHashMap;

import static org.junit.Assert.*;
import org.junit.Test;

public class FixedSizeHashMapTest {
	
	@Test
	public void testConstructor() {
		int integerMapMax = 10;
		HistoryDeletingHashMap<Integer, Integer> integerMap = 
				new HistoryDeletingHashMap<Integer, Integer>(integerMapMax);
		assertNotNull(integerMap);
		assertEquals(0, integerMap.size());
		assertEquals(integerMapMax, integerMap.maxSize());
	}

	@Test
	public void test() {

		HistoryDeletingHashMap<Integer, Integer> integerMap = 
				new HistoryDeletingHashMap<Integer, Integer>(10);
		
		int firstSetSize = 6;
		
		for(int i = 0; i < firstSetSize; i++){
			integerMap.put(i, i);
		}
		
		assertEquals("After insertion into empty HistoryDeletingHashMap, "
				+ "size was incorect.", firstSetSize, integerMap.size());
		
		int offset = 10;
		for(int i = 0; i < 5; i++){
			integerMap.put(offset+i, offset+i);
		}
		
		integerMap.remove(12);
		integerMap.remove(7);
		
	}

}
