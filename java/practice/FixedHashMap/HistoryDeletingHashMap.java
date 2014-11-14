package practice.FixedHashMap;

import java.util.HashMap;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class HistoryDeletingHashMap<K, V>{
	
	public class LinkedEntry<K, V>{
		public LinkedEntry<K, V> prev;
		public LinkedEntry<K, V> next;
		public final K key;
		public final V value;
		
		public LinkedEntry(K newKey, V newValue, LinkedEntry<K, V> nextEntry, LinkedEntry<K, V> prevEntry) {
			this.key = newKey;
			this.value = newValue;
			this.next = nextEntry;
			this.prev = prevEntry;
		}

		@Override
		public String toString(){
			String s = "< ";
			s += prev != null ? prev.value : " -";
			s += ", " + value + ", ";
			s += next != null ? next.value : "- ";
			s += " >";
			return s;
		}
	}
	
	private int maxSize = 10;
	private HashMap<K, LinkedEntry<K, V> > dataMap;
	
	private K headKey = null;
	private LinkedEntry<K, V> headEntry = null;
	private K tailKey = null;
	private LinkedEntry<K, V> tailEntry = null;
	
	public HistoryDeletingHashMap (int maxSize){
		this.maxSize = maxSize;
		this.dataMap = new HashMap<K, HistoryDeletingHashMap<K, V>.LinkedEntry<K, V> >(maxSize);
	}
	
	/**
	 * Put the given value into the hash map, with the given key. 
	 * @param newKey The key of the item to be added
	 * @param newValue The value to be inserted
	 * @return The value that was inserted
	 */
	public V put(K newKey, V newValue){
		while(dataMap.size() >= maxSize){
			this.deleteOldest();
		}
		
		LinkedEntry<K, V> newLinkedValue = new LinkedEntry<K, V>(newKey, newValue, null, this.headEntry);
		this.dataMap.put(newKey, newLinkedValue);
		if(this.headEntry != null) {
			this.headEntry.next = newLinkedValue;
		}
		this.headKey = newKey;
		this.headEntry = newLinkedValue;
		
		if(dataMap.size() == 1){
			this.tailKey = newKey;
			this.tailEntry = newLinkedValue;
		}
		return newValue;
	}

	public LinkedEntry<K, V> remove(K deleteKey){
		LinkedEntry<K, V> deleteEntry = this.dataMap.get(deleteKey);
		if(deleteEntry != null){
			LinkedEntry<K, V> joinHead = deleteEntry.prev;
			LinkedEntry<K, V> joinTail = deleteEntry.next;
			
			if(joinTail != null) joinTail.prev = joinHead;
			if(joinHead != null) joinHead.next = joinTail;
			
			if(deleteKey.equals(this.headKey)) {
				this.headKey = deleteEntry.prev.key;
				this.headEntry = deleteEntry.prev;
			}
			if(deleteKey.equals(this.tailKey)) {
				this.tailKey = deleteEntry.next.key;
				this.tailEntry = deleteEntry.next;
			}
			
			this.dataMap.remove(deleteKey);
			return deleteEntry;
		}
		return null;
	}
	
	private LinkedEntry<K, V> deleteOldest() {
		return this.remove(this.tailKey);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Key -> Value\n");
		sb.append("Size: " + this.dataMap.size() + "\n");
		for(Entry<K, HistoryDeletingHashMap<K, V>.LinkedEntry<K, V> > e : this.dataMap.entrySet()) {
			sb.append("" + e.getKey() + " -> " + e.getValue() + "\n");
		}
		return sb.toString();
	}

	
	
	////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		HistoryDeletingHashMap<Integer, Integer> map = new HistoryDeletingHashMap<Integer, Integer>(10);
		for(int i = 0; i < 10; i++){
			map.put(i, i);
		}
		System.out.println(map.toString());
		
		int offset = 10;
		for(int i = 0; i < 5; i++){
			map.put(offset+i, offset+i);
		}
		System.out.println(map.toString());
		
		map.remove(12);
		System.out.println(map.toString());

		map.remove(7);
		System.out.println(map.toString());
		
	}

	public int size() {
		return dataMap.size();
	}

	public int maxSize() {
		return this.maxSize;
	}

}
