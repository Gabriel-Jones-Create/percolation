import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Represents Percolation Grid Object square. Blocks that are <code>true</code>
 * are open and blocks that are <code>false</code> are closed.
 * 
 * 
 * @author gabrieljones
 *
 */
public class Percolation {

	private boolean[][] grid; // 2d grid of boolean variables that determine the board

	private WeightedQuickUnionUF percolation; // 1d representation of 2d grid

	private WeightedQuickUnionUF isFullUF; // 1d representation of 2d grid

	private final int TOP; // integer value of top most block

	private final int BOTTOM; // integer value of bottom most block

	private int sideSize; // size of a side of the board

	private int totalOpen; // number of open blocks in the grid

	/**
	 * 
	 * Constructs Percolation Object with a grid that is n by n tiles
	 * 
	 * @param n side size of percolation grid
	 */
	public Percolation(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Invalid argumenet. Should be greater than 0.");
		}
		sideSize = n;
		int totalEle = n * n + 2;
		percolation = new WeightedQuickUnionUF(totalEle);
		isFullUF = new WeightedQuickUnionUF(totalEle);
		grid = new boolean[n][n];
		TOP = 0;
		BOTTOM = totalEle - 1;
		totalOpen = 0;
	}

	/**
	 * Returns one less than the inputed value if it is valid
	 * 
	 * @param i input value
	 * @throws IllegalArgumentException if i is greater than the side size or less
	 *                                  than one
	 * @return number one less than input if input meets requirements
	 */
	private int checkArgs(int i) {
		if (i < 1 || i > sideSize) {
			throw new IllegalArgumentException("Invalid argumenet. Should be greater than 0.");
		}
		return i - 1;
	}

	/**
	 * Connects specified block to all open adjacent blocks
	 * 
	 * @param row row of the block to be connected
	 * @param col column of the block to be connected
	 */
	private void adjacentUnion(int row, int col) {
		if (row == 0) {
			isFullUF.union(TOP, get1DPositionFrom2DPosition(row, col));
			percolation.union(TOP, get1DPositionFrom2DPosition(row, col));
		}
		if (row == sideSize - 1) {
			percolation.union(BOTTOM, get1DPositionFrom2DPosition(row, col));
		}
		if (row > 0) {
			if (grid[row - 1][col]) {
				isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row - 1, col));
				percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row - 1, col));
			}
		}
		if (row < sideSize - 1) {
			if (grid[row + 1][col]) {
				isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row + 1, col));
				percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row + 1, col));
			}
		}
		if (col > 0) {
			if (grid[row][col - 1]) {
				isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col - 1));
				percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col - 1));
			}
		}
		if (col < sideSize - 1) {
			if (grid[row][col + 1]) {
				isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col + 1));
				percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col + 1));
			}
		}
	}

	/**
	 * 
	 * Opens specified block in percolation grid and connects it to the adjacent
	 * blocks that are also open
	 * 
	 * @param row row of the block to be opened in the grid
	 * @param col column of the block to be opened in the grid
	 */
	public void open(int row, int col) {
		row = checkArgs(row);
		col = checkArgs(col);
		if (!grid[row][col]) {
			grid[row][col] = true;
			totalOpen++;
			adjacentUnion(row, col);
		}
	}

	/**
	 * Returns <code>true</code> if specified block is open and <code>false</code>
	 * otherwise
	 * 
	 * @param row row of the block to be checked
	 * @param col column of the block to be checked
	 * @return <code>true</code> if specified block is open and <code>false</code>
	 *         otherwise
	 */
	public boolean isOpen(int row, int col) {
		return grid[checkArgs(row)][checkArgs(col)];
	}

	/**
	 * Returns <code>true</code> if there is a path from the top to the specified
	 * block and <code>false</code> otherwise
	 * 
	 * @param row row of the block to be checked
	 * @param col column of the block to be checked
	 * @return <code>true</code> if there is a path from the top to the specified
	 *         block and <code>false</code> otherwise
	 */
	public boolean isFull(int row, int col) {
		return (isFullUF.find(get1DPositionFrom2DPosition(checkArgs(row), checkArgs(col))) == isFullUF.find(TOP));
	}

	/**
	 * Returns total number of open sites on board
	 * 
	 * @return total number of open sites on board
	 */
	public int numberOfOpenSites() {

		return totalOpen;

	}

	/**
	 * Returns <code>true</code> if there is a path from the top to the bottom of
	 * the board using only open blocks and <code>false</code> otherwise
	 * 
	 * @return <code>true</code> if there is a path from the top to the bottom of
	 *         the board using only open blocks and <code>false</code> otherwise
	 */
	public boolean percolates() {

		return percolation.find(TOP) == percolation.find(BOTTOM);

	}

	public static void main(String[] args) {

	}

	/**
	 * Returns integer value that represents 1d position of specified block on 2d
	 * board
	 * 
	 * @param row row of the specified block to be converted to 1d
	 * @param col column of the specified block to be converted to 1d
	 * @return integer value that represents 1d position of specified block on 2d
	 *         board
	 */
	private int get1DPositionFrom2DPosition(int row, int col) {

		return (row * sideSize) + (col + 1);

	}

}
