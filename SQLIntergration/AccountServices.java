package SQLIntergration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountServices {
	public void registerNewUser(String name, String username, String password) throws SQLException {
		Connection connection = SQLConnection.makeConnection();
		PreparedStatement preStmt = null;

		String SQL = "INSERT INTO accounts (`name`, `username`, `password`) VALUES (?, ?, ?);";

		preStmt = connection.prepareStatement(SQL);

		preStmt.setString(1, name);
		preStmt.setString(2, username);
		preStmt.setString(3, password);

		preStmt.executeUpdate();

		System.out.println("----------------------");
		System.out.println("Account Registered Successfully!");
	}

	public int login(String username, String password) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "SELECT u.* FROM accounts u WHERE username = ?";

		PreparedStatement preStmt = connection.prepareStatement(sql);
		preStmt.setString(1, username);

		ResultSet resultSet = preStmt.executeQuery();

		if (!resultSet.next()) {
			System.out.println("----------------------");
			System.out.println("Account not found. Please try again.");
			return -1;
		}

		Account account = new Account();
		account.setId(resultSet.getInt("ID"));
		account.setUsername(resultSet.getString("username"));
		account.setPassword(resultSet.getString("password"));
		account.setFailedAttempts(resultSet.getInt("FailedAttempts"));

		if (!account.getPassword().equals(password)) {
			System.out.println("----------------------");
            System.out.println("Wrong credentials! Try Again!");
			updateFailedAttempts(account.getUsername());
			return -1;
		}

		if (account.getFailedAttempts() >= 4) {
			System.out.println("----------------------");
			System.out.println("Your Account is locked!");
			return -1;

		} else {
			System.out.println("----------------------");
			System.out.println("Login successfully!");
			return account.getId();
		}
	}

	public void updateFailedAttempts(String username) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "UPDATE account SET `FailedAttempts` = FailedAttempts + 1 WHERE username = ?";

		PreparedStatement preStmt = connection.prepareStatement(sql);

		preStmt.setString(1, username);

		preStmt.executeUpdate();
	}

	public void showAccountEnrolledCourses(int id) throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String sql = "SELECT ac.* FROM accounts a " 
		+ "JOIN accountscourses ac "
		+ "ON ac.user_id = a.id WHERE a.id = ?;";

		CourseServices courseService = new CourseServices();

		PreparedStatement preStmt = connection.prepareStatement(sql);

		preStmt.setInt(1, id);

		ResultSet resultSet = preStmt.executeQuery();

		ArrayList<Course> accountCourses = new ArrayList<Course>();

		while (resultSet.next()) {
			Course course = new Course();
			course.setId(resultSet.getInt("course_id"));
			accountCourses.add(course);
		}

		System.out.println("----------------------");
		System.out.println("Enrolled Courses: ");

		for (Course course : accountCourses) {
			if (course.getId() == 0) {
				System.out.println("----------------------");
				System.out.println("Currently not enrolled in any courses.");
			} else {
				courseService.showCourseDetails(course.getId());
			}
		}

	}

	public void enrolledInNewCourse(int courseId, int accountId) throws SQLException {
		Connection connection = SQLConnection.makeConnection();
		String sql = "SELECT * FROM accountscourses WHERE account_id = ?;";

		PreparedStatement preStmt = connection.prepareStatement(sql);

		preStmt.setInt(1, accountId);

		ResultSet resultSet = preStmt.executeQuery();

		while (resultSet.next()) {
			if (resultSet.getInt("course_id") == 0) {
				updateNewCourse(connection, courseId, accountId);
				System.out.println("----------------------");
				System.out.println("Enrolled in course successfully");

				return;
			}
			if (resultSet.getInt("course_id") == courseId) {
				System.out.println("----------------------");
				System.out.println("Course already enrolled!");
				return;
			}
			if (resultSet.getInt("course_id") != courseId) {
				insertNewCourse(connection, accountId, courseId);
				System.out.println("----------------------");
				System.out.println("Enrolled in course successfully");
				return;
			}
		}
	}

	public void updateNewCourse(Connection connection, int courseId, int accountId) throws SQLException {
		String sql = "UPDATE accountscourses SET course_id = ? WHERE (`account_id` = ?) and (`course_id` = '0');";

		PreparedStatement preStmt = connection.prepareStatement(sql);

		preStmt.setInt(1, courseId);

		preStmt.setInt(2, accountId);

		preStmt.executeUpdate();

	}

	public void insertNewCourse(Connection connection, int accountId, int courseId) throws SQLException {
		String sql = "INSERT INTO accountscourses (`account_id`, `course_id`) VALUES (?, ?);";

		PreparedStatement preStmt = connection.prepareStatement(sql);

		preStmt.setInt(1, accountId);

		preStmt.setInt(2, courseId);

		preStmt.executeUpdate();
	}
}
