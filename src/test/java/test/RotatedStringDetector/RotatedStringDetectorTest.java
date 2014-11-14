package test.RotatedStringDetector;

import static org.junit.Assert.*;

import org.junit.Test;

import practice.RotatedStringDetector.RotatedStringDetector;

public class RotatedStringDetectorTest {
	
	@Test
	public void testBothNull() {
		String testString = null;
		assertTrue("Two null strings did not did not return true.", 
				RotatedStringDetector.isRotation(testString, testString));
	}
	
	@Test
	public void testOneNull() {
		String testString = null;
		assertFalse("Two null strings did not did not return false.", 
				RotatedStringDetector.isRotation(testString, "Not Null"));

		assertFalse("Null test String with non-null base string did not return false.", 
				RotatedStringDetector.isRotation("Not Null", testString));
	}
	
	@Test
	public void testSameString() {
		String testString = "test";
		assertTrue("", RotatedStringDetector.isRotation(testString, testString));
	}
	
	@Test
	public void testSingleRightShift() {
		String testString = "waterbottle";
		String rotatedString = "ewaterbottl";
		assertTrue("", RotatedStringDetector.isRotation(testString, rotatedString));
	}

	@Test
	public void testMultiRightShift() {
		String testString = "waterbottle";
		String rotatedString = "tlewaterbot";
		assertTrue("", RotatedStringDetector.isRotation(testString, rotatedString));
	}

	
	@Test
	public void testSingleLeftShift() {
		String testString = "waterbottle";
		String rotatedString = "aterbottlew";
		assertTrue("", RotatedStringDetector.isRotation(testString, rotatedString));
	}

	@Test
	public void testMultiLeftShift() {
		String testString = "waterbottle";
		String rotatedString = "rbottlewate";
		assertTrue("", RotatedStringDetector.isRotation(testString, rotatedString));
	}
}
