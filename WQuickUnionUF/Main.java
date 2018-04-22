
public class Main {

    public static void main(String[] args) {

        System.out.println("Weighted Quick Union Algorithm Test: ");
        WQuickUnionUF Sequence = new WQuickUnionUF(10);
        Sequence.isConnected(1, 0);
        Sequence.Union(1,0);
        Sequence.isConnected(1, 0);
        Sequence.Union(0, 2);
        Sequence.Union(1, 3);
        Sequence.Union(3, 4);
        Sequence.Union(4, 5);
        Sequence.Union(6, 3);
        Sequence.Union(7, 8);
        Sequence.Union(8, 6);
        Sequence.Union(8, 9);
        Sequence.isConnected(3, 7);
        Sequence.ShowTree();
        Sequence.AllElementsConnected();
        System.out.println("max 1 = " + Sequence.GetMaxInTree(1));
        System.out.println("max 7 = " + Sequence.GetMaxInTree(7));
        System.out.println("max 0 = " + Sequence.GetMaxInTree(0));
        System.out.println("max 9 = " + Sequence.GetMaxInTree(9));
        System.out.println("max 10 = " + Sequence.GetMaxInTree(10));

      
        int[] init = new int[]{0, 0, 0, 0, 1, 1, 8, 8, 6, 6};
        WQuickUnionUF Sequence_2 = new WQuickUnionUF(init);
        Sequence_2.isConnected(1, 2);
        Sequence_2.AllElementsConnected();
    }
}

class WQuickUnionUF
{
    private int[] id;
    private int[] sz;
    private int[] maxInTree;
    private int MaxTree = 0;// store the biggest tree in the forest

    //Constructor
    WQuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        maxInTree = new int[N];
        for (int i = 0; i < N; i++ )
        {
            id[i] = maxInTree[i] = i;
            sz[i] = 1;
            //System.out.println(id[i]);
        }
    }
  
    WQuickUnionUF(int[] N)
    {
        if (N != null){
            id = N;
            sz = new int[N.length];
            for (int i = 0; i < N.length; i++ )
            {
                sz[i] = 1;
                //System.out.println(id[i]);
            }
        }
    }

    //find the root of the element
    private int root(int i)
    {
        int Input = i;
        while (i != id[i]) {
            //flattening the tree
            id[i] = id[id[i]];
            i = id[i];
        }
        int Root = i;
        //fast path compression
        while (Input != Root) {
            int NextElement = id[Input];
            id[Input] = Root;
            Input = NextElement;
        }
        return Root;
    }

    //helper method to AllElementsConnected()
    private void CheckMAxTree(int newRoot)
    {//set root of the tree as root of the maximum tree in the forest
        if (sz[newRoot] > MaxTree) MaxTree = newRoot;
    }
  
    public void ShowTree()
    {//test method
        for (int number : id) System.out.print(number + " ");
        System.out.println("\n");
    }

    public void isConnected(int p, int q)
    {//checking the connection between two elements of the sequence
        if (root(p) == root(q)) System.out.println(p + " and " + q + " are connected\n");
        else System.out.println(p + " and " + q + " are NOT connected\n");
    }

    public void Union(int p, int q)
    {
        int firstNodeRoot = root(p);
        int secondNodeRoot = root(q);
        if (firstNodeRoot == secondNodeRoot) return;//almost in the same tree
        //balance by linking root of smaller tree to root of larger tree
        if (sz[firstNodeRoot] < sz[secondNodeRoot]){
            id[firstNodeRoot] = secondNodeRoot;
            sz[secondNodeRoot] += sz[firstNodeRoot];
            CheckMAxTree(secondNodeRoot);//check if now it is a largest tree in the forest
            //set the maximum element in new tree
            maxInTree[secondNodeRoot] = NewMaxInTree(maxInTree[firstNodeRoot], maxInTree[secondNodeRoot]);
        }
        else {
            id[secondNodeRoot] = firstNodeRoot;
            sz[firstNodeRoot] += sz[secondNodeRoot];
            CheckMAxTree(firstNodeRoot);//check if now it is a largest tree in the forest
            //set the maximum element in new tree
            maxInTree[firstNodeRoot] = NewMaxInTree(maxInTree[firstNodeRoot], maxInTree[secondNodeRoot]);
        }
    }

    //helper function to find maximum element in the tree for union method
    private int NewMaxInTree(int firstNodeMax, int secondNodeMax)
    {
        return (firstNodeMax > secondNodeMax) ? firstNodeMax : secondNodeMax;
    }

    public int GetMaxInTree(int i)
    {
        if (i < id.length) return maxInTree[root(i)];
        else {
            System.out.println("There is no element = " + i + " in the array!");
            return -1;
        }
    }

    public void Remove(int i)
    {

    }

    public boolean AllElementsConnected()
    {
        if (sz[MaxTree] < id.length)
        {
            System.out.println("Not all elements are connected");
            return false;
        }
        System.out.println("All elements are connected");
        return true;
    }
}