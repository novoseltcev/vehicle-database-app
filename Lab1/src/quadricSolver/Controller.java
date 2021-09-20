package quadricSolver;
import quadricSolver.exceptions.*;
import java.util.Scanner;

/**
 * Controller level of QuadraticSolverApp
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Controller {
	public static void main(String[] argv) {
		try {
			View.print("Enter a, b, c - coefficients quadratic equation (a * x^2 + b * x + c):");

			double[] coefficient = read(3);
			Model quadratic = new Model(coefficient[0], coefficient[1], coefficient[2]);

			String[] result = quadratic.solve(3);
			if (result.length == 2) {
				View.print("x = " + result[0] + ", " + result[1]);
			} else {
				View.print("x = " + result[0]);
			}
		} catch(InvalidQuadraticException e) {
			View.error("Invalid coefficients a and b must not be nullable together");
		}
	}

	/**
	 * read double buffer with handling input error
	 * @param count Count input values to {@linkplain Controller#readDouble(Scanner)} from input
	 * @return readied double buffer
	 */
	public static double[] read(int count) {
		try (Scanner sc = new Scanner(System.in)) {
			double[] result = new double[count];
			for (int i = 0; i < count; ++i) {
				double tmp = readDouble(sc);
				result[i] = tmp;
			}
			return result;
		} catch (Exception e) {
			View.crash();
			throw e;
		}
	}

	/**
	 *	read double value with handling input error
	 * @param sc opened Scanner descriptor
	 * @return readied double value
	 */
	private static double readDouble(Scanner sc) {
		while (true) {
			if (sc.hasNextFloat()) {
				return sc.nextFloat();
			} else {
				View.error("Please input valid number");
			}
		}
	}
}
