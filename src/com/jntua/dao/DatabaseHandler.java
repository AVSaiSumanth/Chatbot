package com.jntua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.jntua.dao.model.AttendanceTL;
import com.jntua.dao.model.ClassRoom;
import com.jntua.dao.model.CourseTL;
import com.jntua.dao.model.DeptTL;
import com.jntua.dao.model.QuestionTl;
import com.jntua.dao.model.StaffSubjectTL;
import com.jntua.dao.model.StaffTL;
import com.jntua.dao.model.StudentAnsTL;
import com.jntua.dao.model.StudentTL;
import com.jntua.dao.model.SubjectTL;
import com.jntua.dao.model.UserTL;
import com.jntua.dao.util.JDBCUtility;
import com.jntua.utility.MailService;
import com.sun.org.apache.xpath.internal.operations.Gte;

@Repository
public class DatabaseHandler {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	private MailService mailService=new MailService();

	// Database Name
	private static final String DATABASE_NAME = "JNTUATPFacultlyDBase.db";

	// Staff table name
	private static final String TABLE_STAFF = "StaffTL";

	// Staff Table Columns names

	private static String staffId = "staffId";
	private static String name = "name";
	private static String emailId = "emailId";
	private static String mobileNo = "mobileNo";
	private static String userName = "userName";
	private static String password = "password";

	private static final String TABLE_SUBJECT = "SubjectTL";
	private static String subjectId = "SubjectId";

	private static String title = "title";
	private static String courseId = "courseId";
	private static String semester = "semester";
	private static String status = "status";

	private static final String TABLE_DEPTTL = "DeptTL";

	private static String deptId = "deptId";
	private static String deptName = "deptName";
	private static String deptDescription = "deptDescription";

	private static final String TABLE_ATTENTL = "AttendanceTL";
	private static String attendanceId = "attendanceId";

	private static String attendanceTime = "attendanceTime";

	private static final String TABLE_STAFF_SUBJECT = "StaffSubjectTL";

	private static String staffSubjectId = "staffSubjectId";

	private static final String TABLE_CLASS_ROOM = "ClassRoomTL";

	private static final String classRoomId = "classRoomId";

	private static final String classTime = "classTime";

	private static final String TABLE_STUDENT = "StudentTL";

	private static String studentId = "studentId";

	private static String regNo = "regNo";

	private static String attendance_date = "attendance_date";
	private static String TIMESTAMP = "TIMESTAMP";
	private static String DEFAULT_CURRENT_TIMESTAMP = "DEFAULT CURRENT_TIMESTAMP";

	public Integer addClassRoom(ClassRoom classRoom) {
		String selectQuery = "SELECT  max(" + classRoomId + ") as classRoomId FROM " + TABLE_CLASS_ROOM;
		Connection con = JDBCUtility.getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		int cid = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery(selectQuery);
			if (rs.next()) {
				cid = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(st);
		}

		cid++;

		try {
			con = JDBCUtility.getConnection();
			pst = con.prepareStatement("insert into " + TABLE_CLASS_ROOM + "(" + classRoomId + "," + subjectId + ","
					+ staffId + ")values(?,?,?)");

			pst.setInt(1, cid);
			pst.setInt(2, classRoom.getSubjectId());
			pst.setInt(3, classRoom.getStaffId());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtility.closeStatement(pst);
		}

		return cid;
	}

	public void addStudentAns(List<StudentAnsTL> queAns) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into studentanstl(qid,studentId,createdon,sid,ans)values(?,?,?,?,?)");

