import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Represents PercolationStats object that creates a specified amount of a
 * specified size Percolation objects and finds the average value, standard
 * deviation, and confidence coefficient
 * 
 * @author gabrieljones
 *
 */
public class PercolationStats {
	private double[] trials; // stores number of blocks that must be opened for a grid to percolate for every
								// trial divided by the total blocks in the board
	private int sideSize; // the size of the size of a Percolation board
	private double stdDev; // standard deviation of all of the trials
	private double mean; // average number to create percolating grid
	private double coeff; // calculated confidence coefficient
	// perform independent trials on an n-by-n grid

	/**
	 * Constructs PercolationStats Object based on a specified board size and number
	 * of trials
	 * 
	 * @param n     side size of Percolation objects to be constructed
	 * @param trial number of trials to complete by the method
	 */
	public PercolationStats(int n, int trial) {
		if (n <= 0 || trial <= 0) {
			throw new IllegalArgumentException("Arguments should be greater than 0.");
		}
		sideSize = n;
		trials = new double[trial];
		performOperations();
		mean = StdStats.mean(trials);
		stdDev = StdStats.stddev(trials);
		calcConfidenceCoeff();
	}

	/**
	 * Sets values for trials variable by running the specified number of games set
	 * in the beginning
	 */
	private void performOperations() {
		for (int i = 0; i < trials.length; i++) {
			Percolation grid = new Percolation(sideSize);
			int iteration = 0;
			while (!grid.percolates()) {
				int x = StdRandom.uniform(sideSize) + 1;
				int y = StdRandom.uniform(sideSize) + 1;
				if (!grid.isOpen(x, y)) {
					grid.open(x, y);
					iteration++;
				}
			}
			trials[i] = (double) iteration / (sideSize * sideSize);
		}
	}

	/**
	 * Sets coeff variable to calculated confidence coefficient
	 */
	private void calcConfidenceCoeff() {
		coeff = (1.96 * stdDev) / Math.sqrt(trials.length);
	}

	/**
	 * Returns average value of the trials variable
	 * 
	 * @return average value of the trials variable
	 */
	public double mean() {
		return mean;
	}

	/**
	 * Returns standard deviation of the trials variable
	 * 
	 * @return standard deviation of the trials variable
	 */
	public double stddev() {
		return stdDev;
	}

	/**
	 * Returns difference of the average and the confidence coefficient
	 * 
	 * @return difference of the average and the confidence coefficient
	 */
	public double confidenceLo() {
		return mean - coeff;
	}

	/**
	 * Returns sum of the average and the confidence coefficient
	 * 
	 * @return sum of the average and the confidence coefficient
	 */
	public double confidenceHi() {
		return mean + coeff;
	}

	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.printf("mean                     = %.16f %n", ps.mean());
		System.out.printf("stddev                   = %.16f %n", ps.stddev());
		System.out.printf("95%% confidence interval = [%.16f, %.16f] %n", ps.confidenceLo(), ps.confidenceLo());
	}

}