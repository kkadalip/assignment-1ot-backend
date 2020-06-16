package com.iot.assignment.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class WindChillUtilTest {

	@Test
	public void calcWindChillInC() {
		assertNotNull("-1C and 10m/s wind", WindChillUtil.calcWindChillInC(-1d, 10d));
		double delta = 0.1;
		Double res1 = WindChillUtil.calcWindChillInC(-15d, 10d);
		assertEquals("-15C and 10m/s wind", -26.9, res1, delta);
		Double res2 = WindChillUtil.calcWindChillInC(10d, 25d);
		assertEquals("10C and 25m/s wind", 4.1, res2, delta);
		Double res3 = WindChillUtil.calcWindChillInC(0d, 0d);
		assertNull("0C and no wind", res3);
	}

	@Test
	public void calcWindChillInF() {
		assertNotNull("-1C and 10m/s wind", WindChillUtil.calcWindChillInF(-1d, 10d));
		double delta = 0.1;
		Double res1 = WindChillUtil.calcWindChillInF(-15d, 10d);
		assertEquals("-15C and 10m/s wind", -16.4, res1, delta);
		Double res2 = WindChillUtil.calcWindChillInF(10d, 25d);
		assertEquals("10C and 25m/s wind", 39.4, res2, delta);
		Double res3 = WindChillUtil.calcWindChillInF(0d, 0d);
		assertNull("0C and no wind", res3);
	}
}
