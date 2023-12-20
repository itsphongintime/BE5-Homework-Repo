package SQLIntergration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseServices {
	public void showAllCourses() throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String SQL = "SELECT * FROM courses;";
		
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(SQL);
		
		ArrayList<Course> courses = new ArrayList<Course>();

		while (resultSet.next()) {
			Course course = new Course();

			course.setId(resultSet.getInt("ID"));
			course.setName(resultSet.getString("Name"));
			course.setBegin(resultSet.getDate("BeginDate"));
			course.setEnd(resultSet.getDate("EndDate"));
			course.setFee(resultSet.getInt("Fee"));
			course.setMentors(getMentorsViaCourseID(course.getId(), connection));
			courses.add(course);
		}
		
		System.out.println("----------------------");
		for (Course course : courses) {
			System.out.println(course.getId() + ". " + course.getName());
		}
	}
	
	public void showCourseDetails(int id) throws SQLException {
		Connection connection = SQLConnection.makeConnection();
		
		String sql = "SELECT c.*, m.name as mentorName FROM course c "
				+ "JOIN coursesmentors cm ON c.id = cm.course_id "
				+ "JOIN mentors m ON m.id = cm.mentor_id WHERE c.id = ? "
				+ "GROUP BY c.id, c.name, c.begin, c.end, c.fee";

		PreparedStatement preStmt = connection.prepareStatement(sql);
		
		preStmt.setInt(1, id);

		ResultSet resultSet = preStmt.executeQuery();

		Course course = new Course();
		course.setId(resultSet.getInt("ID"));
		course.setName(resultSet.getString("Name"));
		course.setBegin(resultSet.getDate("BeginDate"));
		course.setEnd(resultSet.getDate("EndDate"));
		course.setFee(resultSet.getInt("Fee"));
		course.setMentors(getMentorsViaCourseID(course.getId(), connection));

		System.out.println("----------------------");
		System.out.println(course.getName());
		System.out.println("Course's Mentors:");
		for (Mentor mentor : course.getMentors()) {
			System.out.println(mentor.getName());
		}
		System.out.println("From " + course.getBegin());
		System.out.println("To " + course.getEnd());
		System.out.println("Course's fee: AUD" + course.getFee());
	}

	public ArrayList<Mentor> getMentorsViaCourseID(int courseId, Connection connection) throws SQLException {
		String sql = "SELECT m.* FROM courses c "
				+ "JOIN coursesmentors cm ON c.id = cm.course_id "
				+ "JOIN mentor m ON m.id = cm.mentor_id "
				+ "WHERE c.id = ?;";

		ArrayList<Mentor> mentors = new ArrayList<Mentor>();
		
		PreparedStatement preStmt = connection.prepareStatement(sql);
		
		preStmt.setInt(1, courseId);

		ResultSet resultSet = preStmt.executeQuery();

		while (resultSet.next()) {
			Mentor mentor = new Mentor();
			mentor.setName(resultSet.getString("Name"));
			mentors.add(mentor);
		}
		return mentors;
	}

	public ArrayList<Mentor> getAllMentors() throws SQLException {
		Connection connection = SQLConnection.makeConnection();

		String SQL = "SELECT * FROM mentors";
		
		Statement stmt = connection.createStatement();
		
		ResultSet resultSet = stmt.executeQuery(SQL);
		
		ArrayList<Mentor> mentors = new ArrayList<Mentor>();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Mentor mentor = new Mentor(id, name);
			mentors.add(mentor);
		}
		return mentors;
	}
}
