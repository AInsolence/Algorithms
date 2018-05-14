import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Solver {
    private final boolean isSolvable;
    private final int movesToSolve;
    private final Node SearchNode;
    private final Stack<Board> solutionPath;

    // Inner class to extend Board class methods
    private class Node implements Comparable<Node>
    {
        public final Board sBoard;
        public final Node predecessor;
        public final int priority;

        public Node(Board _board, Node _predecessor, int _moves)
        {
            sBoard = _board;
            predecessor = _predecessor;
            priority = sBoard.manhattan() + _moves;
        }

        public int compareTo(Node that) {
            if(this.priority < that.priority) return -1;
            if(this.priority > that.priority) return 1;
            else return 0;
        }
    }
    //*************************************************************************//

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if(initial == null) throw new IllegalArgumentException();
        solutionPath = new Stack<Board>();
        Node initNode = new Node(initial, null, 0);
        if(initNode.getBoard().isGoal()){
            isSolvable = true;
            movesToSolve = 0;
            solutionPath.push(startNode.getBoard());// TODO check correctness
        }
        MinPQ<Node> origPQ = new MinPQ<Node>();
        origPQ.insert(initNode);
        // start
        while(true)

        
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return isSolvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return movesToSolve;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if(!isSolvable()) return null;
        Stack<Board> 
        return solutionPath;
    }
    public static void main(String[] args) {
        //
    }
}