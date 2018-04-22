/* This is the PercolationStats program based on the Percolation class as 
  data structure to store percolation grid elements. 
  It provides logic to conduct 'T' experiments of percolation-problem with
  the n-by-n size grid and find sample mean, standard deviation 
  and a 95% confidence interval of percolation threshold. 

  Public methods:

  public double mean()         - returns sample mean of percolation threshold
  public double stddev()       - returns sample standard deviation percolation threshold
  public double confidenceLo() - returns low  endpoint of 95% confidence interval
  public double confidenceHi() - returns high endpoint of 95% confidence interval

  NOTE: you can start this programm from console with arguments (n, T) */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

 private int[] trialsResults;
 private final int gridSize;
 private final double confidence95;

 public PercolationStats(int n, int trials) { 
  if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
  gridSize = n;
  trialsResults = new int[trials];
  confidence95 = 1.96;
  startExperiment();
 }
 public double mean() {
  return StdStats.mean(trialsResults) / (gridSize * gridSize);
 }
 public double stddev() {
  return StdStats.stddev(trialsResults) / (gridSize * gridSize);
 }
 public double confidenceLo() {
  return (mean() - confidence95 * stddev() / Math.sqrt(trialsResults.length));
 }
 public double confidenceHi() {
  return (mean() + confidence95 * stddev() / Math.sqrt(trialsResults.length));
 }
 private void startExperiment() {
  Percolation perc;
  for (int i = 0; i < trialsResults.length; i++) {
   perc = new Percolation(gridSize); 
   while (!perc.percolates()) {
    int row = StdRandom.uniform(gridSize) + 1;
    int column = StdRandom.uniform(gridSize) + 1;
    perc.open(row, column);
   }
   trialsResults[i] = perc.numberOfOpenSites();
   perc = null;
  }
 }
 public static void main(String[] args) {
  if (args.length == 2) {
   PercolationStats sample = new PercolationStats(
    Integer.parseInt(args[0]),
    Integer.parseInt(args[1]));
   System.out.println("sample mean: " + sample.mean());
   System.out.println("Standard deviation: " + sample.stddev());
   System.out.println("ConfidenceLo: " + sample.confidenceLo());
   System.out.println("ConfidenceHi: " + sample.confidenceHi());
  } else {
   System.out.println("You do not provide arguments, loading standard sample...");
   PercolationStats sample = new PercolationStats(100, 30);
   System.out.println("sample mean: " + sample.mean());
   System.out.println("Standard deviation: " + sample.stddev());
   System.out.println("ConfidenceLo: " + sample.confidenceLo());
   System.out.println("ConfidenceHi: " + sample.confidenceHi());
  }
 }
}