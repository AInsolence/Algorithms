import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Solver {
    private boolean isSolvable;
    private int movesToSolve;
    private final Stack<Board> path;

    // Inner class to extend Board class methods
    private class Node implements Comparable<Node>
    {
        private final Board sBoard;
        private final Node predecessor;
        private final int moves;
        private final int priority;

        public Node(Board _board, Node _predecessor, int _moves)
        {
            sBoard = _board;
            predecessor = _predecessor;
            moves = _moves;
            priority = sBoard.manhattan() + _moves;
        }

        public int compareTo(Node that) {
            if(this.priority < that.priority) return -1;
            if(this.priority > that.priority) return 1;
            else return 0;
        }
        public Board getBoard(){
            return sBoard;
        }
        public Node getPredecessor(){
            return predecessor;
        }
        public int getMoves(){
            return moves;
        }
    }
    //*************************************************************************//

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if(initial == null) throw new IllegalArgumentException();
        path = new Stack<Board>();
        
        Node start = new Node(initial, null, 0);

        if(start.getBoard().isGoal()){
            isSolvable = true;
            movesToSolve = 0;
            path.push(start.getBoard());
        }
        else{
            Node origin = new Node(initial, null, 0);
            Node twin = new Node(initial.twin(), null, 0);
            MinPQ<Node> originPQ = new MinPQ<>();
            MinPQ<Node> twinPQ = new MinPQ<>();
            originPQ.insert(start);
            twinPQ.insert(twin);
            //
            origin = originPQ.delMin();
            twin = twinPQ.delMin();
            for(Board neighbor : origin.getBoard().neighbors()){
                originPQ.insert(new Node(neighbor, origin, origin.getMoves() + 1));
            }
            for(Board neighbor : twin.getBoard().neighbors()){
                twinPQ.insert(new Node(neighbor, twin, twin.getMoves() + 1));
            }
            //
            
            boolean solvable = false;
            while(!solvable){
                // origin board part
                origin = originPQ.delMin();
                if(origin.getBoard().isGoal()){
                    isSolvable = true;
                    Stack<Board> invertPath = new Stack<>();    // auxillary stack
                    while(origin.getBoard() != start.getBoard()){  // reproduce all nodes
                        invertPath.push(origin.getBoard());
                        origin = origin.getPredecessor();
                    }
                    path.push(start.getBoard());
                    while(!invertPath.empty()) path.push(invertPath.pop()); // invert to path
                    movesToSolve = path.size() - 1;
                    /*for(Board result : path){
                        System.out.println(result.toString());
                    }*/
                    solvable = true;
                    break;
                }
                else{
                    for(Board neighbor : origin.getBoard().neighbors()){
                        if(!neighbor.equals(origin.getPredecessor().getBoard())){
                            originPQ.insert(new Node(neighbor, origin, origin.getMoves() + 1));
                        }
                    }
                }
                // twin board part
                twin = twinPQ.delMin();
                if(twin.getBoard().isGoal()){
                    isSolvable = false;
                    movesToSolve = -1;
                    solvable = true;
                    break;
                }
                else{
                    for(Board neighbor : twin.getBoard().neighbors()){
                        if(!neighbor.equals(twin.getPredecessor().getBoard())){
                            twinPQ.insert(new Node(neighbor, twin, twin.getMoves() + 1));
                        }
                    }
                }
            }
        }
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return isSolvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if(!isSolvable()) return -1;
        return movesToSolve;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if(!isSolvable()) return null;
        return path;
    }
    public static void main(String[] args) {
        //
    }
}