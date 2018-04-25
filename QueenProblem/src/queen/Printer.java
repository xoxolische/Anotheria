package queen;

import java.util.List;

/**
 * Class for printing result
 * 
 * @author Nikita Pavlov
 *
 */
public class Printer {

	private Printer() {

	}

	/**
	 * Prints out to console all solutions
	 * 
	 * @param result
	 *            List of String[]
	 */
	public static void printResult(List<String[]> result) {
		for (String[] a : result) {
			String resultString = "";
			for (String s : a) {
				resultString += s + "\r\n";
			}
			resultString += "\r\n";
			System.out.println(resultString);
		}

	}

}
