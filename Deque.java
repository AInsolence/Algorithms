import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

 private Node first = null;
 private Node last = null;
 private int size = 0;

 private class Node {
  Item item;
  Node next;
  Node prev;
 }

 private class DequeIterator implements Iterator<Item> {
  private Node current = first;

  public boolean hasNext() {
   return current != null;
  }
  public Item next() {
   if (!this.hasNext()) {
    throw new NoSuchElementException();
   }
   Item item = current.item;
   current = current.next;
   return item;
  }
  public void remove() {
   throw new UnsupportedOperationException();
  }
 }

 public Deque() // construct an empty deque
 {
 }
 public boolean isEmpty() // is the deque empty?
 {
  return (first == null);
 }
 public int size() // return the number of items on the deque
 {
  return size;
 }
 public void addFirst(Item newItem) // add the item to the front
 {
  if (newItem == null) throw new IllegalArgumentException();
  Node oldfirst = first;
  first = new Node();
  first.item = newItem;
  first.prev = null;
  first.next = null;
  if (oldfirst != null) {
   first.next = oldfirst;
   oldfirst.prev = first;
  } else {
   last = first;
  }
  size++;
 }
 public void addLast(Item newItem) // add the item to the end
 {
  if (newItem == null) throw new IllegalArgumentException();
  Node oldlast = last;
  last = new Node();
  last.item = newItem;
  last.next = null;
  last.prev = null;
  if (oldlast != null) {
   last.prev = oldlast;
   oldlast.next = last;
  } else {
   first = last;
  }
  size++;
 }
 public Item removeFirst() // remove and return the item from the front
 {
  if (this.size() == 0) throw new NoSuchElementException();
  Node removedItem = first;
  if (first.next == null) {
   first = null;
   last = null;
  } else {
   first = first.next;
   first.prev = null;
  }
  size -= 1;
  return removedItem.item;
 }
 public Item removeLast() // remove and return the item from the end
 {
  if (this.size() == 0) throw new NoSuchElementException();
  Node removedItem = last;
  if (last.prev == null) {
   first = null;
   last = null;
  } else {
   last = last.prev;
   last.next = null;
  }
  size -= 1;
  return removedItem.item;
 }
 public Iterator<Item> iterator() // return an iterator over items in order from front to end
 {
  return new DequeIterator();
 }
 public static void main(String[] args) // unit testing (optional)
 {
//test here
 }
}