package passwordValidation;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordValidationApp {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter New Password: ");
		String password = reader.nextLine();
		reader.close();
		
		String[] patterns = {"(?=([\\s]+))", "(?=([^a-zA-Z]))", "(?=([^\\d]))", "(?=([^\\p{Punct}]))"};  
		
		for( String regx : patterns) {
			Pattern p = Pattern.compile(regx);
			Matcher match = p.matcher(password);
		
			try {
				if (match.find()) {
					throw new RequirementsException(password, regx);
				}
			} catch (RequirementsException e) {
			System.out.println(RequirementsException.errorMsg);
			System.out.println(e.toString());
			}
		}
	}
}

class RequirementsException extends Exception {
	private String pw;
	static String errorMsg;
	
	RequirementsException(String pw, String rx) {
		this.pw = pw;
		switch (rx) {
		case "(?=([\\s]+))": errorMsg = "Password can not contain White Spaces";
		break;
		case "(?=([^a-zA-Z]))": errorMsg = "Password must contain a Letter";
		break;
		case "(?=([^\\d]))": errorMsg = "Password must contain a Digit";
		break;
		case "(?=([^\\p{Punct}]))": errorMsg = "Password must contain a Special Character (e.g. !@#$%^&*)";
		break;
		}
		
	}
	
	public String toString() {
		return ("RequirementsException: " + pw);
	}
}

/*class PasswordDoesntMatchException extends Exception {
	private String pw;
	
	PasswordDoesntMatchException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("PasswordDoesntMatchException: " + pw);
	}
}
*/