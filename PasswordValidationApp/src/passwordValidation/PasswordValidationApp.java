package passwordValidation;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordValidationApp {

	public static void main(String[] args) {
		
		boolean digitPresent = false;
		boolean letterPresent = false;
		boolean specCharPresent = false;
		boolean noWhiteSpace = false;
		boolean passwordMatch = false;
		String password;
		String retypedPassword;
		
		Scanner reader = new Scanner(System.in);
		
		do {
			System.out.print("Enter New Password: ");
			password = reader.nextLine();
			
			Pattern whSp = Pattern.compile("\\s");
			Matcher ws = whSp.matcher(password);
			Pattern d = Pattern.compile("\\d");
			Matcher digit = d.matcher(password);
			Pattern l = Pattern.compile("[a-zA-Z]");
			Matcher letter = l.matcher(password);
			Pattern sc = Pattern.compile("\\p{Punct}");
			Matcher specChar = sc.matcher(password);
			
			try {
				if (ws.find()) {
					throw new WhiteSpaceException(password);
				} else {
					noWhiteSpace = true;
				}
				if (!(letter.find())) {
					throw new LetterException(password);
				} else {
					letterPresent = true;
				}
				if (!(digit.find())) {
					throw new DigitException(password);
				} else {
					digitPresent = true;
				}
				if (!(specChar.find())) {
					throw new SpecCharException(password);
				} else {
					specCharPresent = true;
				}
			} catch (WhiteSpaceException e) {
				System.out.println("ERROR: Password must not contain White Spaces");
				System.out.println(e.toString()); 
			} catch (LetterException e) {
				System.out.println("ERROR: Password must contain at least one Letter");
				System.out.println(e.toString());
			} catch (DigitException e) {
				System.out.println("ERROR: Password must contain at least one Digit");
				System.out.println(e.toString());
			} catch (SpecCharException e) {
				System.out.println("ERROR: Password must contain at least one Special Character (e.g. !@#$%^&*)");
				System.out.println(e.toString());
			}
		}  while (!(digitPresent) || !(noWhiteSpace) || !(letterPresent) || !(specCharPresent));
		
		do {
			if ((digitPresent) && (noWhiteSpace) && (letterPresent) && (specCharPresent)) {
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

class WhiteSpaceException extends Exception {
	private String pw;
	
	WhiteSpaceException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("WhiteSpaceException: " + pw);
	}
}

class DigitException extends Exception {
	private String pw;
	
	DigitException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("DigitException: " + pw);
	}
}

class LetterException extends Exception {
	private String pw;
	
	LetterException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("LetterException: " + pw);
	}
}

class SpecCharException extends Exception {
	private String pw;
	
	SpecCharException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("SpecCharException: " + pw);
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