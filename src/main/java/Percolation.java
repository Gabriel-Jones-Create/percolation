import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/******************************************************************************
 *  Name:    Kevin Wayne
 *  Login:   wayne
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner Login:   N/A
 *  Partner Precept: N/A
 * 
 *  Compilation:  javac-algs4 Percolation.java
 *  Execution:    java-algs4 Percolation
 *  Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *
 *  Description:  Modeling Percolation like a boss. woot. woot.
 ******************************************************************************/
public class Percolation {
  
   private boolean[][] grid; // true is open false is closed
   private int openSites;
   private WeightedQuickUnionUF percolation;
	
   public Percolation(int n)  {
   // TODO: create n-by-n grid, with all sites blocked
	   percolation = new WeightedQuickUnionUF(n * n + 2);
	   grid = new boolean[n][n];
   }

   public void open(int row, int col) {
    // TODO: open site (row, col) if it is not open already
	   if(!grid[row-1][col]) {
	   grid[row-1][col] = true;
	   percolation.union(row, col);;
	   openSites++;
	   }
   }
   public boolean isOpen(int row, int col) {
     // TODO: is site (row, col) open?
     return grid[row-1][col];
   }
   public boolean isFull(int row, int col) {
     // TODO: is site (row, col) full?
     return !grid[row-1][col];
   }
   public int numberOfOpenSites() {
     // TODO: number of open sites
     return openSites;
   }
   
   public boolean percolates() {
     // TODO: does the system percolate?
     return false;
   }

   public static void main(String[] args) {
     // TODO: test client (optional)
   }
}
