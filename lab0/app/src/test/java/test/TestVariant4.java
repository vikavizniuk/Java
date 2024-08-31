package test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lab.Variant4;


public class TestVariant4 {

	
	////////////////////////////////////////////////

	@Test(dataProvider = "integerProvider")
	public void integerTest(int a, int b, int expected) {
		assertEquals(new Variant4().integerNumbersTask(a, b), expected);
	}

	@DataProvider
	public Object[][] integerProvider() {
		return new Object[][] { { 100, 1, 100 }, { 12, 3, 4 }, { 35, 3, 11 } };
	}

	@Test(expectedExceptions = AssertionError.class, dataProvider = "negativeIntegerProvider") 
	public void negativeIntegerTest(int a, int b) {
		new Variant4().integerNumbersTask(a, b);
	}

	@DataProvider
	public Object[][] negativeIntegerProvider() {
		return new Object[][] { { 3 , 5}, {-2, 5}, {6, -4}};
	}

	////////////////////////////////////////////////

	@Test(dataProvider = "positiveIfProvider")
	public void positiveIfTest(int a, int b, int c, int expected) {
		assertEquals(new Variant4().ifTask(a, b, c), expected);
	}

	@DataProvider
	public Object[][] positiveIfProvider() {
		return new Object[][] {
			{ 1, -1, -1, 1 },
			{ -1, 1, -1, 1 },
			{ -1, -1, 1, 1 },
			{ 1, 2, -1, 2 },
			{ 1, -1, 2, 2 },
			{ -1, 1, 2, 2 },
			{ 1, 2, 3, 3 },
			{ 2, 1, 3, 3 },
			{ 3, 1, 2, 3 }
		};
	}

	@Test(dataProvider = "negativeIfProvider")
	public void negativeIfTest(int a, int b, int c, int expected) {
		assertEquals(new Variant4().ifTask(a, b, c), expected);
	}

	@DataProvider
	public Object[][] negativeIfProvider() {
		return new Object[][] {
			{ -1, -2, -3, 0 },
			{ 1, -2, -3, 1 },
			{ -1, 2, -3, 1 },
			{ -1, -2, 3, 1 },
			{ 1, 2, -3, 2 },
			{ -1, 2, 3, 2 },
			{ 2, 3, -1, 2 }
		};
	}


	//////////////////////////////////////////////////

	@Test(dataProvider = "positiveBooleanProvider")
	public void positiveBooleanTest(int A, int B, boolean expected) {
		assertEquals(new Variant4().booleanTask(A, B), expected);
	}

	@DataProvider
	public Object[][] positiveBooleanProvider() {
		return new Object[][] {
			{ 3, 3, true },
			{ 4, 2, true },
			{ 5, 1, true },
			{ 10, 0, true }
		};
	}

	@Test(dataProvider = "negativeBooleanProvider")
	public void negativeBooleanTest(int A, int B, boolean expected) {
		assertEquals(new Variant4().booleanTask(A, B), expected);
	}

	@DataProvider
	public Object[][] negativeBooleanProvider() {
		return new Object[][] {
			{ 2, 3, false },
			{ 2, 2, false },
			{ 1, 3, false },
			{ 1, 2, false },
			{ 3, 4, false },
			{ 4, 5, false },
			{ 5, 6, false },
			{ 2, 4, false },
			{ 1, 5, false },
			{ 3, 4, false },
			{ 5, 5, false }
		};
	}



	//////////////////////////////////////////////////

	@Test(dataProvider = "switchProvider")
	public void switchTest(int monthNumber, int expectedDays) {
		assertEquals(new Variant4().switchTask(monthNumber), expectedDays);
	}

