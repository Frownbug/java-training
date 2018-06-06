package passwordValidation;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordValidationApp {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter New Password: ");
		String password = reader.nextLine();
		
		Pattern ws = Pattern.compile("\\s+");
		Pattern letter = Pattern.compile("[a-zA-Z]");
		Pattern digit = Pattern.compile("[\\d]");
		Pattern punct = Pattern.compile("[\\p{Punct}]");
		Matcher s = ws.matcher(password);
		Matcher l = letter.matcher(password);
		Matcher d = digit.matcher(password);
		Matcher p = punct.matcher(password);
		
		try {
			if (s.find()) {
				throw new WhiteSpacesException(password);
			} else if (!(l.find()) && d.find() && p.find()) {
				throw new LetterException(password);
			} else if (!(d.find()) && l.find() && p.find()) {
				throw new DigitException(password);
			} else if (!(p.find()) && d.find() && l.find()) {
				throw new SpecialCharException(password);
			} else if (!(l.find()) && !(d.find()) && p.find()) {
				throw new DigitLetterException(password);
			} else if (!(l.find()) && !(p.find()) && d.find()) {
				throw new SpecialCharLetterException(password);
			} else if (!(d.find()) && !(p.find()) && l.find()) {
				throw new SpecialCharDigitException(password);
			} else {
				System.out.print("Re-type Password: ");
				String retypedPassword = reader.nextLine();
				reader.close();
				
				try {
					if (!(password.matches(retypedPassword))) {
						throw new PasswordDoesntMatchException(retypedPassword);
					} else {
						System.out.println("Password Accepted");
					}
				} catch (PasswordDoesntMatchException e) {
					System.out.println("ERROR: Password does not match");
					System.out.println(e.toString());
				}
			}
		} catch (WhiteSpacesException e) {
			System.out.println("ERROR: Password cannot contain white spaces");
			System.out.println(e.toString());
		} catch (DigitException e) {
			System.out.println("ERROR: Password must contain at least one number");
			System.out.println(e.toString());
		} catch (LetterException e) {
			System.out.println("ERROR: Password must contain at least one letter");
			System.out.println(e.toString());
		} catch (SpecialCharException e) {
			System.out.println("ERROR: Password must contain a special character e.g. \"!@#$%^&*(){}[]:;\"");
			System.out.println(e.toString());
		} catch (DigitLetterException e) {
			System.out.println("ERROR: Password must contain at least one digit and letter");
			System.out.println(e.toString());
		} catch (SpecialCharLetterException e) {
			System.out.println("ERROR: Password must contain at least one special character and letter");
			System.out.println(e.toString());
		} catch (SpecialCharDigitException e) {
			System.out.println("ERROR: Password must contain at least one special character and Digit");
			System.out.println(e.toString());
		}
	}
}

class WhiteSpacesException extends Exception {
	String pw;
	
	WhiteSpacesException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("WhiteSpacesException: " + pw);
	}
}

class DigitException extends Exception {
	String pw;
	
	DigitException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("DigitException: " + pw);
	}
}

class LetterException extends Exception {
	String pw;
	
	LetterException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("LetterException: " + pw);
	}
}

class SpecialCharException extends Exception {
	String pw;
	
	SpecialCharException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("SpecialCharException: " + pw);
	}
}

class DigitLetterException extends Exception {
	String pw;
	
	DigitLetterException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("DigitLetterException: " + pw);
	}
}

class SpecialCharLetterException extends Exception {
	String pw;
	
	SpecialCharLetterException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("SpecialCharLetterException: " + pw);
	}
}

class SpecialCharDigitException extends Exception {
	String pw;
	
	SpecialCharDigitException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("SpecialCharDigitException: " + pw);
	}
}

class PasswordDoesntMatchException extends Exception {
	String pw;
	
	PasswordDoesntMatchException(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return ("PasswordDoesntMatchException: " + pw);
	}
}