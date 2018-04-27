public class Deque<Item> implements Iterable<Item> {

    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != 0;
        }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove()
        {
            throw UnsupportedOperationException();
        }
    }

    Node first = null;
    Node last = null;

    public Deque()                           // construct an empty deque
    public boolean isEmpty()                 // is the deque empty?
    {
        return (first == null);
    }
    public int size()                        // return the number of items on the deque
    {
        int size = 0;
        for(node : this){ if(node) size++};
        return size;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        oldfirst.prev = first;
    }
    public void addLast(Item item)           // add the item to the end
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        oldfirst.prev = last;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        Node item = first.item;
        first = first.next;
        first.prev = null;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        Node item = last.item;
        last = last.prev;
        last.next = null;
        return item;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    public static void main(String[] args)   // unit testing (optional)
}