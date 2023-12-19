package SQLIntergration;

import java.util.ArrayList;

public class Account {
	private int id;
	private String name;
	private String username;
	private String password;
	private ArrayList<Course> enrolledCourses;
	private int failedAttempts;
	
	public Account() {};
	
	public Account(String username, String password) {
		super();		
		this.username = username;
		this.password = password;
		this.enrolledCourses = new ArrayList<Course>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(Course course) {
		this.enrolledCourses.add(course);
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
}
