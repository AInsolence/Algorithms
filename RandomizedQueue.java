//usea cast Item[] a = (Item[]) new Object[1];
//import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] container;
	private sizeOfQueue = 0;

	public RandomizedQueue()                 // construct an empty randomized queue
    {
    	container = (Item[]) new Object[1];
    }

	private resize(boolean increase)
	{
		int capacity = container.length;
		
		if(increase == true) capacity = capacity * 2;
		else capacity = capacity/2;

		Item[] newContainer = (Item[]) new Object[capacity];
		for(int i = 0; i < size(); i++)
		{
			newContainer[i] = container[i];
			container = newContainer;
		}
	}
	public class RDIterator implements Iterator<Item>
    {
        private int current = 0;

        public boolean hasNext()
        {
            return current != null;
        }
        public Item next()
        {
            if(this.hasNext() == false){throw new NoSuchElementException();};
            Item item = container[current];
            current = current++;
            return item;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isEmpty()                 // is the randomized queue empty?
    {
    	return sizeOfQueue == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
    	return sizeOfQueue;
    }
    public void enqueue(Item item)           // add the item
    {
    	if(sizeOfQueue == container.length - 1) resize(true);
    	container[sizeOfQueue++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
    	shuffle(container);
    	Item item = container[sizeOfQueue];
    	container[sizeOfQueue] = null;
    	size -= 1;
    	if(sizeOfQueue < (container.length - 1)/4) resize(false);
    	return item;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
    	shuffle(container);
    	Item item = container[sizeOfQueue];
    	return item;
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
    	return new RDIterator<Item>();
    }
    public static void main(String[] args)   // unit testing (optional)
}