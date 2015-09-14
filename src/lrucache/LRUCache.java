package lrucache;

import java.util.Hashtable;
/* =================================================================

In this LRU implementation, we will try to use an linked list implementation. 

we don't need to keep the time of each key, we can just remove the head of the linked list and when 
update a key, we can just remove it from the linked list and re-append to the head.
 
   ================================================================= */


public class LRUCache {
    /* Private fields 
     * 
     */
	private final int _capacity;
	private final Hashtable _map;
	
	private final LinkedUsage _usage;
	
    public LRUCache(int capacity) {
    	_capacity = capacity;
        _usage = this.new LinkedUsage();
//        _usage = LRUCache.this.new LinkedUsage(); /* or you can do this */
//    	 _usage = new LinkedUsage(); /* or you can do this */
        _map = new Hashtable(capacity);
    }
    
    public int get(int key) {
        if (_map.containsKey(key)) { 
        	_usage.update(key);
        	return (int)_map.get(key);
        }
        
        return -1;
    }
    
    public void set(int key, int value) {
        Entry evicted  = _usage.evict();
        if (evicted != null) {
        	_map.remove(evicted.getKey());
        }
        
        _usage.update(key);
        _map.put(key, value);
    }
    
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(3);
    	cache.set(1, 1);
    	cache.set(2, 2);
    	cache.set(3, 3);
    	cache.set(4,  4);
    	if (-1 == cache.get(1)) { 
    		System.out.println("1 evicted");
    	} else {
    		System.out.println("1 is not evicted");
    	}
    	System.out.println("key 1: " + cache.get(1));
    	System.out.println("key 2: " + cache.get(2));
    	System.out.println("key 3: " + cache.get(3));

    }
    
    
    /*==================================================
     * Private classes
     *==================================================*/
    private class LinkedUsage {
    	/* Private fields 
    	 */
    	private Entry _head;
    	private Entry _tail;
    	private final Hashtable<Integer, Entry> _lookup;
    	private int _length;
    	
    	/* Constructor
    	 */
    	public LinkedUsage() { 
    		_lookup = new Hashtable<Integer, Entry>(LRUCache.this._capacity);
    	}
    	
    	/* Properties
    	 */
    	public Entry getHead() { 
    		return _head;
    	}
    	
    	public void setHead(Entry head) { 
    		_head = head;
    	}
    	
    	public int getLength() {
    		return _length;
    	}
    	
    	public void update(int key) {
    		if (exists(key)) { 
    			Entry entry = _lookup.get(key);
    			if (entry.getPrev() != null) {
    				entry.getPrev().setNext(entry.getNext());
    			} else if (entry.getNext() != null) { 
    				entry.getNext().setPrev(entry.getNext());
    			}
    			
    			if (_tail != null) {
    				_tail.setNext(entry);
    			}
    			
    			_tail = entry;
    			
    			if (_head == null) { 
    				_head = _tail;
    			}
    		} else {
    			Entry entry = new Entry(key);
    			_lookup.put(key, entry);
    			if (_tail != null) { 
    				_tail.setNext(entry);
    			}
    			
    			_tail = entry;
    			
    			if (_head == null) { 
    				_head = _tail;
    			}
    			++_length;
    		}
    	}
    	
    	/* NOTE: Not implemented 
    	 */
    	public Entry evict() { 
    		if (isFull()) {
        		Entry entry = _head;
        		if (_head != null) { 
            		_lookup.remove(_head.getKey());
            		_head = _head.getNext();
            		--_length;
        		}
        		return entry;
    		}
    		
    		return null;
    	}
    	
    	public boolean exists(int key) {
    		return _lookup.containsKey(key); // I am expecting the autoboxing to be working automatically.
    	}
    	
    	/* Private Methods
    	 */
    	private boolean isFull() {
    		return _length == _capacity;
    	}
    }
    
    
    
    private class Entry {
    	/* Private fields 
    	 */
    	private final int _key;
    	private Entry _next;
    	private Entry _prev;
    	
    	/* Constructor
    	 */
    	public Entry(int key) { 
    		_key = key;
    	}

    	/* Properties
    	 */
    	public int getKey() { 
    		return _key;
    	}
    	
    	public Entry getPrev() { 
    		return _prev;
    	}
    	
    	public Entry getNext() { 
    		return _next;
    	}
    	
    	public void setPrev(Entry prev) { 
    		_prev = prev;
    		if (prev != null) {
    			prev._next = this;
    		}
    	}
    	
    	public void setNext(Entry next) { 
    		_next = next;
    		if (next != null) {
    			next._prev = this;
    		}
    	}
    }
}