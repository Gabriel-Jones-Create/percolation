import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationStatsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		assertEquals("Math.PI shoudl equal 3.14 with 0.01 delta",Math.PI, 3.14, 0.01); // third value is the max difference (delta) ∆
		Percolation p = new Percolation(4);
		p.open(0, 0);
		
		
	}

}
