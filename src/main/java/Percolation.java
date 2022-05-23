import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {



	private boolean[][] grid; // (openslots) true - open || false - closed

	private int numOpen; // sideSize

	private WeightedQuickUnionUF percolation; // backwash

	private WeightedQuickUnionUF isFullUF; // uf

	private final int TOP;

	private final int BOTTOM;
	
	private int sideSize;
	
    private int totalOpen;

	// creates n-by-n grid, with all sites initially blocked



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
    private int checkArgs(int i) {
        if (i < 1 || i > sideSize) {
            throw new IllegalArgumentException("Invalid argumenet. Should be greater than 0.");
        }
        return i - 1;
    }
    
    /**
     * topUnion method in example
     * 
     * @param row
     * @param col
     */
    private void topUnion(int row, int col) {
        if (row > 0) {
            if (grid[row - 1][col]) {
                isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row - 1, col));
                percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row - 1, col));
            }
        }
    }
    
    private void bottomUnion(int row, int col) {
        if (row < sideSize - 1) {
            if (grid[row + 1][col]) {
                isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row + 1, col));
                percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row + 1, col));
            }
        }
    }

    private void leftUnion(int row, int col) {
        if (col > 0) {
            if (grid[row][col - 1]) {
                isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col - 1));
                percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col - 1));
            }
        }
    }

    private void rightUnion(int row, int col) {
        if (col < sideSize - 1) {
            if (grid[row][col + 1]) {
                isFullUF.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col + 1));
                percolation.union(get1DPositionFrom2DPosition(row, col), get1DPositionFrom2DPosition(row, col + 1));
            }
        }
    }

    // Join all adjecent open vertices
    private void adjecentUnion(int row, int col) {
        if (row == 0) {
            isFullUF.union(TOP, get1DPositionFrom2DPosition(row, col));
            percolation.union(TOP, get1DPositionFrom2DPosition(row, col));
        }
        if (row == sideSize - 1) {
            percolation.union(BOTTOM, get1DPositionFrom2DPosition(row, col));
        }
        topUnion(row, col);
        bottomUnion(row, col);
        leftUnion(row, col);
        rightUnion(row, col);
    }




    public void open(int row, int col) {
        row = checkArgs(row);
        col = checkArgs(col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            totalOpen++;
            adjecentUnion(row, col);
        }
    }


	public boolean isOpen(int row, int col) {
		return grid[checkArgs(row)][checkArgs(col)];
	}



	// done

	public boolean isFull(int row, int col) {
        return (isFullUF.find(get1DPositionFrom2DPosition(checkArgs(row), checkArgs(col))) == isFullUF.find(TOP));
	}



	// done

	public int numberOfOpenSites() {

		return totalOpen;

	}



	// done

	public boolean percolates() {

		return percolation.find(TOP) == percolation.find(BOTTOM);

	}



	public static void main(String[] args) {
		Percolation test = new Percolation(2);
        test.open(1, 1);
        test.open(2, 2);
        test.open(1, 2);
        System.out.println(test.percolates());

	}



	// helper method

	private int get1DPositionFrom2DPosition(int row, int col) {

		return (row * sideSize) + (col + 1);

	}



}

