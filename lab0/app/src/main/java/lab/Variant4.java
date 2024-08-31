package lab;

public class Variant4 {

	/**
	 * 
	 * @param k is distance in cm
	 * @return Дані цілі позитивні числа A і B (A <= B). 
	 * На відрізку довжини A розміщено максимально можливу кількість відрізків довжини B (без накладень). 
	 * Використовуючи операцію поділу націло, знайти кількість відрізків B , розміщених на відрізку A .
	 */

	public int integerNumbersTask(int a, int b) {
		assert (a > 0 && b > 0 && a > b): "Arguments should be positive integers";
		return a / b;
	}

	/**
	 * 
	 * @param number
	 * @return Дано два цілих числа: A, B. Перевірити істинність висловлювання: «Справедливі нерівності A > 2 і B <= 3».
	 */
	public boolean booleanTask(int A, int B) {
		return A > 2 && B <= 3;
	}	

	/**
	 * 
	 * @param parameter is integer number
	 * @return Дано три цілих числа. Знайти кількість позитивних чисел у вихідному наборі.
	 */
	public int ifTask(int a, int b, int c) {
		int count = 0;
		if (a > 0) count++;
		if (b > 0) count++;
		if (c > 0) count++;
		return count;
	}
	/**
	 * 
	 * @param number1
	 * @return Даний номер місяця— ціле число в діапазоні 12 (1 січень, 2 лютого і лютий і т.д.).
	 * Визначити кількість днів цього місяця для невисокосного року.
	 */
	public int switchTask(int monthNumber) {
		int days;
		switch (monthNumber) {
			case 1: // January
			case 3: // March
			case 5: // May
			case 7: // July
			case 8: // August
			case 10: // October
			case 12: // December
				days = 31;
				break;
			case 4: // April
			case 6: // June
			case 9: // September
			case 11: // November
				days = 30;
				break;
			case 2: // February
				days = 28;
				break;
			default:
				throw new IllegalArgumentException("Invalid month number: " + monthNumber);
		}
		return days;
	}
	/**
	 * 
	 * @param n is integer number
	 * @return Дано дійсне число— ціна 1 кг цукерок. Вивести вартість 1, 2, 10 кг цукерок.
	 */
	public double forTask(double pricePerKg) {
		assert pricePerKg > 0 : "Price should be more than zero";
		double totalCost = 0;
		for (int i = 1; i <= 10; i++) {
			double cost = pricePerKg * i;
			System.out.println("Cost of " + i + " kg of candies: " + cost);
			totalCost += cost;
		}
		return totalCost;
	}
	/**
	 * 
	 * @param n is integer number
	 * @return Дано ціле число NN (> 0). Якщо воно є ступенем числа 3, то вивести True, якщо не є — вивести False.
	 */
	public boolean whileTask(int n) {
		assert n > 0 : "Number should be more than zero";
		while (n % 3 == 0) {
			n /= 3;
		}
		return n == 1;
	}

	/**
	 * 
	 * @param n is integer number
	 * @return Дано ціле число  N (N > 1), а також перший член  A і знаменник  D геометричної прогресії. 
	 * Сформувати і вивести масив розміру N , що містить N  перших членів даної прогресії.
	 */
	public int[] arrayTask(int N, int A, int D) {
		assert N > 1 : "N should be greater than 1";
		int[] progression = new int[N];
		progression[0] = A;
		for (int i = 1; i < N; i++) {
			progression[i] = progression[i - 1] * D;
		}
		return progression;
	}
	/**
	 * 
	 * @param array
	 * @param k1 
	 * @param k2
	 * @return Дані цілі позитивні числа MM, N та  набір з N чисел. 
	 * Сформувати матрицю розміру M * N, у якому в кожному рядку містяться всі числа з вихідного набору (в тому у порядку).
	 */
	public int[][] twoDimensionArrayTask(int M, int N, int[] numbers) {
		assert (M > 0 && N > 0) : "M and N should be positive numbers";
		assert numbers.length == N : "The length of numbers array should be equal to N";

		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = numbers[j];
			}
		}
		return matrix;
	}

	public static void main(String... strings) {
		Variant4 variant = new Variant4();

		int result = variant.integerNumbersTask(10, 3); 
		System.out.println("Number of segments: " + result);

		boolean booleanResult = variant.booleanTask(3, 3); 
		System.out.println("Result: " + booleanResult);

		int positiveCount = variant.ifTask(1, -2, 3); 
		System.out.println("Number of positive numbers: " + positiveCount);

		int daysInMonth = variant.switchTask(2); 
		System.out.println("Number of days in the month: " + daysInMonth);

		double totalCost = variant.forTask(15.5); 
		System.out.println("Total cost for 1 to 10 kg: " + totalCost);

		boolean powerResult = variant.whileTask(27); 
		System.out.println("Is power of 3: " + powerResult);

		int[] arrayResult = variant.arrayTask(5, 2, 3); 
		System.out.print("Geometric progression: ");
		for (int num : arrayResult) {
			System.out.print(num + " ");
		}
		System.out.println();

		int[] numbers = {1, 2, 3, 4}; 
		int[][] matrixResult = variant.twoDimensionArrayTask(3, 4, numbers); 
		System.out.println("Matrix:");
		for (int[] row : matrixResult) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}