			ListIterator<StudentAnsTL> li = queAns.listIterator();
			while (li.hasNext()) {
				StudentAnsTL studentAnsTL = li.next();

				pst.setInt(1, studentAnsTL.getQid());
				pst.setString(5, studentAnsTL.getAns());
				pst.setInt(2, studentAnsTL.getStudentId());
				pst.setTimestamp(3, studentAnsTL.getCreatedon());
				pst.setInt(4, studentAnsTL.getSid());
				pst.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}
	}

	public void addStudentMockAns(List<StudentAnsTL> queAns) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(
					"insert into studentmockanstl(qid,studentId,createdon,sid,ans,status)values(?,?,?,?,?,?)");

			ListIterator<StudentAnsTL> li = queAns.listIterator();
			while (li.hasNext()) {
				StudentAnsTL studentAnsTL = li.next();

				pst.setInt(1, studentAnsTL.getQid());
				pst.setString(5, studentAnsTL.getAns());
				pst.setInt(2, studentAnsTL.getStudentId());
				pst.setTimestamp(3, studentAnsTL.getCreatedon());
				pst.setInt(4, studentAnsTL.getSid());
				pst.setString(6, "Active");
				pst.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}
	}

	public void updateStudentMockAns(Integer studentId, String status) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update studentmockanstl set status where studentId=?");

			pst.setInt(1, studentId);
			pst.setString(2, status);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}
	}

	public void addAttendance(AttendanceTL attendanceTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into " + TABLE_ATTENTL + "(" + studentId + "," + subjectId + "," + status
					+ "," + classRoomId + ")values(?,?,?,?)");

			pst.setInt(1, attendanceTL.getStudentId());
			pst.setInt(2, attendanceTL.getSubjectId());
			pst.setString(3, attendanceTL.getStatus());
			pst.setInt(4, attendanceTL.getClassRoomId());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addDept(DeptTL deptTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(
					"insert into " + TABLE_DEPTTL + "(" + deptName + "," + deptDescription + ")values(?,?)");
			pst.setString(1, deptTL.getDeptName().toUpperCase());
			pst.setString(2, deptTL.getDeptDescription());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addQuestion(QuestionTl questionTl) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(
					"insert into questiontl(quationtitle, quetiontype, sid, status, ans, a, b, c, d,examType,sectionType,nmarks)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, questionTl.getQuationTitle());
			pst.setString(2, questionTl.getQuetionType());
			pst.setInt(3, questionTl.getSid());
			pst.setString(4, questionTl.getStatus());
			pst.setString(5, questionTl.getAns());
			pst.setString(6, questionTl.getA());
			pst.setString(7, questionTl.getB());
			pst.setString(8, questionTl.getC());
			pst.setString(9, questionTl.getD());
			pst.setString(10, questionTl.getExamType());
			pst.setString(11, questionTl.getSectionType());
			pst.setDouble(12, questionTl.getNmarks());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addStaffSubject(StaffSubjectTL staffSubjectTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into " + TABLE_STAFF_SUBJECT + "(" + subjectId + "," + staffId + ","
					+ status + ")values(?,?,?)");
			pst.setInt(1, staffSubjectTL.getSubjectId());
			pst.setInt(2, staffSubjectTL.getStaffId());
			pst.setString(3, staffSubjectTL.getStatus());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addStaff(StaffTL staffTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into " + TABLE_STAFF + "(" + name + "," + emailId + "," + mobileNo + ","
					+ userName + "," + password + "," + deptName + ")values(?,?,?,?,?,?)");
			pst.setString(1, staffTL.getName());
			pst.setString(2, staffTL.getEmailId());
			pst.setString(3, staffTL.getMobileNo());
			pst.setString(4, staffTL.getUserName());
			pst.setString(5, staffTL.getPassword());
			pst.setString(6, staffTL.getDeptName());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addUser(UserTL userTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(
					"insert into User_TL(user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no )values(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, userTL.getDesg());
			pst.setString(2, userTL.getFirstName());
			pst.setString(3, userTL.getLastName());
			pst.setString(4, "Active");
			pst.setInt(5, userTL.getDeptId());
			pst.setString(6, userTL.getFacultlyId());
			pst.setString(7, userTL.getEmailId());
			pst.setString(8, userTL.getUserName());
			pst.setString(9, userTL.getPassword());
			pst.setString(10, userTL.getMobileNo());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void deleteUser(Integer userId) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete FROM user_tl  where user_id=?");

			pst.setInt(1, userId);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public List<Map<String, Object>> getDeptUsers(Integer courseId) {
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT user_id, user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no,deptName FROM user_tl u,depttl d where u.dept_Id=d.deptId and u.dept_id in(select deptId from coursetl where courseId=?)";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {

				Map<String, Object> m = new LinkedHashMap<String, Object>();
				m.put("userId", rs.getInt("user_id"));
				m.put("desg", rs.getString("user_type"));
				m.put("firstName", rs.getString("first_name"));
				m.put("lastName", rs.getString("last_name"));
				m.put("facultyCode", rs.getString("faculty_code"));
				m.put("deptName", rs.getString("deptName"));

				users.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return users;

	}

	public List<Map<String, Object>> getAllUsers() {
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT user_id, user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no,deptName FROM user_tl u,depttl d where u.dept_Id=d.deptId";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {

				Map<String, Object> m = new LinkedHashMap<String, Object>();
				m.put("userId", rs.getInt("user_id"));
				m.put("desg", rs.getString("user_type"));
				m.put("firstName", rs.getString("first_name"));
				m.put("lastName", rs.getString("last_name"));
				m.put("facultyCode", rs.getString("faculty_code"));
				m.put("deptName", rs.getString("deptName"));

				users.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return users;

	}

	public Map<String, Object> getUser(String unm, String pwd) {
		Map<String, Object> m = null;
		// Select All Query
		String selectQuery = "SELECT user_id, user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no,deptName FROM user_tl u,depttl d where u.username=? and u.password=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, unm);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if (rs.next()) {

				m = new LinkedHashMap<String, Object>();
				m.put("userId", rs.getInt("user_id"));
				m.put("desg", rs.getString("user_type"));
				m.put("firstName", rs.getString("first_name"));
				m.put("lastName", rs.getString("last_name"));
				m.put("facultyCode", rs.getString("faculty_code"));
				m.put("deptName", rs.getString("deptName"));
				m.put("password", rs.getString("password"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return m;

	}

	public Map<String, Object> getUser(String unm) {
		Map<String, Object> m = null;
		// Select All Query
		String selectQuery = "SELECT user_id, user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no,deptName FROM user_tl u,depttl d where u.username=? ";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, unm);

			rs = pst.executeQuery();
			if (rs.next()) {

				m = new LinkedHashMap<String, Object>();
				m.put("userId", rs.getInt("user_id"));
				m.put("desg", rs.getString("user_type"));
				m.put("firstName", rs.getString("first_name"));
				m.put("lastName", rs.getString("last_name"));
				m.put("facultyCode", rs.getString("faculty_code"));
				m.put("deptName", rs.getString("deptName"));
				m.put("emailId", rs.getString("email_id"));
				m.put("password", rs.getString("password"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return m;

	}

	public void addStudent(StudentTL studentTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into " + TABLE_STUDENT + "(" + name + "," + emailId + "," + mobileNo
					+ "," + regNo + "," + password + "," + courseId + "," + semester + ",grade,totalNoofClasses,totalNoofClassesAtten)values(?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, studentTL.getName());
			pst.setString(2, studentTL.getEmailId());
			pst.setString(3, studentTL.getMobileNo());
			pst.setString(4, studentTL.getRegNo());
			pst.setString(5, studentTL.getPassword());
			pst.setInt(6, studentTL.getCourseId());
			pst.setString(7, studentTL.getSemester());
			pst.setString(8, studentTL.getGrade());
			pst.setInt(9, studentTL.getTotalNoofClasses());
			pst.setInt(10, studentTL.getTotalNoofClassesAtten());
			

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void updateStudent(Integer studentId, String password) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update studenttl set password=? where studentId=?");

			pst.setInt(2, studentId);
			pst.setString(1, password);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void updateUser(Integer userId, String password) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update user_tl set password=? where user_id=?");

			pst.setInt(2, userId);
			pst.setString(1, password);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void addSubject(SubjectTL subjectTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into " + TABLE_SUBJECT + "(" + name + "," + title + "," + status + ","
					+ courseId + "," + semester + ",qpattern)values(?,?,?,?,?,?)");
			pst.setString(1, subjectTL.getName());
			pst.setString(2, subjectTL.getTitle());
			pst.setString(3, subjectTL.getStatus());
			pst.setInt(4, subjectTL.getCourseId());
			pst.setString(5, subjectTL.getSemester());
			pst.setString(6, "single");

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public void updateSubjectExamPattern(String examPattern, Integer subjectId) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update " + TABLE_SUBJECT + " set qpattern=? where subjectId=?");
			pst.setString(1, examPattern);

			pst.setInt(2, subjectId);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	// Getting single AttendanceTL
	public AttendanceTL getAttendance(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		AttendanceTL attendanceTL = null;
		try {
			pst = con.prepareStatement(
					"select  attendanceId, studentId, subjectId, status from AttendanceTL where attendanceId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {

				attendanceTL = new AttendanceTL();
				attendanceTL.setAttendanceId(rs.getInt(1));
				attendanceTL.setStudentId(rs.getInt(2));
				attendanceTL.setSubjectId(rs.getInt(3));
				attendanceTL.setStatus(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return attendanceTL;
	}

	public DeptTL getDept(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		DeptTL deptTL = null;
		try {
			pst = con.prepareStatement("select  deptId, deptName, deptDescription from DeptTL where deptId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				deptTL = new DeptTL();
				deptTL.setDeptId(rs.getInt(1));
				deptTL.setDeptName(rs.getString(2));
				deptTL.setDeptDescription(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return deptTL;
	}

	public StaffSubjectTL getStaffSubject(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StaffSubjectTL staffSubjectTL = null;
		try {
			pst = con.prepareStatement(
					"select  staffSubjectId, subjectId, staffId, status from StaffSubjectTL where staffSubjectId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {

				staffSubjectTL = new StaffSubjectTL();
				staffSubjectTL.setStaffSubjectId(rs.getInt(1));
				staffSubjectTL.setSubjectId(rs.getInt(2));
				staffSubjectTL.setStaffId(rs.getInt(3));
				staffSubjectTL.setStatus(rs.getString(4));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return staffSubjectTL;
	}

	public StaffTL getStaff(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StaffTL staffTL = null;
		try {
			pst = con.prepareStatement(
					"select  staffId, name, emailId, mobileNo, userName, password, deptName  from StaffTL where staffId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				staffTL = new StaffTL();
				staffTL.setStaffId(rs.getInt(1));
				staffTL.setName(rs.getString(2));
				staffTL.setEmailId(rs.getString(3));
				staffTL.setMobileNo(rs.getString(4));
				staffTL.setUserName(rs.getString(5));
				staffTL.setPassword(rs.getString(6));
				staffTL.setDeptName(rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return staffTL;
	}

	public StaffTL getStaff(String uunm, String pwd) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		StaffTL staffTL = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(
					"select  staffId, name, emailId, mobileNo, userName, password, deptName from StaffTL where userName=? and password=?");
			pst.setString(1, uunm);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if (rs.next()) {
				staffTL = new StaffTL();
				staffTL.setStaffId(rs.getInt(1));
				staffTL.setName(rs.getString(2));
				staffTL.setEmailId(rs.getString(3));
				staffTL.setMobileNo(rs.getString(4));
				staffTL.setUserName(rs.getString(5));
				staffTL.setPassword(rs.getString(6));
				staffTL.setDeptName(rs.getString(7));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return staffTL;

	}

	public StudentTL getStudent(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StudentTL studentTL = null;
		try {
			pst = con.prepareStatement(
					"select   name, emailId, mobileNo, regNo, password, courseId, semester,studentId from StudentTL where studentId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				studentTL = new StudentTL();
				studentTL.setStudentId(rs.getInt(8));
				studentTL.setName(rs.getString(1));
				studentTL.setEmailId(rs.getString(2));
				studentTL.setMobileNo(rs.getString(3));
				studentTL.setRegNo(rs.getString(4));
				studentTL.setPassword(rs.getString(5));
				studentTL.setCourseId(rs.getInt(6));
				studentTL.setSemester(rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}
		System.out.println(studentTL+" studenttl");

		return studentTL;
	}

	public StudentTL getStudent(String unm, String pwd) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StudentTL studentTL = null;
		try {
			pst = con.prepareStatement(
					"select  name, emailId, mobileNo, regNo, password, courseId, semester, studentId from StudentTL where regNo=? and password=?");
			pst.setString(1, unm);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if (rs.next()) {
				studentTL = new StudentTL();
				studentTL.setStudentId(rs.getInt(8));
				studentTL.setName(rs.getString(1));
				studentTL.setEmailId(rs.getString(2));
				studentTL.setMobileNo(rs.getString(3));
				studentTL.setRegNo(rs.getString(4));
				studentTL.setPassword(rs.getString(5));
				studentTL.setCourseId(rs.getInt(6));
				studentTL.setSemester(rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return studentTL;
	}

	public StudentTL getStudent(String unm) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StudentTL studentTL = null;
		try {
			pst = con.prepareStatement(
					"select  name, emailId, mobileNo, regNo, password, courseId, semester, studentId,totalNoofClassesAtten, totalNoofClasses,grade from StudentTL where regNo=? ");
			pst.setString(1, unm);

			rs = pst.executeQuery();
			if (rs.next()) {
				studentTL = new StudentTL();
				studentTL.setStudentId(rs.getInt(8));
				studentTL.setName(rs.getString(1));
				studentTL.setEmailId(rs.getString(2));
				studentTL.setMobileNo(rs.getString(3));
				studentTL.setRegNo(rs.getString(4));
				studentTL.setPassword(rs.getString(5));
				studentTL.setCourseId(rs.getInt(6));
				studentTL.setSemester(rs.getString(7));
				studentTL.setTotalNoofClasses(rs.getInt("totalNoofClasses"));
				studentTL.setTotalNoofClassesAtten(rs.getInt("totalNoofClassesAtten"));
				studentTL.setGrade(rs.getString("grade"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return studentTL;
	}

	public SubjectTL getSubject(int id) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		SubjectTL subjectTL = null;
		try {
			pst = con.prepareStatement(
					"select   name, title, courseId, semester, status,subjectId,qpattern from SubjectTL where subjectId=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(6));
				subjectTL.setName(rs.getString(1));
				subjectTL.setTitle(rs.getString(2));
				subjectTL.setCourseId(rs.getInt(3));
				subjectTL.setSemester(rs.getString(4));
				subjectTL.setStatus(rs.getString(5));
				subjectTL.setQpattern(rs.getString("qpattern"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectTL;
	}

	public List<AttendanceTL> getAllAttence() {
		List<AttendanceTL> attendanceList = new ArrayList<AttendanceTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ATTENTL;
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				AttendanceTL attendanceTL = new AttendanceTL();
				attendanceTL.setAttendanceId(rs.getInt(1));
				attendanceTL.setStudentId(rs.getInt(2));
				attendanceTL.setSubjectId(rs.getInt(3));
				attendanceTL.setStatus(rs.getString(4));

				attendanceList.add(attendanceTL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return attendanceList;
	}

	public List<DeptTL> getAllDept() {
		List<DeptTL> deptTLList = new ArrayList<DeptTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_DEPTTL;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeptTL deptTL = new DeptTL();
				deptTL.setDeptId(rs.getInt(1));
				deptTL.setDeptName(rs.getString(2));
				deptTL.setDeptDescription(rs.getString(3));
				deptTLList.add(deptTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return deptTLList;
	}

	public List<QuestionTl> getQuestion(String status, int sid) {
		List<QuestionTl> questions = new ArrayList<QuestionTl>();
		// Select All Query
		String selectQuery = "SELECT  nmarks,qid, quationtitle, quetiontype, sid, status, ans, a, b, c, d,sectionType FROM  questiontl where status=? and sid=? order by  sectionType";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(2, sid);
			pst.setString(1, status);
			rs = pst.executeQuery();
			while (rs.next()) {
				QuestionTl questionTl = new QuestionTl();
				questionTl.setA(rs.getString("a"));
				questionTl.setB(rs.getString("b"));
				questionTl.setC(rs.getString("c"));
				questionTl.setD(rs.getString("d"));
				questionTl.setAns(rs.getString("ans"));
				questionTl.setQid(rs.getInt("qid"));
				questionTl.setStatus(rs.getString("status"));
				questionTl.setQuationTitle(rs.getString("quationTitle"));
				questionTl.setQuetionType(rs.getString("quetionType"));
				questionTl.setSid(rs.getInt("sid"));
				questionTl.setSectionType(rs.getString("sectionType"));
				questionTl.setNmarks(rs.getDouble("nmarks"));
				questions.add(questionTl);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return questions;
	}

	public List<QuestionTl> getQuestion(String status, int sid, String sectionType) {
		List<QuestionTl> questions = new ArrayList<QuestionTl>();
		// Select All Query
		String selectQuery = "SELECT nmarks, qid, quationtitle, quetiontype, sid, status, ans, a, b, c, d,sectionType FROM  questiontl where status=? and sid=? and  sectionType=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(2, sid);
			pst.setString(1, status);
			pst.setString(3, sectionType);
			rs = pst.executeQuery();
			while (rs.next()) {
				QuestionTl questionTl = new QuestionTl();
				questionTl.setA(rs.getString("a"));
				questionTl.setB(rs.getString("b"));
				questionTl.setC(rs.getString("c"));
				questionTl.setD(rs.getString("d"));
				questionTl.setAns(rs.getString("ans"));
				questionTl.setQid(rs.getInt("qid"));
				questionTl.setStatus(rs.getString("status"));
				questionTl.setQuationTitle(rs.getString("quationTitle"));
				questionTl.setQuetionType(rs.getString("quetionType"));
				questionTl.setSid(rs.getInt("sid"));
				questionTl.setSectionType(rs.getString("sectionType"));
				questionTl.setNmarks(rs.getDouble("nmarks"));
				questions.add(questionTl);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return questions;
	}

	public List<QuestionTl> getMockQuestion(String status, int sid) {
		List<QuestionTl> questions = new ArrayList<QuestionTl>();
		// Select All Query
		String selectQuery = "SELECT  nmarks,qid, quationtitle, quetiontype, sid, status, ans, a, b, c, d,examType ,sectionType FROM  questiontl where status=? and sid=? and examType='mock' order by  sectionType";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(2, sid);
			pst.setString(1, status);
			rs = pst.executeQuery();
			while (rs.next()) {
				QuestionTl questionTl = new QuestionTl();
				questionTl.setA(rs.getString("a"));
				questionTl.setB(rs.getString("b"));
				questionTl.setC(rs.getString("c"));
				questionTl.setD(rs.getString("d"));
				questionTl.setAns(rs.getString("ans"));
				questionTl.setQid(rs.getInt("qid"));
				questionTl.setStatus(rs.getString("status"));
				questionTl.setQuationTitle(rs.getString("quationTitle"));
				questionTl.setQuetionType(rs.getString("quetionType"));
				questionTl.setSid(rs.getInt("sid"));
				questionTl.setSectionType(rs.getString("sectionType"));
				questionTl.setNmarks(rs.getDouble("nmarks"));
				questions.add(questionTl);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return questions;
	}

	public QuestionTl getQuestion(int qid) {
		QuestionTl questionTl = null;
		// Select All Query
		String selectQuery = "SELECT  nmarks,qid, quationtitle, quetiontype, sid, status, ans, a, b, c, d, sectionType FROM  questiontl where qid=? order by  sectionType";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, qid);
			rs = pst.executeQuery();
			if (rs.next()) {
				questionTl = new QuestionTl();
				questionTl.setA(rs.getString("a"));
				questionTl.setB(rs.getString("b"));
				questionTl.setC(rs.getString("c"));
				questionTl.setD(rs.getString("d"));
				questionTl.setAns(rs.getString("ans"));
				questionTl.setQid(rs.getInt("qid"));
				questionTl.setStatus(rs.getString("status"));
				questionTl.setQuationTitle(rs.getString("quationTitle"));
				questionTl.setQuetionType(rs.getString("quetionType"));
				questionTl.setSectionType(rs.getString("sectionType"));
				questionTl.setSid(rs.getInt("sid"));
				questionTl.setNmarks(rs.getDouble("nmarks"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return questionTl;
	}

	public List<StaffTL> getAllStaff() {
		List<StaffTL> staffTList = new ArrayList<StaffTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_STAFF;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				StaffTL staffTL = new StaffTL();
				staffTL.setStaffId(rs.getInt(1));
				staffTL.setName(rs.getString(2));
				staffTL.setEmailId(rs.getString(3));
				staffTL.setMobileNo(rs.getString(4));
				staffTL.setUserName(rs.getString(5));
				staffTL.setPassword(rs.getString(6));
				staffTL.setDeptName(rs.getString(7));

				staffTList.add(staffTL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return staffTList;
	}

	public List<StudentTL> getAllStudents() {
		List<StudentTL> studentTLList = new ArrayList<StudentTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentTL studentTL = new StudentTL();
				studentTL.setStudentId(rs.getInt(1));
				studentTL.setName(rs.getString(2));
				studentTL.setEmailId(rs.getString(3));
				studentTL.setMobileNo(rs.getString(4));
				studentTL.setRegNo(rs.getString(5));
				studentTL.setPassword(rs.getString(6));
				studentTL.setCourseId(rs.getInt(7));
				studentTL.setSemester(rs.getString(8));

				studentTLList.add(studentTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return studentTLList;
	}

	public List<StudentTL> getStudents(String depNm, String sem) {
		List<StudentTL> studentTLList = new ArrayList<StudentTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " where " + deptName + "='" + depNm + "' and "
				+ semester + "='" + sem + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentTL studentTL = new StudentTL();
				studentTL.setStudentId(rs.getInt(1));
				studentTL.setName(rs.getString(2));
				studentTL.setEmailId(rs.getString(3));
				studentTL.setMobileNo(rs.getString(4));
				studentTL.setRegNo(rs.getString(5));
				studentTL.setPassword(rs.getString(6));
				studentTL.setCourseId(rs.getInt(7));
				studentTL.setSemester(rs.getString(8));

				studentTLList.add(studentTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return studentTLList;
	}

	public List<SubjectTL> getAllSubjects() {
		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<SubjectTL> getCourseSubjects(int courseId) {
		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + " where courseId=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, courseId);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<SubjectTL> getSubjects(Integer courseId, String sem) {
		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + "  where courseId=? and semester=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, courseId);
			pst.setString(2, sem);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<SubjectTL> getSubjects() {
		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);

			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<SubjectTL> getSubjects(Integer courseId, String sem, Integer fid) {
		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT
				+ "  where courseId=? and semester=? and subjectId in(select subjectId from staffsubjecttl where staffId=?)";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, courseId);
			pst.setString(2, sem);
			pst.setInt(1, fid);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<SubjectTL> getStaffSubjects(int stId) {

		List<SubjectTL> subjectsList = new ArrayList<SubjectTL>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + " sb," + TABLE_STAFF_SUBJECT + " ssb where ssb."
				+ subjectId + "=sb." + subjectId + " and ssb." + staffId + "=" + stId;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubjectTL subjectTL = new SubjectTL();
				subjectTL.setSubjectId(rs.getInt(1));
				subjectTL.setName(rs.getString(2));
				subjectTL.setTitle(rs.getString(3));
				subjectTL.setCourseId(rs.getInt(4));
				subjectTL.setSemester(rs.getString(5));
				subjectTL.setStatus(rs.getString(6));

				subjectsList.add(subjectTL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectsList;
	}

	public List<String> getAllDeptName() {
		List<String> deptTLList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  " + deptName + " FROM " + TABLE_DEPTTL;

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				String deptName = rs.getString(1);

				deptTLList.add(deptName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return deptTLList;
	}

	public List<String> getDeptStaff(String dept) {
		List<String> staffTList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  " + staffId + "," + name + "  FROM " + TABLE_STAFF + " where " + deptName + "='"
				+ dept + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				String staffId = rs.getString(1);
				String staffName = rs.getString(2);

				staffTList.add(staffId + "(" + staffName + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return staffTList;
	}

	public List<String> getDeptSubjects(String dept, String semes) {
		List<String> subjectList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT " + subjectId + ", " + name + "," + title + "  FROM " + TABLE_SUBJECT + " where "
				+ deptName + "='" + dept + "' and " + semester + "='" + semes + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				int subId = rs.getInt(1);
				String subjectName = rs.getString(2);
				String subjectTitle = rs.getString(3);

				subjectList.add(subId + "(" + subjectName + "|" + subjectTitle + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return subjectList;
	}

	public List<Map<String, Object>> getAlloredSubjects() {
		List<Map<String, Object>> allotedSubjectList = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT " + subjectId + ", " + staffId + "  FROM " + TABLE_STAFF_SUBJECT + " where "
				+ status + "='Active'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				int subId = rs.getInt(1);
				int sfId = rs.getInt(2);
				SubjectTL subjectTL = getSubject(subId);
				StaffTL staffTL = getStaff(sfId);
				Map<String, Object> m = new LinkedHashMap<String, Object>();
				m.put("subject", subjectTL);
				m.put("staff", staffTL);
				allotedSubjectList.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return allotedSubjectList;
	}

	public Map<String, Object> getStudentAttendance(String rgNo) {
		Map<String, Object> student = new LinkedHashMap<String, Object>();
		// Select All Query
		String selectQuery = "SELECT " + studentId + ", " + name + ",s." + deptName + "," + semester + "  FROM "
				+ TABLE_STUDENT + " s  where s." + regNo + "='" + rgNo + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		ResultSet rs = null;
		PreparedStatement pst3 = null;
		ResultSet rs3 = null;
		PreparedStatement pst4 = null;
		ResultSet rs4 = null;

		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			if (rs.next()) {
				int studId = rs.getInt(1);
				String sname = rs.getString(2);
				String sem = rs.getString(4);
				String dname = rs.getString(3);

				student.put("studentName", sname);
				student.put("sem", sem);
				student.put("dname", dname);

				selectQuery = "SELECT " + subjectId + ", " + name + "," + title + "  FROM " + TABLE_SUBJECT + " where "
						+ deptName + "='" + dname + "' and " + semester + "='" + sem + "'";

				pst = con.prepareStatement(selectQuery);
				rs = pst.executeQuery();

				List<Map<String, Object>> subjectList = new ArrayList<Map<String, Object>>();
				// looping through all rows and adding to list
				while (rs.next()) {

					int subId = rs.getInt(1);
					String subjectName = rs.getString(2);
					String subjectTitle = rs.getString(3);
					selectQuery = "SELECT count(" + classRoomId + ") from " + TABLE_CLASS_ROOM + " where " + subjectId
							+ "=" + subId + "";
					int totalClasses = 0;

					pst2 = con.prepareStatement(selectQuery);
					rs2 = pst2.executeQuery();
					if (rs2.next()) {
						totalClasses = rs2.getInt(1);

					}
					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Present' and " + studentId + "=" + studId + " and " + classRoomId + " in(select "
							+ classRoomId + " from " + TABLE_CLASS_ROOM + " where " + subjectId + "=" + subId + " )";
					int totalPresent = 0;
					pst3 = con.prepareStatement(selectQuery);
					rs3 = pst3.executeQuery();
					if (rs3.next()) {
						totalPresent = rs3.getInt(1);
					}

					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Absent' and " + studentId + "=" + studId + " and " + classRoomId + " in(select "
							+ classRoomId + " from " + TABLE_CLASS_ROOM + " where " + subjectId + "=" + subId + " )";
					int totalAbsent = 0;
					pst4 = con.prepareStatement(selectQuery);
					rs4 = pst4.executeQuery();
					if (rs4.next()) {
						totalAbsent = rs4.getInt(1);
					}
					Map<String, Object> subjectAtten = new LinkedHashMap<String, Object>();
					subjectAtten.put("subject", subjectName);
					subjectAtten.put("subjectTitle", subjectTitle);
					subjectAtten.put("Present", totalPresent);
					subjectAtten.put("Absent", totalAbsent);
					subjectAtten.put("Total", totalClasses);
					double perc = (100.0 * totalPresent) / totalClasses;
					subjectAtten.put("TotalPercentage", perc);

					subjectList.add(subjectAtten);

				}

				student.put("allSubjectAttendance", subjectList);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs4);
			JDBCUtility.closeStatement(pst4);
			JDBCUtility.closeResultSet(rs3);
			JDBCUtility.closeStatement(pst3);
			JDBCUtility.closeResultSet(rs2);
			JDBCUtility.closeStatement(pst2);
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);
		}

		System.out.println(student);

		return student;
	}

	public Map<String, Object> getStudentAttendance(Integer stId) {
		Map<String, Object> student = new LinkedHashMap<String, Object>();
		// Select All Query
		String selectQuery = "SELECT " + studentId + ", " + name + ",s." + deptName + "," + semester + "  FROM "
				+ TABLE_STUDENT + " s  where s." + studentId + "='" + stId + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		PreparedStatement pst3 = null;
		ResultSet rs3 = null;
		PreparedStatement pst4 = null;
		ResultSet rs4 = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			if (rs.next()) {
				int studId = rs.getInt(1);
				String sname = rs.getString(2);
				String sem = rs.getString(4);
				String dname = rs.getString(3);

				student.put("studentName", sname);
				student.put("sem", sem);
				student.put("dname", dname);

				selectQuery = "SELECT " + subjectId + ", " + name + "," + title + "  FROM " + TABLE_SUBJECT + " where "
						+ deptName + "='" + dname + "' and " + semester + "='" + sem + "'";

				pst = con.prepareStatement(selectQuery);
				rs = pst.executeQuery();

				List<Map<String, Object>> subjectList = new ArrayList<Map<String, Object>>();
				// looping through all rows and adding to list
				while (rs.next()) {

					int subId = rs.getInt(1);
					String subjectName = rs.getString(2);
					String subjectTitle = rs.getString(3);
					selectQuery = "SELECT count(" + classRoomId + ") from " + TABLE_CLASS_ROOM + " where " + subjectId
							+ "=" + subId + "";
					int totalClasses = 0;
					pst2 = con.prepareStatement(selectQuery);
					rs2 = pst2.executeQuery();
					if (rs2.next()) {
						totalClasses = rs2.getInt(1);

					}
					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Present' and " + studentId + "=" + studId + " and " + classRoomId + " in(select "
							+ classRoomId + " from " + TABLE_CLASS_ROOM + " where " + subjectId + "=" + subId + " )";
					int totalPresent = 0;
					pst3 = con.prepareStatement(selectQuery);
					rs3 = pst3.executeQuery();
					if (rs3.next()) {
						totalPresent = rs3.getInt(1);
					}

					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Absent' and " + studentId + "=" + studId + " and " + classRoomId + " in(select "
							+ classRoomId + " from " + TABLE_CLASS_ROOM + " where " + subjectId + "=" + subId + " )";
					int totalAbsent = 0;
					pst4 = con.prepareStatement(selectQuery);
					rs4 = pst4.executeQuery();
					if (rs4.next()) {
						totalAbsent = rs4.getInt(1);
					}
					Map<String, Object> subjectAtten = new LinkedHashMap<String, Object>();
					subjectAtten.put("subject", subjectName);
					subjectAtten.put("subjectTitle", subjectTitle);
					subjectAtten.put("Present", totalPresent);
					subjectAtten.put("Absent", totalAbsent);
					subjectAtten.put("Total", totalClasses);
					double perc = (100.0 * totalPresent) / totalClasses;
					subjectAtten.put("TotalPercentage", perc);

					subjectList.add(subjectAtten);

				}

				student.put("allSubjectAttendance", subjectList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs4);
			JDBCUtility.closeStatement(pst4);
			JDBCUtility.closeResultSet(rs3);
			JDBCUtility.closeStatement(pst3);
			JDBCUtility.closeResultSet(rs2);
			JDBCUtility.closeStatement(pst2);
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);
		}

		System.out.println(student);

		return student;
	}

	public List<Map<String, Object>> getStudentsAttendance(String depNm, String sem) {
		List<Map<String, Object>> studentTLList = new ArrayList<Map<String, Object>>();
		String selectQuery = "SELECT  " + studentId + " ," + regNo + " FROM " + TABLE_STUDENT + " where " + deptName
				+ "='" + depNm + "' and " + semester + "='" + sem + "'";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		PreparedStatement pst3 = null;
		ResultSet rs3 = null;
		PreparedStatement pst4 = null;
		ResultSet rs4 = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					Integer sid = rs.getInt(1);

					Map<String, Object> student = new LinkedHashMap<String, Object>();
					student.put("regNo", rs.getString(2));
					selectQuery = "SELECT count(" + classRoomId + ") from " + TABLE_CLASS_ROOM + " where " + subjectId
							+ " in(SELECT " + subjectId + " from " + TABLE_SUBJECT + " where " + deptName + "='" + depNm
							+ "' and " + semester + "='" + sem + "')";

					pst2 = con.prepareStatement(selectQuery);
					rs2 = pst2.executeQuery();
					int totalClasses = 0;

					if (rs2.next()) {

						totalClasses = rs2.getInt(1);

					}
					student.put("totalClasses", totalClasses);

					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Present' and " + studentId + "=" + sid;

					pst3 = con.prepareStatement(selectQuery);
					rs3 = pst3.executeQuery();
					int totalPresent = 0;

					if (rs3.next()) {

						totalPresent = rs3.getInt(1);

					}
					student.put("totalPresent", totalPresent);

					selectQuery = "SELECT count(" + attendanceId + ") from " + TABLE_ATTENTL + " where " + status
							+ "='Absent' and  " + studentId + "=" + sid;

					pst4 = con.prepareStatement(selectQuery);
					rs4 = pst4.executeQuery();
					int totalAbsent = 0;

					if (rs4.next()) {

						totalAbsent = rs4.getInt(1);

					}
					student.put("totalAbsent", totalAbsent);
					double per = (100.0 * totalPresent) / totalClasses;
					student.put("totalPerc", per);

					studentTLList.add(student);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCUtility.closeResultSet(rs4);
			JDBCUtility.closeStatement(pst4);
			JDBCUtility.closeResultSet(rs3);
			JDBCUtility.closeStatement(pst3);
			JDBCUtility.closeResultSet(rs2);
			JDBCUtility.closeStatement(pst2);
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);
		}

		System.out.println(studentTLList);

		return studentTLList;
	}

	public void insertCourse(CourseTL courseTL) {
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into  CourseTL (deptId, courseName,courseCode,descr)values(?,?,?,?)");
			pst.setInt(1, courseTL.getDeptId());
			pst.setString(2, courseTL.getCourseName());
			pst.setString(3, courseTL.getCourseCode());
			pst.setString(4, courseTL.getDescr());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeStatement(pst);
		}

	}

	public List<Map<String, Object>> getAllCourses() {
		List<Map<String, Object>> courses = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT courseId, deptName, courseName, courseCode, descr FROM coursetl c ,depttl d where c.deptId=d.deptId";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			rs = pst.executeQuery();
			while (rs.next()) {

				String deptName = rs.getString("deptName");
				String descr = rs.getString("descr");
				String ccode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String courseId = rs.getString("courseId");

				Map<String, Object> m = new HashMap<String, Object>();
				m.put("deptName", deptName);
				m.put("descr", descr);
				m.put("ccode", ccode);
				m.put("courseName", courseName);
				m.put("courseId", courseId);
				courses.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return courses;
	}

	public List<Map<String, Object>> getStudentAns(Integer sid, Integer studentId) {
		System.out.println("getttt");
		List<Map<String, Object>> ans = null;
		String selectQuery = "SELECT studentansid,  s.ans as studentAns, studentId, createdon, s.sid as subid, s.qid as sqid, quationtitle, quetiontype,sectionType,  status, q.ans, a, b, c, d,nmarks FROM studentanstl s,questiontl q where s.qid=q.qid and s.sid=? and  s.studentId=? order by q.sectionType";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, sid);
			pst.setInt(2, studentId);
			rs = pst.executeQuery();
			ans = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> m = new LinkedHashMap<>();

				m.put("studentansid", rs.getInt("studentansid"));
				m.put("studentAns", rs.getString("studentAns"));
				m.put("studentId", rs.getInt("studentId"));
				m.put("createdon", rs.getTimestamp("createdon"));
				m.put("subid", rs.getInt("subid"));
				m.put("sqid", rs.getInt("sqid"));
				m.put("quationtitle", rs.getString("quationtitle"));
				m.put("quetiontype", rs.getString("quetiontype"));
				m.put("status", rs.getString("status"));
				m.put("ans", rs.getString("ans"));
				m.put("d", rs.getString("d"));
				m.put("c", rs.getString("c"));
				m.put("b", rs.getString("b"));
				m.put("a", rs.getString("a"));
				m.put("sectionType", rs.getString("sectionType"));
				m.put("nmarks", rs.getDouble("nmarks"));

				ans.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}
		System.out.println(ans);

		return ans;

	}

	public List<Map<String, Object>> getStudentAns(Integer sid, Integer studentId, String sectionType) {

		List<Map<String, Object>> ans = null;
		String selectQuery = "SELECT studentansid,  s.ans as studentAns, studentId, createdon, s.sid as subid, s.qid as sqid, quationtitle, quetiontype,sectionType,  status, q.ans, a, b, c, d,nmarks FROM studentanstl s,questiontl q where s.qid=q.qid and s.sid=? and  s.studentId=? and q.sectionType=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, sid);
			pst.setInt(2, studentId);
			pst.setString(3, sectionType);
			rs = pst.executeQuery();
			ans = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> m = new LinkedHashMap<>();

				m.put("studentansid", rs.getInt("studentansid"));
				m.put("studentAns", rs.getString("studentAns"));
				m.put("studentId", rs.getInt("studentId"));
				m.put("createdon", rs.getTimestamp("createdon"));
				m.put("subid", rs.getInt("subid"));
				m.put("sqid", rs.getInt("sqid"));
				m.put("quationtitle", rs.getString("quationtitle"));
				m.put("quetiontype", rs.getString("quetiontype"));
				m.put("status", rs.getString("status"));
				m.put("ans", rs.getString("ans"));
				m.put("d", rs.getString("d"));
				m.put("c", rs.getString("c"));
				m.put("b", rs.getString("b"));
				m.put("a", rs.getString("a"));
				m.put("sectionType", rs.getString("sectionType"));
				m.put("nmarks", rs.getDouble("nmarks"));

				ans.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return ans;

	}

	public List<Map<String, Object>> getStudentMockAns(Integer sid, Integer studentId) {
		List<Map<String, Object>> ans = null;
		String selectQuery = "SELECT studentansid,  s.ans as studentAns, studentId, createdon, s.sid as subid, s.qid as sqid, quationtitle, quetiontype,  q.status, q.ans, a, b, c, d,nmarks FROM studentmockanstl s,questiontl q where s.qid=q.qid and s.sid=? and  s.studentId=? and s.status='Active";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, sid);
			pst.setInt(2, studentId);
			rs = pst.executeQuery();
			ans = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> m = new LinkedHashMap<>();

				m.put("studentansid", rs.getInt("studentansid"));
				m.put("studentAns", rs.getString("studentAns"));
				m.put("studentId", rs.getInt("studentId"));
				m.put("createdon", rs.getTimestamp("createdon"));
				m.put("subid", rs.getInt("subid"));
				m.put("sqid", rs.getInt("sqid"));
				m.put("quationtitle", rs.getString("quationtitle"));
				m.put("quetiontype", rs.getString("quetiontype"));
				m.put("status", rs.getString("status"));
				m.put("ans", rs.getString("ans"));
				m.put("d", rs.getString("d"));
				m.put("c", rs.getString("c"));
				m.put("b", rs.getString("b"));
				m.put("a", rs.getString("a"));
				m.put("nmarks", rs.getDouble("nmarks"));

				ans.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return ans;

	}

	public List<Map<String, Object>> getDeptFac(Integer deptId) {
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT user_id, user_type, first_name, last_name, status, dept_id, faculty_code, email_id, username, password, mobile_no,deptName FROM user_tl u,depttl d where u.dept_Id=d.deptId and u.dept_Id=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();
			while (rs.next()) {

				Map<String, Object> m = new LinkedHashMap<String, Object>();
				m.put("userId", rs.getInt("user_id"));
				m.put("desg", rs.getString("user_type"));
				m.put("firstName", rs.getString("first_name"));
				m.put("lastName", rs.getString("last_name"));
				m.put("facultyCode", rs.getString("faculty_code"));
				m.put("deptName", rs.getString("deptName"));

				users.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return users;

	}

	public List<Map<String, Object>> getDeptCourses(Integer deptId) {
		List<Map<String, Object>> courses = new ArrayList<Map<String, Object>>();
		// Select All Query
		String selectQuery = "SELECT courseId, deptName, courseName, courseCode, descr FROM coursetl c ,depttl d where c.deptId=d.deptId and c.deptId=?";

		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();
			while (rs.next()) {

				String deptName = rs.getString("deptName");
				String descr = rs.getString("descr");
				String ccode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String courseId = rs.getString("courseId");

				Map<String, Object> m = new HashMap<String, Object>();
				m.put("deptName", deptName);
				m.put("descr", descr);
				m.put("ccode", ccode);
				m.put("courseName", courseName);
				m.put("courseId", courseId);
				courses.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}

		return courses;
	}

	public Integer getRank(Integer sid, Integer subjectId) {
		double studentMarks = 0.0;
		Integer rank = 0;
		String selectQuery = "SELECT distinct studentId FROM studentanstl where sid=?";
		Set<Double> smarks = new TreeSet<>(new Comparator<Double>() {

			@Override
			public int compare(Double arg0, Double arg1) {

				return -arg0.compareTo(arg1);
			}
		});
		Connection con = JDBCUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, subjectId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer studentId = rs.getInt(1);
				Double marks = 0.0;
				List<Map<String, Object>> lst = this.getStudentAns(subjectId, studentId);
				for (int index = 0; index < lst.size(); index++) {
					Map<String, Object> m = (Map<String, Object>) lst.get(index);
					String studentAns = (String) m.get("studentAns");
					String ans = (String) m.get("ans");
					if (studentAns != null && ans != null) {
						if (studentAns.trim().equalsIgnoreCase(ans.trim())) {
							marks += 1;
						} else {
							marks = marks - (Double) m.get("nmarks");
						}
					}

				}
				smarks.add(marks);
				if (sid.equals(studentId)) {
					studentMarks = marks;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtility.closeResultSet(rs);
			JDBCUtility.closeStatement(pst);

		}
		for (double d : smarks) {
			rank++;
			if (d == studentMarks) {
				break;
			}

		}
		System.out.println(smarks + " smm");
		return rank;
	}

	public void getRanks() {
		List<SubjectTL> subjects = this.getAllSubjects();

		for (SubjectTL subj : subjects) {
			Set<Double> smarks = new TreeSet<>(new Comparator<Double>() {

				@Override
				public int compare(Double arg0, Double arg1) {

					return -arg0.compareTo(arg1);
				}
			});
			Map<StudentTL, Double> mm = new LinkedHashMap<>();
			String selectQuery = "SELECT distinct studentId FROM studentanstl where sid=?";

			Connection con = JDBCUtility.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = con.prepareStatement(selectQuery);
				pst.setInt(1, subj.getSubjectId());

				rs = pst.executeQuery();
				while (rs.next()) {
					Integer studentId = rs.getInt(1);
					Double marks = 0.0;
					List<Map<String, Object>> lst = this.getStudentAns(subj.getSubjectId(), studentId);
					if (lst != null) {
						for (int index = 0; index < lst.size(); index++) {
							Map<String, Object> m = (Map<String, Object>) lst.get(index);
							String studentAns = (String) m.get("studentAns");
							String ans = (String) m.get("ans");
							if (studentAns != null && ans != null) {
								if (studentAns.trim().equalsIgnoreCase(ans.trim())) {
									marks += 1;
								} else {
									marks = marks - (Double) m.get("nmarks");
								}
							}

						}
						StudentTL std=getStudent(studentId);
						mm.put(std, marks);
						smarks.add(marks);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtility.closeResultSet(rs);
				JDBCUtility.closeStatement(pst);

			}

			try {
				int rank=0;
				for (double mrk : smarks) {
					rank++;
					Set<Map.Entry<StudentTL, Double>> me=mm.entrySet();
					for(Map.Entry<StudentTL, Double> ms:me)
					{
						StudentTL studentTL=ms.getKey();
						double smark=ms.getValue();
						if(mrk==smark)
						{
							try {
								System.out.println(mailService);
								System.out.println(studentTL+" stuu");
								
								mailService.send(studentTL.getEmailId(), "your rank "+rank, "subject "+subj.getName()+" marks "+smark);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}