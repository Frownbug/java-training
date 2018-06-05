package studentDataBase;

import java.util.ArrayList;

public class StudentDataBaseApp {

	public static void main(String[] args) {
		
	}
}
	
class Student {
	//properties of student
	private static int iD = 1000;
	private String email;
	private String phoneNumber;
	private String state;
	private String city;
	private String name;
	private String SSN;
	private String userID;
	private double balance = 0;
	private String[] courseList = { "MATH", "SCIENCE", "HISTORTY", "LANGUAGE" };
	private ArrayList<String> studentCourses = new ArrayList<String>();
	
	//constructor
	public Student(String SSN, String name) {
		this.SSN = SSN;
		this.name = name;
		iD++;
		setEmail();
		setUserID();
	}
	
	private void setUserID() {
		int max = 9000;
		int min = 1000;
		int randNumb = (int) (Math.random() * (max - min));
		randNumb += min;
		userID = iD + "" + randNumb + SSN.substring(6, 10);
		System.out.println("UserID: " + userID);
	}
	
	private void setEmail() {
		this.email = name.toLowerCase() + "@school.edu";
	}
	
	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void enroll(String pickedCourse) {
		String course = pickedCourse.toUpperCase();
		for(String c : courseList) {
			if(c.equals(course) && !(studentCourses.contains(course))) {
				studentCourses.add(course);
				balance -= 200;
			}
		}
		studentCourses.trimToSize();
	}
	
	public void pay(double amount) {
		balance += amount;
		checkBalance();
	}
	
	public void checkBalance() {
		System.out.println("Balance: $" + balance);
	}
	
	public void showCourses() {
		System.out.println("Student\'s Courses: " + studentCourses);
	}
	
	@Override
	public String toString() {
		return "[STUDENT INFO]\nName: " + name + " \nFrom: " + getCity() + ", " + getState() + " \nEmail: " + getEmail() + " \nPhone: " + phoneNumber;
	}
		
}
