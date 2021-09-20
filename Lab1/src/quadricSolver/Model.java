package quadricSolver;
import quadricSolver.exceptions.*;

/**
 * Model level of QuadraticSolverApp
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Model {
	/** part of equation <i><b>a</b>*x^2+b*x+c</i> */
	private final double a;
	/** part of equation <i>a*x^2+<b>b</b>*x+c</i> */
	private final double b;
	/** part of equation <i>a*x^2+b*x+<b>c</b></i> */
	private final double c;
	/** cached discriminant value */
	private final double discriminant;

	/**
	 * @param a part of equation <i><b>a</b>*x^2+b*x+c</i>
	 * @param b part of equation <i>a*x^2+<b>b</b>*x+c</i>
	 * @param c part of equation <i>a*x^2+b*x+<b>c</b></i>
	 * @throws InvalidQuadraticException called when equation have many solves (<b>a=b=0</b>)
	 */
	public Model(double a, double b, double c) throws InvalidQuadraticException {
		if (a == 0 && b == 0) {
			throw new InvalidQuadraticException("");
		} 
		this.a = a;
		this.b = b;
		this.c = c;
		this.discriminant = Math.pow(this.b, 2) - 4 * this.a * this.c;
	}

	/** @return boolean if solve is complex */
	public Boolean isComplex(){ return this.discriminant < 0; }

	/**
	 * @param format formatting string to represent the result
	 * @return equation complex solve in string represents
	 */
	private String[] complexRoot(String format) {
		double base = 2 * a;
		double real = -b / base;
		double complex = Math.sqrt(-discriminant) / base;

		String[] solve = new String[2];
		solve[0] = String.format(format + " + i" + format, real, complex);
		solve[1] = String.format(format + " - i" + format, real, complex);
		return solve;
	}

	/**
	 * @param format formatting string to represent the result
	 * @return equation solve in string represents
	 */
	private String[]  oneRoot(String format) {
		double leftValue = -b;
		double base = 2 * a;

		String[] result = new String[1];
		result[0] = String.format(format, leftValue/ base);
		return result;
	}

	/**
	 * @param format formatting string to represent the result
	 * @return equation solve in string represents
	 */
	private String[] twoRoot(String format) {
		double leftValue = -b;
		double rightValue = Math.sqrt(discriminant);
		double base = 2 * a;

		String[] result = new String[2];
		result[0] = String.format(format, (leftValue + rightValue) / base);
		result[1] = String.format(format, (leftValue - rightValue) / base);
		return result;
	}

	/**
	 * @param roundThreshold count digits after dot in string represents
	 * @return equation solve in string represents
	 */
	public String[] solve(int roundThreshold) {
		String format = "%." + roundThreshold + "f";
		String[] result;
		if (!isComplex()) {
			if (this.discriminant == 0) {
				result = oneRoot(format);
			} else {
				result = twoRoot(format);
			}
		} else {
			result = complexRoot(format);
		} return result;
	}
}
