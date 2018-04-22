/* This is the Percolation class based on WeightedQuickUnionUF class. 
  It provides data-structure and algorithms to use in percolation-problem 
  experiment.

  Public methods:

  void open(int row, int col) - open site in the grid
  boolean isOpen(int row, int col) - return true if site is open
  boolean isFull(int row, int col) - return true if site is full
  int numberOfOpenSites() - return number of open sites
  boolean percolates() - return true if we have path from top to the bottom */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

 private final int gridSize;
 private final WeightedQuickUnionUF elementsArray;
 private boolean[] openElements;
 private int counterOpenElements;

 public Percolation(int x) { 
  // validate input
  if (x <= 0) throw new IllegalArgumentException();
  gridSize = x;
  // +2 mock vertices
  elementsArray = new WeightedQuickUnionUF(gridSize * gridSize + 2); 

  openElements = new boolean[gridSize * gridSize + 2];
  for (int i = 0; i < gridSize * gridSize + 2; i++) {
   openElements[i] = false;
  }
  // create and Full mock nodes
  openElements[0] = true;
  openElements[gridSize * gridSize + 1] = true;
  // init counter of open sites
  counterOpenElements = 0; 
 }
 // open site (row, col) if it is not open already
 public void open(int row, int col) { 
  if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
   throw new IllegalArgumentException();
  }
  int currentNode = row * (gridSize) + col - gridSize;
  // handle with case, when grid size == 1
  if (gridSize == 1 && currentNode == 1) {
   elementsArray.union(0, currentNode);
   elementsArray.union(currentNode, gridSize * gridSize + 1);
   counterOpenElements += 1;
   return;
  }

  if (openElements[currentNode]) return;
  openElements[currentNode] = true;
  counterOpenElements += 1;
  checkNeighbors(row, col);
 }

 public boolean isOpen(int row, int col) {
  if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
   throw new IllegalArgumentException();
  }
  int currentNode = row * (gridSize) + col - gridSize;
  return openElements[currentNode];
 }

 public boolean isFull(int row, int col) {
  if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
   throw new IllegalArgumentException();
  }
  int currentNode = row * (gridSize) + col - gridSize;
  return elementsArray.connected(currentNode, 0);
 }

 public int numberOfOpenSites() {
  return counterOpenElements;
 }

 public boolean percolates() {
  return elementsArray.connected(0, gridSize * gridSize);
 }

 private void checkNeighbors(int row, int col) {
  int currentNode = row * (gridSize) + col - gridSize;

  if (row == 1) {
   elementsArray.union(currentNode, 0);
   checkBottomNode(currentNode);
   return;
  }
  if (row == gridSize) {
   elementsArray.union(currentNode, gridSize * gridSize);
   checkTopNode(currentNode);
   return;
  }
  if (col == 1) {
   checkTopNode(currentNode);
   checkBottomNode(currentNode);
   checkRightNode(currentNode);
   return;
  }
  if (col == gridSize) {
   checkTopNode(currentNode);
   checkBottomNode(currentNode);
   checkLeftNode(currentNode);
   return;
  }
  // if element not at the edge of the grid - check all neighbors
  checkTopNode(currentNode);
  checkBottomNode(currentNode);
  checkLeftNode(currentNode);
  checkRightNode(currentNode);
  if (elementsArray.connected(currentNode, 0)) openElements[currentNode] = true;
 }

 private void checkTopNode(int i) {
  if (openElements[i - gridSize]) elementsArray.union(i, i - gridSize);
 }

 private void checkBottomNode(int i) {
  if (openElements[i + gridSize]) elementsArray.union(i, i + gridSize);
 }

 private void checkLeftNode(int i) {
  if (openElements[i - 1]) elementsArray.union(i, i - 1);
 }

 private void checkRightNode(int i) {
  if (openElements[i + 1]) elementsArray.union(i, i + 1);
 }
 public static void main(String[] args) {
    System.out.println("Hello World!");
 }
}