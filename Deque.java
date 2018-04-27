import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    Node first = null;
    Node last = null;

    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    public class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }
        public Item next()
        {
            if(this.hasNext() == false){throw new NoSuchElementException();};
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public Deque()                           // construct an empty deque
    {
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return (first == null);
    }
    public int size()                        // return the number of items on the deque
    {
        int size = 0;
        for(Iterator<Item> i = this.iterator(); i.hasNext(); i.next()) size++;
        return size;
    }
    public void addFirst(Item newItem)          // add the item to the front
    {
        if(newItem == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = newItem;
        first.prev = null;
        first.next = null;
        if(oldfirst != null)
        {
            first.next = oldfirst;        
            oldfirst.prev = first;
        }
        else 
        {
           last = first;
        }
    }
    public void addLast(Item newItem)           // add the item to the end
    {
        if(newItem == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = newItem;
        last.next = null;
        last.prev = null;
        if(oldlast != null)
        {
            last.prev = oldlast;
            oldlast.next = last;
        }
        else 
        {
            first = last;
        }
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if(this.size() == 0) throw new NoSuchElementException();
        Node removedItem = first;
        if(first.next == null)
        {
            first = null;
            last = null;
        } 
        else
        {
            first = first.next;
            first.prev = null;
        }
        return removedItem.item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(this.size() == 0) throw new NoSuchElementException();
        Node removedItem = last;
        if(last.prev == null)
        {
            first = null;
            last = null;
        } 
        else
        {
            last = last.prev;
            last.next = null;
        }
        return removedItem.item;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        System.out.println("Hello from Deque!");
        Deque<Integer> MyDeque = new Deque<>();
        Iterator<Integer> MyIter = MyDeque.iterator();
        System.out.println("Has next?: " + MyIter.hasNext());
        MyDeque.addFirst(10);
        MyIter = MyDeque.iterator();
        System.out.println("Has next?: " + MyIter.hasNext());
        MyDeque.addFirst(20);
        System.out.println("Has next?: " + MyIter.hasNext());
        System.out.println("next work?: " + MyIter.next());
        MyDeque.addFirst(30);
        if (MyDeque.removeFirst() != 30) System.out.println("Test 1 failed. removeFirst()");
        if (MyDeque.size() != 2) System.out.println("Test 2 failed. Size()");
        if (MyDeque.removeFirst() != 20) System.out.println("Test 3 failed. removeFirst()");
        if (MyDeque.removeFirst() != 10) System.out.println("Test 4 failed. removeFirst()");
        if (MyDeque.size() != 0) System.out.println("Test 5 failed. Size()");
        MyDeque.addLast(40);
        MyDeque.addLast(50);
        MyDeque.addLast(60);
        System.out.println(MyDeque.removeFirst());
        System.out.println(MyDeque.removeFirst());
        if (MyDeque.removeFirst() != 60) System.out.println("Test 6 failed. removeLast()");
        System.out.println(MyDeque.isEmpty());
        MyDeque.addLast(60);
        System.out.println(MyDeque.isEmpty());
        //MyDeque.addLast(null);
        //MyDeque.addFirst(null);
        MyDeque.addLast(33);
        MyDeque.addLast(44);
        MyDeque.addLast(55);
        MyDeque.addLast(66);
        MyDeque.addLast(77);
        MyDeque.addLast(88);
        MyDeque.addLast(99);
        MyDeque.addLast(100);
        MyDeque.addLast(200);
        MyDeque.addLast(300);
        MyDeque.addLast(400);        

        for (Iterator<Integer> Iter = MyDeque.iterator(); Iter.hasNext();)
        {
            System.out.println(MyDeque.removeFirst());
            Iter.next();
        }
    }
}