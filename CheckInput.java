package chess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {

	private final static Pattern validInput = Pattern.compile("([1-8][a-hA-H])", Pattern.CASE_INSENSITIVE);

	public boolean checkCoordinateValidity(String input) {
		Matcher matcher = validInput.matcher(input);

		return matcher.matches();
	}
}
