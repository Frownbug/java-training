package passwordValidation;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordValidationApp {

	public static void main(String[] args) {
		
		boolean validPassword = true;
		boolean noWhiteSpace = true;
		boolean passwordMatch = true;
		String password;
		String retypedPassword;
		
		Scanner reader = new Scanner(System.in);
		
		do {
			System.out.print("Enter New Password: ");
			password = reader.nextLine();
			
			Pattern whSp = Pattern.compile("\\s");
			Matcher ws = whSp.matcher(password);
			
			try {
				if (ws.find()) {
					noWhiteSpace = false;
					throw new WhiteSpaceException(password);
				} else {
					noWhiteSpace = true;
				}
			} catch (WhiteSpaceException e) {
				System.out.println("ERROR: Password must not contain White Spaces");
				System.out.println(e.toString());
			}
			
			String[] patterns = {"[a-zA-Z]", "\\d", "\\p{Punct}"};
			
			for( String regx : patterns) {
				Pattern p = Pattern.compile(regx);
				Matcher match = p.matcher(password);
			
				try {
					if (!(match.find())) {
						validPassword = false;
						throw new RequirementsException(password, regx);
					} else {
						validPassword = true;
					}
				} catch (RequirementsException e) {
					System.out.println(RequirementsException.errorMsg);
					System.out.println(e.toString());
				} 
			}
		}  while (validPassword == false || noWhiteSpace == false);
		
		do {
			if (validPassword) {
				System.out.print("Re-type Password: ");
				retypedPassword = reader.nextLine();
				
				try {
					if (!(retypedPassword.equals(password))) {
						passwordMatch = false;
						throw new PasswordDoesntMatchException(retypedPassword);
					} else {
						passwordMatch = true;
						System.out.println("Password Accepted");
					}
				} catch (PasswordDoesntMatchException e) {
					System.out.println("ERROR: Password does not match");
					System.out.println(e.toString());
				}
			}
		} while (passwordMatch == false);
		
		reader.close();
	}
}

class RequirementsException extends Exception {
	private String pw;
	static String errorMsg;
	
	RequirementsException(String pw, String rx) {
		this.pw = pw;
		switch (rx) {
		case "[a-zA-Z]": errorMsg = "Password must contain a Letter";
		break;
		case "\\d": errorMsg = "Password must contain a Digit";
		break;
		case "\\p{Punct}": errorMsg = "Password must contain a Special Character (e.g. !@#$%^&*)";
		break;
		}
		
	}
	
	public String toString() {
		return ("RequirementsException: " + pw);
	}
}

class WhiteSpaceException extends Exception {
	private String pw;
	
	WhiteSpaceException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("WhiteSpaceException: " + pw);
	}
}

class PasswordDoesntMatchException extends Exception {
	private String pw;
	
	PasswordDoesntMatchException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("PasswordDoesntMatchException: " + pw);
	}
}