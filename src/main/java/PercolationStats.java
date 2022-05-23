import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trials;
    private int sideSize;
    private double stdDev;
    private double aver;
    private double coeff;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trial) {
        if (n <= 0 || trial <= 0) {
            throw new IllegalArgumentException("Arguments should be greater than 0.");
        }
        sideSize = n;
        this.trials = new double[trial];
        performOperations();
        this.aver = StdStats.mean(trials);
        this.stdDev = StdStats.stddev(trials);
        calcConfidenceCoeff();
    }

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

    private void calcConfidenceCoeff() {
        this.coeff = (1.96 * stdDev) / Math.sqrt(trials.length);
    }

    public double aver() {
        return aver;
    }


    public double stddev() {
        return stdDev;
    }


    public double confidenceLo() {
        return aver - coeff;
    }

    public double confidenceHi() {
        return aver + coeff;
    }

    // test client
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("aver                     = %.16f %n", ps.aver());
        System.out.printf("stddev                   = %.16f %n", ps.stddev());
        System.out.printf("95%% confidence interval = [%.16f, %.16f] %n", ps.confidenceLo(), ps.confidenceLo());
    }

}