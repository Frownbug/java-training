package studentDataBase;

import java.util.ArrayList;

public class StudentDataBaseApp {

	public static void main(String[] args) {
		
		Student student1 = new Student("1234567890", "James");
		Student student2 = new Student("1020304050", "Sarah");
		
		student1.setCity("Columbus");
		student1.setPhoneNumber("614-555-1234");
		student1.setState("Ohio");
		System.out.println(student1.toString());
		student1.enroll("math");
		student1.enroll("language");
		student1.enroll("chior");
		student1.showCourses();
		student1.checkBalance();
		student1.pay(235.06);
		
		student2.setCity("Seattle");
		student2.setState("Washington");
		student2.setPhoneNumber("516-888-4444");
		student2.enroll("Histroy");
		
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
