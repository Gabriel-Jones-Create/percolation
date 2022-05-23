import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationStatsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		assertEquals("Math.PI shoudl equal 3.14 with 0.01 delta", Math.PI, 3.14, 0.01); // third value is the max
																						// difference (delta) ∆

	}

	@Test
	public void testMeanAccurate() {
		PercolationStats perc = new PercolationStats(10, 50);
		assertEquals("Average value should be close to 0.6", perc.mean(), 0.6, 0.04); // more trials means more accuracy
	}

	@Test
	public void testMeanRough() {
		PercolationStats perc = new PercolationStats(10, 2);
		assertEquals("Average value should be close to 0.6", perc.mean(), 0.6, 0.24); // fewer trials is more prone to
																						// error
	}

	@Test
	public void testStdDevAccurate() {
		PercolationStats perc = new PercolationStats(10, 50);
		assertEquals("Standard Deviation value should be close to 0.07", perc.stddev(), 0.07, 0.0185);
	}

	@Test
	public void testStdDevRough() {
		PercolationStats perc = new PercolationStats(10, 2);
		assertEquals("Standard Deviation value should be close to 0.07", perc.stddev(), 0.07, 0.06);
	}

	@Test
	public void testConfidenceLowAccurate() {
		PercolationStats perc = new PercolationStats(10, 50);
		assertEquals("Should Print", perc.confidenceLo(), 0.6, 0.054);
	}

	@Test
	public void testConfidenceLowRough() {
		PercolationStats perc = new PercolationStats(10, 2);
		assertEquals("Should Print", perc.confidenceLo(), 0.6, 0.3);
	}

	@Test
	public void testConfidenceHighAccurate() {
		PercolationStats perc = new PercolationStats(10, 50);
		assertEquals("Should Print", perc.confidenceHi(), 0.6, 0.054);
	}

	@Test
	public void testConfidenceHighRough() {
		PercolationStats perc = new PercolationStats(10, 50);
		assertEquals("Should Print", perc.confidenceHi(), 0.6, 0.3);
	}

}
