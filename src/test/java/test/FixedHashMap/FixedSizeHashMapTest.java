package test.FixedHashMap;


import static org.junit.Assert.*;

import org.junit.Test;

import practice.FixedHashMap.HistoryDeletingHashMap;

public class FixedSizeHashMapTest {
	
	@Test
	public void testConstructor() {
		int integerMapMax = 10;
		HistoryDeletingHashMap<Integer, Integer> integerMap = 
				new HistoryDeletingHashMap<Integer, Integer>(integerMapMax);
		assertNotNull(integerMap);
		// Assert that the map is empty
		assertEquals(0, integerMap.size());
		// Assert that the map max size is what we set above 
		assertEquals(integerMapMax, integerMap.maxSize());
	}

	@Test
	public void testMuchLessThanFull() {
		int integerMapMax = 100;
		HistoryDeletingHashMap<Integer, String> integerMap = 
				new HistoryDeletingHashMap<Integer, String>(integerMapMax);
		
		int firstSetSize = 25;
		for(int i = 0; i < firstSetSize; i++){
			integerMap.put(i, ""+i);
		}
		
		assertEquals("After insertion into empty HistoryDeletingHashMap, "
				+ "size was incorect.", firstSetSize, integerMap.size());

		int secondSetSize = 5;
		for(int i = 100; i < 100+secondSetSize; i++){
			integerMap.put(secondSetSize+i, ""+secondSetSize+i);
		}
		
		assertEquals("After insertion into non-empty HistoryDeletingHashMap, "
				+ "size was incorect.", firstSetSize+secondSetSize, integerMap.size());
		
	}
	
	@Test
	public void testExactlyFull() {

		int integerMapMax = 100;
		HistoryDeletingHashMap<Integer, String> integerMap = 
				new HistoryDeletingHashMap<Integer, String>(integerMapMax);
		
		int firstSetSize = integerMapMax;
		for(int i = 0; i < firstSetSize; i++){
			integerMap.put(i, ""+i);
		}
		
		assertEquals("After insertion of <maxSize> elements into empty HistoryDeletingHashMap, "
				+ "size was incorect.", 
				firstSetSize, 
				integerMap.size());
		
		assertNotNull("After filling HistoryDeletingHashMap without overflow, "
				+ "oldest element was not found in map. (map.get() returned null)", 
				integerMap.get(0));

		int secondSetSize = 5;
		for(int i = 100; i < 100+secondSetSize; i++){
			integerMap.put(secondSetSize+i, ""+secondSetSize+i);
		}
		
		assertEquals("After insertion into non-empty HistoryDeletingHashMap, "
				+ "size was incorect.", integerMapMax, integerMap.size());
		
	}
	
	@Test
	public void testOverFull() {

		int integerMapMax = 100;
		HistoryDeletingHashMap<Integer, String> integerMap = 
				new HistoryDeletingHashMap<Integer, String>(integerMapMax);
		
		int firstSetSize = integerMapMax + 50;
		for(int i = 0; i < firstSetSize; i++){
			integerMap.put(i, ""+i);
		}
		
		assertEquals("After insertion into exactly full HistoryDeletingHashMap, "
				+ "size was incorect.", 
				integerMapMax, 
				integerMap.size());

		int secondSetSize = 5;
		for(int i = 200; i < 200+secondSetSize; i++){
			integerMap.put(secondSetSize+i, ""+secondSetSize+i);
		}
		
		assertEquals("After insertion into non-empty HistoryDeletingHashMap, "
				+ "size was incorect.", integerMapMax, integerMap.size());
		
	}

}