	@DataProvider
	public Object[][] switchProvider() {
		return new Object[][] {
			// 31 day months
			{ 1, 31 }, // January
			{ 3, 31 }, // March
			{ 5, 31 }, // May
			{ 7, 31 }, // July
			{ 8, 31 }, // August
			{ 10, 31 }, // October
			{ 12, 31 }, // December

			// 30 day months
			{ 4, 30 }, // April
			{ 6, 30 }, // June
			{ 9, 30 }, // September
			{ 11, 30 }, // November

			// 28 days
			{ 2, 28 }  // February
		};
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void invalidMonthTest1() {
		new Variant4().switchTask(0); 
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void invalidMonthTest2() {
		new Variant4().switchTask(13); 
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void invalidMonthTest3() {
		new Variant4().switchTask(-1); 
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void invalidMonthTest4() {
		new Variant4().switchTask(14); 
	}


	///////////////////////////////////////////////////

	@Test(dataProvider = "forProvider")
	public void forTest(double pricePerKg, double expectedTotalCost) {
		assertEquals(new Variant4().forTask(pricePerKg), expectedTotalCost, 0.0001);
	}

	@DataProvider
	public Object[][] forProvider() {
		return new Object[][] {
			{ 10.0, 550.0 }, 
			{ 15.5, 852.5 }, 
			{ 5.75, 316.25 } 
		};
	}

	@Test(expectedExceptions = AssertionError.class)
	public void negativePriceTest() {
		new Variant4().forTask(-10.0); 
		new Variant4().forTask(0.0); 
	}

	///////////////////////////////////////////////////

	@Test(dataProvider = "whileProvider")
	public void whileTest(int n, boolean expectedResult) {
		assertEquals(new Variant4().whileTask(n), expectedResult);
	}

	@DataProvider
	public Object[][] whileProvider() {
		return new Object[][] {
			{ 1, true },   
			{ 3, true },  
			{ 9, true },  
			{ 27, true },  
			{ 81, true },   
			{ 243, true },  
			{ 729, true }, 

			{ 10, false },
			{ 8, false },
			{ 12, false },
			{ 18, false },
			{ 25, false },
			{ 50, false },
			{ 100, false },
			{ 200, false },
			{ 500, false }
		};
	}

	@Test(expectedExceptions = AssertionError.class)
	public void negativeNumberTest() {
		new Variant4().whileTask(-5);
		new Variant4().whileTask(0);
	}


	//////////////////////////////////////////
	@Test(dataProvider = "arrayProvider")
	public void arrayTest(int N, int A, int D, int[] expectedArray) {
		int[] resultArray = new Variant4().arrayTask(N, A, D);
		assertEquals(resultArray.length, expectedArray.length);
		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(resultArray[i], expectedArray[i]);
		}
	}

	@DataProvider
	public Object[][] arrayProvider() {
		return new Object[][] {
			{ 5, 2, 3, new int[] { 2, 6, 18, 54, 162 } }, 
			{ 4, 1, 2, new int[] { 1, 2, 4, 8 } },    
			{ 3, 3, 3, new int[] { 3, 9, 27 } },     
			{ 6, 5, 2, new int[] { 5, 10, 20, 40, 80, 160 } }, 

			{ 3, 1, 1, new int[] { 1, 1, 1 } }, 
			{ 3, 1, 0, new int[] { 1, 0, 0 } }, 
			{ 3, 2, -2, new int[] { 2, -4, 8 } } 
		};
	}

	@Test(expectedExceptions = AssertionError.class)
	public void negativeNTest() {
		new Variant4().arrayTask(-5, 2, 3);
		new Variant4().arrayTask(1, 2, 3);
		new Variant4().arrayTask(0, 2, 3); 
	}

	//////////////////////////////////////////
	
	@Test(dataProvider = "matrixProvider")
	public void twoDimensionArrayTest(int M, int N, int[] numbers, int[][] expectedMatrix) {
		int[][] resultMatrix = new Variant4().twoDimensionArrayTask(M, N, numbers);
		assertEquals(resultMatrix.length, expectedMatrix.length);
		for (int i = 0; i < M; i++) {
			assertEquals(resultMatrix[i].length, expectedMatrix[i].length);
			for (int j = 0; j < N; j++) {
				assertEquals(resultMatrix[i][j], expectedMatrix[i][j]);
			}
		}
	}

	@DataProvider
	public Object[][] matrixProvider() {
		return new Object[][] {
			{ 3, 4, new int[] { 1, 2, 3, 4 }, new int[][] {
				{ 1, 2, 3, 4 },
				{ 1, 2, 3, 4 },
				{ 1, 2, 3, 4 }
			} }, 
			
			{ 2, 3, new int[] { 5, 6, 7 }, new int[][] {
				{ 5, 6, 7 },
				{ 5, 6, 7 }
			} }, 
			
			{ 4, 2, new int[] { 8, 9 }, new int[][] {
				{ 8, 9 },
				{ 8, 9 },
				{ 8, 9 },
				{ 8, 9 }
			} }, 

			{ 1, 1, new int[] { 0 }, new int[][] {
				{ 0 }
			} }, 
			
			{ 3, 1, new int[] { 1 }, new int[][] {
				{ 1 },
				{ 1 },
				{ 1 }
			} }, 

			{ 2, 2, new int[] { -1, -2 }, new int[][] {
				{ -1, -2 },
				{ -1, -2 }
			} } 
		};
	}

	@Test(expectedExceptions = AssertionError.class)
	public void invalidMatrixSizeTest() {
		new Variant4().twoDimensionArrayTask(3, 4, new int[] { 1, 2, 3 }); 
		new Variant4().twoDimensionArrayTask(-2, 3, new int[] { 1, 2, 3 }); 
		new Variant4().twoDimensionArrayTask(2, 2, new int[] { 1 }); 
	}
}
