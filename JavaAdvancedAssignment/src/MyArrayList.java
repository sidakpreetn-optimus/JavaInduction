import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> {

	private Object[] array;

	private int size;

	private int initialCapacity = 10;

	/**
	 * Constructs List with size 10
	 */
	MyArrayList() {
		array = new Object[initialCapacity];
	}

	/**
	 * Constructs List with specified capacity
	 * 
	 * @param initialCapacity
	 */
	MyArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			array = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			array = new Object[initialCapacity];
		} else {
			throw new IllegalArgumentException("Illegal Arguements");
		}
	}

	/**
	 * Constructs List with a Collection
	 * 
	 * @param c
	 */
	MyArrayList(Collection<? extends T> c) {
		array = new Object[c.size()];
		array = c.toArray();
		size = c.size();
	}

	/**
	 * Inserts an element in the List at the end by default
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T t) {
		// Checks if list is full or not
		if (array[array.length - 1] != null) {
			array = extendLength(array);
		}
		if(t==null) {
			t=(T) "null";
		}
		if (array[0] == null) {
			array[0] = t;
		} else {
			array[size] = t;
		}
		size++;
		return true;
	}

	/**
	 * Inserts an element in the List at specified position
	 * 
	 * @param t
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void add(T t, int index) {
		// Checks if list is full or not
		if (array[array.length - 1] != null) {
			array = extendLength(array);
		}
		if(t==null) {
			t=(T) "null";
		}
		indexCheckForAdd(index);
		// Shifts the elements one by one to the right
		for (int i = array.length - 1; i >= index; i--) {
			if (i == 0)
				break;
			array[i] = array[i - 1];
		}
		array[index] = t;
		size++;
	}

	/**
	 * Returns the element at specified position
	 * 
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	T get(int index) {
		indexCheck(index);
		if(array[index]=="null") {
			return null;
		}
		else {
			return (T) array[index];
		}
	}

	/**
	 * Returns size of the List
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes an element through index
	 * 
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		indexCheck(index);
		T oldValue=(T) array[index];
		for(int i=index;i<size;i++) {
			array[i]=array[i+1];
		}
		array[size-1]=null;
		size--;
		return oldValue;
	}
	
	/**
	 * Removes an element by its value
	 * 
	 * @param o
	 * @return
	 */
	public boolean remove(Object o) {
		for(int i=0;i<size-1;i++) {
			if(array[i]==o||array[i]=="null") {
				this.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes all occurences of elements of collection from the list
	 * 
	 * @param c
	 * @return
	 */
	public boolean removeAll(Collection<?> c) {
		boolean status=false;
		for(int i=0;i<c.size();i++){
			for(int j=0;j<size;j++) {
				if(c.contains(array[j])||array[j]=="null") {
					this.remove(j);
					status=true;
				}
			}
		}
		return status;
	}
	/**
	 * Adds a collection to the List in the end
	 * 
	 * @param c
	 * @return
	 */
	public boolean addAll(Collection<? extends T> c) {
		Object[] temp = c.toArray();
		// Checks if list is full or not
		if (array.length - size != temp.length) {
			array = extendLength(array);
		}
		System.arraycopy(temp, 0, array, size, temp.length);
		size += temp.length;
		return true;
	}

	/**
	 * Checks if the List is empty or not
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all the elements from the List
	 */
	public void clear() {
		// assign null to each element through a loop
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}

		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// Overridden function for displaying elements
		String display = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				display += "  "+array[i];
			}
		}

		return "[" + display + "  ]";
	}

	/**
	 * Checks if the index is in the range of List
	 * 
	 * @param index
	 */
	private void indexCheck(int index) {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException("Range Exceeded");
		}
	}

	/**
	 * Checks if the index is in the range of List specifically for add()
	 * function
	 * 
	 * @param index
	 */
	private void indexCheckForAdd(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Range Exceeded");
		}
	}

	/**
	 * Extends the length of the List by 10
	 * 
	 * @param array
	 * @return
	 */
	private Object[] extendLength(Object[] array) {
		return Arrays.copyOf(array, array.length + 10);
	}
}
