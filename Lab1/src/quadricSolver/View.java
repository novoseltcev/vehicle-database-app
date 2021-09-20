package quadricSolver;

/**
 * View level of QuadraticSolverApp
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class View {
	/** @param msg View msg */
	public static void print(String msg) {
		System.out.println(msg);
	}

	/** @param msg View error msg */
	public static void error(String msg) {
		print("Error: " + msg);
	}

	/** Called to report a crash */
	public static void crash() {
		print("Program has been crashed!!!");
	}
}
