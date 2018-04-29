import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

 private Item[] container;
 private int sizeOfQueue = 0;

 public RandomizedQueue() // construct an empty randomized queue
 {
  container = (Item[]) new Object[1];
 }
 public int size() // return the number of items on the randomized queue
 {
  return sizeOfQueue;
 }
 private void resize(int capacity) {
  Item[] newContainer = (Item[]) new Object[capacity];
  for (int i = 0; i < size(); i++) {
   newContainer[i] = container[i];
  }
  container = newContainer;
 }
 private class RDIterator implements Iterator<Item> {
  private int firstItem = 0;
  private int[] randomIndex = new int[size()];
  public RDIterator()
  {
    for(int i = 0; i < randomIndex.length; i++)
    {
      randomIndex[i] = i;
    }
    StdRandom.shuffle(randomIndex);
  }
  public boolean hasNext() {
   return firstItem < size();
  }
  public Item next() {
   if (!this.hasNext()) {
    throw new NoSuchElementException();
   }
   Item item = container[randomIndex[firstItem]];
   firstItem++;
   return item;
  }
  public void remove() {
   throw new UnsupportedOperationException();
  }
 }

 public boolean isEmpty() // is the randomized queue empty?
 {
  return sizeOfQueue == 0;
 }
 public void enqueue(Item item) // add the item
 {
  if (item == null) throw new IllegalArgumentException();
  if (sizeOfQueue == container.length - 1) resize(2 * container.length);
  container[sizeOfQueue] = item;
  sizeOfQueue++;
 }
 public Item dequeue() // remove and return a random item
 {
  if (this.isEmpty()) {
   throw new NoSuchElementException();
  }
  Item item = container[size() - 1];
  container[size() - 1] = null;
  sizeOfQueue -= 1;
  if (size() >= 0 && size() <= ((container.length) / 4)) resize(container.length / 2);
  return item;
 }
 public Item sample() // return a random item (but do not remove it)
 {
  if (this.isEmpty()) {
   throw new NoSuchElementException();
  }
  Item item = container[0];
  return item;
 }
 public Iterator<Item> iterator() // return an independent iterator over items in random order
 {
  return new RDIterator();
 }
 public static void main(String[] args) // unit testing (optional)
 {
// test here

 }
}