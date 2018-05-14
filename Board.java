import java.util.Stack;

public class Board {

    private final int gridSize;
    private final int[] grid;
    private int emptyBlock;
    private int hammingKoeff;
    private int manhattanKoeff;

    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {
        if(blocks == null) throw new IllegalArgumentException();
        gridSize = blocks.length;
        grid = new int[gridSize*gridSize];
        int position = 0;
        for(int row = 0; row < blocks.length; row++){
            for(int column = 0; column < blocks[0].length; column++){
                grid[position] = blocks[row][column];
                if(grid[position] != 0 && grid[position] != position + 1){
                    hammingKoeff++;
                    manhattanKoeff += calcManhattan(grid[position], row, column);
                }
                if(grid[position] == 0) emptyBlock = position;
                position++;
            }
        }
    }
    private Board(int[] blocks, int _gridSize)
    {
        if(blocks == null) throw new IllegalArgumentException();
        gridSize = _gridSize;
        grid = new int[gridSize*gridSize];
        for(int i = 0; i < blocks.length; i++){
                grid[i] = blocks[i];
                if(grid[i] != 0 && grid[i] != i + 1){
                    hammingKoeff++;
                    manhattanKoeff += calcManhattan(grid[i], i/gridSize, i%gridSize);
                }
                if(grid[i] == 0) emptyBlock = i;
        }
    }
    private void swap(int[] array, int i, int k){
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }
    private int[] makeCopy(int[] newGrid){
        int[] twinGrid = new int[gridSize*gridSize];
        for(int i = 0; i < newGrid.length; i++){
                twinGrid[i] = newGrid[i];
        }
        return twinGrid;
    }
    private Board rightNeighbor(int emptyBlock){
        int[] right = makeCopy(grid);
        swap(right, emptyBlock, emptyBlock + 1);
        Board rNeighbor = new Board(right, gridSize);
        return rNeighbor;
    }
    private Board leftNeighbor(int emptyBlock){
        int[] left = makeCopy(grid);
        swap(left, emptyBlock, emptyBlock - 1);
        Board lNeighbor = new Board(left, gridSize);
        return lNeighbor;
    }
    private Board topNeighbor(int emptyBlock){
        int[] top = makeCopy(grid);
        swap(top, emptyBlock, emptyBlock - gridSize);
        Board tNeighbor = new Board(top, gridSize);
        return tNeighbor;
    }
    private Board bottomNeighbor(int emptyBlock){
        int[] bottom = makeCopy(grid);
        swap(bottom, emptyBlock, emptyBlock + gridSize);
        Board bNeighbor = new Board(bottom, gridSize);
        return bNeighbor;
    }
    private int calcManhattan(int value, int row, int column){
        int rightRow = (value - 1)/gridSize;
        int rightCol = (value - 1)%gridSize;
        int distance = Math.abs(rightRow - row) + Math.abs(rightCol - column);
        return distance;
    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension n
    {
        return gridSize;
    }
    public int hamming()                   // number of blocks out of place
    {
        return hammingKoeff;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return manhattanKoeff;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        if(grid[grid.length - 1] != 0) return false;    // check last element
        for(int i = 0; i < grid.length - 1; i++){       // check decreasing order of elements
            if(grid[i] != i + 1) return false;
        }
        return true;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    { 
        int[] twinGrid = makeCopy(grid);
        Stack<Integer> toSwap = new Stack<Integer>();
        for(int i = 0; i < twinGrid.length; i++){
            if(i != emptyBlock){
                if(twinGrid[i] != i + 1) toSwap.push(i);
            } 
        }
        int first = 0;
        int second = 1;
        if(!toSwap.empty()){
            if(toSwap.size() > 1){
                first = toSwap.pop();
                second = toSwap.pop();
            }
            else{
                first = toSwap.pop();
                if(second == emptyBlock){
                    if(first != second + 1) second++;
                    else second +=2;
                }
            } 
            swap(twinGrid, first, second);
        }
        else if(twinGrid[0] != 0 && twinGrid[1] != 0){   // change 2 elements != 0
            swap(twinGrid, 0, 1);
        }
        else swap(twinGrid, 2, 3);
        
        Board twinBoard = new Board(twinGrid, gridSize); // create twin board
        return twinBoard;
    }
    public boolean equals(Object _other)        // does this board equal _other?
    {
        if (_other == this) return true;
        if (_other == null) return false;
        if (_other.getClass() != this.getClass()) return false;
        Board other = (Board) _other;
        if(this.gridSize != other.gridSize) return false;
        for(int i = 0; i < grid.length; i++){
            if(this.grid[i] != other.grid[i]) return false;
        }
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Stack<Board> boardNeighbors = new Stack<>();

        int blockRow = emptyBlock/gridSize;
        int blockCol = emptyBlock%gridSize;

        if(blockRow == 0){  // first row
            if(blockCol == 0){    // first column
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(bottomNeighbor(emptyBlock));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(bottomNeighbor(emptyBlock));
            }
            else{
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(bottomNeighbor(emptyBlock));
            }
        }
        else if(blockRow == gridSize - 1){  // last row
            if(blockCol == 0){    // first column
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
            else{
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
        }
        else{
            if(blockCol == 0){    // first column
                boardNeighbors.push(bottomNeighbor(emptyBlock));
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(bottomNeighbor(emptyBlock));
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
            else{ // in the center
                boardNeighbors.push(bottomNeighbor(emptyBlock));
                boardNeighbors.push(rightNeighbor(emptyBlock));
                boardNeighbors.push(leftNeighbor(emptyBlock));
                boardNeighbors.push(topNeighbor(emptyBlock));
            }
        }

        return boardNeighbors;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(gridSize + "\n");
        int position = 0;
        for (int i = 0; i < grid.length; i++) {
                s.append(String.format("%2d ", grid[i]));
                position++;
                if(position%gridSize == 0 && position != grid.length) s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        int gridSize = 5;
        int[][] testGrid = new int[gridSize][gridSize];
        int position = 1;
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++){
                testGrid[row][column] = position;
                position++;
            }
        }
        //testGrid[0][0] = 24;
        testGrid[4][4] = 0;
        Board myBoard = new Board(testGrid);
        System.out.println(myBoard.toString());
        System.out.println(myBoard.isGoal());
        //System.out.println(myBoard.hamming());
        //System.out.println(myBoard.manhattan());
        Board twinMyBoard = myBoard.twin();
        System.out.println(twinMyBoard.toString());
        /*for (Board b : twinMyBoard.neighbors()) {
            System.out.println(b.toString());
        }*/
    }
}