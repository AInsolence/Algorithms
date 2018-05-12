import java.util.Stack;

public class Board {

    private final int gridSize;
    private final int[] grid;
    private final int hammingKoeff;
    private final int manhattanKoeff;

    private void swap(int[][] twoDarray, int firstRow, int fistColumn, int secondRow, int secondColumn){
        int temp = twoDarray[firstRow][fistColumn];
        twoDarray[firstRow][fistColumn] = twoDarray[secondRow][secondColumn];
        twoDarray[secondRow][secondColumn] = temp;
    }
    private int[][] makeCopy(int[] newGrid){
        // create copy of the grid
        int[][] twinGrid = new int[gridSize][gridSize];
        int position = 0;
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++){
                twinGrid[row][column] = newGrid[position];
                position++;
            }
        }
        return twinGrid;
    }
    private Board rightNeighbor(int blockRow, int blockCol){
        int[][] right = makeCopy(grid);
        swap(right, blockRow, blockCol, blockRow, blockCol + 1);
        Board rNeighbor = new Board(right);
        return rNeighbor;
    }
    private Board leftNeighbor(int blockRow, int blockCol){
        int[][] left = makeCopy(grid);
        swap(left, blockRow, blockCol, blockRow, blockCol - 1);
        Board lNeighbor = new Board(left);
        return lNeighbor;
    }
    private Board topNeighbor(int blockRow, int blockCol){
        int[][] top = makeCopy(grid);
        swap(top, blockRow, blockCol, blockRow - 1, blockCol);
        Board tNeighbor = new Board(top);
        return tNeighbor;
    }
    private Board bottomNeighbor(int blockRow, int blockCol){
        int[][] bottom = makeCopy(grid);
        swap(bottom, blockRow, blockCol, blockRow + 1, blockCol);
        Board bNeighbor = new Board(bottom);
        return bNeighbor;
    }
    private int calcManhattan(int value, int row, int column){
        int rightRow = (value - 1)/gridSize;
        int rightCol = (value - 1)%gridSize;
        int distance = Math.abs(rightRow - row) + Math.abs(rightCol - column);
        System.out.println("value = " + value);
        System.out.println("current row&col = " + row + " " + column);
        System.out.println("right row&col = " + rightRow + " " + rightCol);
        System.out.println("distance = " + distance);
        return distance;
    }

    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {
        if(blocks == null) throw new IllegalArgumentException();
        gridSize = blocks.length;
        grid = new int[gridSize*gridSize];

        int tempHammKoeff = 0;
        int tempManKoeff = 0;
        int position = 0;

        for(int row = 0; row < blocks.length; row++){
            for(int column = 0; column < blocks[0].length; column++){
                grid[position] = blocks[row][column];
                if(grid[position] != 0 && grid[position] != position + 1){
                    tempHammKoeff++;
                    tempManKoeff += calcManhattan(grid[position], row, column);
                } 
                position++;
            }
        }
        hammingKoeff = tempHammKoeff;
        manhattanKoeff = tempManKoeff;
 
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
        int[][] twinGrid = makeCopy(grid);
        // change 2 elements != 0
        if(twinGrid[0][0] != 0 && twinGrid[0][1] != 0){
            swap(twinGrid, 0, 0, 0, 1);
        }
        else swap(twinGrid, 1, 0, 1, 1);
        Board twinBoard = new Board(twinGrid); // create twin board
        return twinBoard;
    }
    public boolean equals(Object _other)        // does this board equal _other?
    {
        if (_other == this) return true;
        if (_other == null) return false;
        if (_other.getClass() != this.getClass()) return false;
        Board other = (Board) _other;
        for(int i = 0; i < grid.length; i++){
            if(this.grid[i] != other.grid[i]) return false;
        }
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Stack<Board> boardNeighbors = new Stack<>();
        int emptyBlock = 0;
        for(int i = 0; i < grid.length; i++){
            if(grid[i] == 0){
                emptyBlock = i;
            }
        }
        int blockRow = emptyBlock/gridSize;
        int blockCol = emptyBlock%gridSize;

        if(blockRow == 0){  // first row
            if(blockCol == 0){    // first column
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
            }
            else{
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
            }
        }
        else if(blockRow == gridSize - 1){  // last row
            if(blockCol == 0){    // first column
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
            }
            else{
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
            }
        }
        else{
            if(blockCol == 0){    // first column
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
            }
            else if(blockCol == gridSize - 1){    // last column
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
            }
            else{ // in the center
                boardNeighbors.push(bottomNeighbor(blockRow, blockCol));
                boardNeighbors.push(rightNeighbor(blockRow, blockCol));
                boardNeighbors.push(leftNeighbor(blockRow, blockCol));
                boardNeighbors.push(topNeighbor(blockRow, blockCol));
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
        int position = 0;
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++){
                testGrid[row][column] = position;
                position++;
            }
        }
        //testGrid[0][0] = 24;
        //testGrid[4][4] = 0;
        Board myBoard = new Board(testGrid);
        System.out.println(myBoard.toString());
        System.out.println(myBoard.hamming());
        System.out.println(myBoard.manhattan());
        /*Board twinMyBoard = myBoard.twin();
        System.out.println(twinMyBoard.toString());
        for (Board b : twinMyBoard.neighbors()) {
            System.out.println(b.toString());
        }*/
    }
}