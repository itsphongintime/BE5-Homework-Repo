package SQLIntergration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
	private int id;
	private String name;
	ArrayList<Mentor> mentors;
	private Date begin;
	private Date end;
	private int fee;
	
	public Course() {};

	public Course(int id, String name, List<Mentor> mentors, java.sql.Date begin, java.sql.Date end, int fee) {
		super();
		this.id = id;
		this.name = name;
		this.mentors = new ArrayList<Mentor>();
		this.begin = begin;
		this.end = end;
		this.fee = fee;
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

	public ArrayList<Mentor> getMentors() {
		return mentors;
	}

	public void setMentors(ArrayList<Mentor> mentors) {
		this.mentors = mentors;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
}