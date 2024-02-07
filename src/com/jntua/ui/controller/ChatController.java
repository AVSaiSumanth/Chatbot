package com.jntua.ui.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jntua.dao.ChatDAO;
import com.jntua.dao.DatabaseHandler;
import com.jntua.dao.model.ActivityTL;
import com.jntua.dao.model.ChatUserTL;
import com.jntua.dao.model.CollegeTL;
import com.jntua.dao.model.CourseTL;
import com.jntua.dao.model.DeptTL;
import com.jntua.dao.model.PlacementTL;
import com.jntua.dao.model.QuestionTl;
import com.jntua.dao.model.StaffSubjectTL;
import com.jntua.dao.model.StaffTL;
import com.jntua.dao.model.StudentAnsTL;
import com.jntua.dao.model.StudentTL;
import com.jntua.dao.model.SubjectTL;
import com.jntua.dao.model.UserTL;
import com.jntua.utility.MailService;

@Controller
public class ChatController {

	@Autowired
	private DatabaseHandler db;
	@Autowired
	private ChatDAO chatDAO;

	@RequestMapping("login")
	public String showLogin(HttpServletRequest request) {

		return "index";

	}

	@RequestMapping("chatlogin")
	public String showChatLogin(HttpServletRequest request) {

		return "chatlogin";

	}

	@RequestMapping(value = "/chatlogin", method = RequestMethod.POST)
	public String chatLogin(HttpServletRequest request, ChatUserTL chatUserTL) {

		Integer chatUserId = chatDAO.insertChat(chatUserTL);
		chatUserTL.setChatUserId(chatUserId);
		HttpSession session = request.getSession();
		session.setAttribute("chatUser", chatUserTL);

		return "chatloginbox";

	}
	@RequestMapping(value = "/getmessages", method = RequestMethod.POST)
	public @ResponseBody
	String getmessages(HttpServletRequest request) {

		String comments = request.getParameter("comments");
		
		StringBuffer sb = new StringBuffer();
		CollegeTL collegeTL = chatDAO.getCollege();
		if (comments.equalsIgnoreCase("hi")||comments.equalsIgnoreCase("hai")||comments.equalsIgnoreCase("hello")){
			sb.append("Hello");
		}
		else
		{
			try {
				comments=SimpleSuggestionService.getWord(comments+" ");
				System.out.println(comments);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (comments
					.equalsIgnoreCase("how many departments are there in jntua")
					|| comments.equalsIgnoreCase("how many departments are there")
					|| comments.equalsIgnoreCase("how many departments")|| comments.equalsIgnoreCase("noof departments are there")) {

				List<DeptTL> depts = db.getAllDept();
				if (depts != null) {
					sb.append(depts.size() + " departments are there");

				} else {
					sb.append("No departments");
				}
			}

			else if (comments.equalsIgnoreCase("where is jntuacea")
					|| comments.equalsIgnoreCase("where is jntua")
					|| comments.equalsIgnoreCase("where")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getAddress());

				} else {
					sb.append("No Address Found");
				}

			} else if (comments.equalsIgnoreCase("who is hostel manager")||comments.equalsIgnoreCase("hostel manager")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getHostelManager());

				} else {
					sb.append("No Data Found");
				}

			} else if (comments
					.equalsIgnoreCase("who is controller of examinations")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getCe());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.equalsIgnoreCase("when jntua was estsblished")
					|| comments.equalsIgnoreCase("when jntuacea was estsblished ")
					|| comments.equalsIgnoreCase("when")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getEstaYear());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.equalsIgnoreCase("who is the principal of colllege")
					|| comments.equalsIgnoreCase("who is the principal")
					|| comments.equalsIgnoreCase("principal")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getPrincipal());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments
					.equalsIgnoreCase("who is the vice-principal of college")
					|| comments.equalsIgnoreCase("who is the vice-principal ")
					|| comments.equalsIgnoreCase("vice-principal ")
					|| comments
							.equalsIgnoreCase("who is the viceprincipal of college")
					|| comments.equalsIgnoreCase("who is the viceprincipal")
					|| comments.equalsIgnoreCase("viceprincipal")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getVicePrincipal());

				} else {
					sb.append("No Data Found");
				}

			} else if (comments.equalsIgnoreCase("no.of Affliated colleges")
					|| comments.equalsIgnoreCase("no.of Affliated  ")
					|| comments.equalsIgnoreCase("no.of Affliated ")
					|| comments.equalsIgnoreCase("noof Affliated colleges")
					|| comments.equalsIgnoreCase("noof Affliated ")
					|| comments.equalsIgnoreCase("noof Affliated")
					|| comments.equalsIgnoreCase("no of Affliated colleges")
					|| comments.equalsIgnoreCase("no of Affliated")
					|| comments.equalsIgnoreCase("no of Affliated")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getNoofaff());

				} else {
					sb.append("No Data Found");
				}

			} else if (comments.equalsIgnoreCase("who is the vice-chancellor")
					|| comments.equalsIgnoreCase("who is the vice chancellor")
					|| comments.equalsIgnoreCase("who is the vc")
					|| comments.equalsIgnoreCase("vc")) {

				if (collegeTL != null) {
					sb.append(collegeTL.getVc());

				} else {
					sb.append("No Data Found");
				}

			} 

			else if (comments.contains("how many companies visited")
					|| comments.contains("visited")) {
				String year = comments.replaceAll("[^0-9]", "");
				List<PlacementTL> placements = chatDAO.getPlacements(year);

				if (placements != null && placements.size() > 0) {
					Set<String> s = new HashSet<String>();
					ListIterator<PlacementTL> li = placements.listIterator();
					while (li.hasNext()) {
						PlacementTL placementTL = li.next();
						s.add(placementTL.getCname());
					}
					sb.append(s.size());

				} else {
					sb.append("No Data Found");
				}

			} else if (comments.contains("how many students are placed")
					|| comments.contains("placed")) {
				String year = comments.replaceAll("[^0-9]", "");
				List<PlacementTL> placements = chatDAO.getPlacements(year);

				if (placements != null && placements.size() > 0) {

					sb.append(placements.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("list the companies visited to college")
					|| comments.contains("list the companies")) {
				String year = comments.replaceAll("[^0-9]", "");
				System.out.println("yaer....");
				System.out.println("year"+year);
				List<PlacementTL> placements = (year != null&&!year.trim().equals("") )? chatDAO
						.getPlacements(year) : chatDAO.getPlacements();

				if (placements != null && placements.size() > 0) {
					Set<String> s = new HashSet<String>();
					ListIterator<PlacementTL> li = placements.listIterator();
					while (li.hasNext()) {
						PlacementTL placementTL = li.next();
						s.add(placementTL.getCname());
					}
					sb.append(s);

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("what is the average package")
					|| comments.contains("what is the avg package")) {
				String year = comments.replaceAll("[^0-9]", "");
				List<PlacementTL> placements = year != null ? chatDAO
						.getPlacements(year) : chatDAO.getPlacements();

				if (placements != null && placements.size() > 0) {

					ListIterator<PlacementTL> li = placements.listIterator();
					double tot = 0;
					while (li.hasNext()) {
						PlacementTL placementTL = li.next();
						tot = tot + placementTL.getCtc();
					}
					double avg = tot / placements.size();
					sb.append(avg);

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("list out the faculty of the")
					|| comments.contains("list the faculty of the")||comments.contains("faculty of the")) {
				String deptName = "";
				if (comments.contains("list out the faculty of the")) {
					deptName = comments.substring(
							"list out the faculty of the".length()).trim();
				} else if (comments.contains("list the faculty of the")) {
					deptName = comments.substring(
							"list the faculty of the".length()).trim();
				}
				else if (comments.contains("faculty of the")) {
					deptName = comments.substring(
							"faculty of the".length()).trim();
				}
				List<UserTL> users = chatDAO.getUsers(deptName.toUpperCase());

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("what is the designation of")

			) {
				String factName = "";
				if (comments.contains("what is the designation of")) {
					factName = comments.substring(
							"what is the designation of".length()).trim();
				}

				List<UserTL> users = chatDAO.getUsersFn(factName);

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("what is the emailid of the")

			) {
				String factName = "";
				if (comments.contains("what is the emailid of the")) {
					factName = comments.substring(
							"what is the emailid of the".length()).trim();
				}

				List<UserTL> users = chatDAO.getUsersFn(factName);

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}

				} else {
					sb.append("No Data Found");
				}

			}
			else if (comments.contains("who is the hod of the")) {
				String deptName = "";
				if (comments.contains("who is the hod of the")) {
					deptName = comments.substring(
							"who is the hod of the".length()).trim();
				}
				List<UserTL> users = chatDAO.getUsersType("HOD", deptName.toUpperCase());

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}
					//sb.append("Total no.of Professors are" + users.size());

				} else {
					sb.append("No Data Found");
				}
				
				

			}

			else if (comments.contains("how many professors are there in the")) {
				String deptName = "";
				if (comments.contains("how many professors are there in the")) {
					deptName = comments.substring(
							"how many professors are there in the".length()).trim();
				}
				List<UserTL> users = chatDAO.getUsersType("Professor", deptName.toUpperCase());

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}
					sb.append("Total no.of Professors are" + users.size());

				} else {
					sb.append("No Data Found");
				}
				
				

			} else if (comments
					.contains("how many Asst-professors are there in the")) {
				String deptName = "";
				if (comments.contains("how many Asst-professors are there in the")) {
					deptName = comments.substring(
							"how many Asst-professors are there in the".length())
							.trim();
				}
				List<UserTL> users = chatDAO.getUsersType("Assistant Professor",
						deptName.toUpperCase());

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}

					sb.append("Total no.of Assistant Professors are" + users.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments
					.contains("how many Associate Professor are there in the")) {
				String deptName = "";
				if (comments
						.contains("how many Associate Professor are there in the")) {
					deptName = comments.substring(
							"how many Associate Professor are there in the"
									.length()).trim();
				}
				List<UserTL> users = chatDAO.getUsersType("Associate Professor",
						deptName.toUpperCase());

				if (users != null && users.size() > 0) {

					ListIterator<UserTL> li = users.listIterator();
					while (li.hasNext()) {
						UserTL userTL = li.next();
						sb.append(userTL + "<br>");
					}

					sb.append("Total no.of Associate Professors are" + users.size());

				} else {
					sb.append("No Data Found");
				}

			} else if (comments.contains("who is the student  with the reg_num")) {
				String regNo = "";
				if (comments.contains("who is the student  with the reg_num")) {
					regNo = comments.substring(
							"who is the student  with the reg_num".length())
							.trim();
				}
				StudentTL studentTL = db.getStudent(regNo);

				if (studentTL != null) {

					sb.append(studentTL);

				} else {
					sb.append("No Data Found");
				}

			}
			
			 else if (comments.contains("what is the results of student  with the reg_num")) {
					String regNo = "";
					if (comments.contains("what is the results of student  with the reg_num")) {
						regNo = comments.substring(
								"what is the results of student  with the reg_num".length())
								.trim();
					}
					StudentTL studentTL = db.getStudent(regNo);

					if (studentTL != null) {

						sb.append("Student results garde is "+studentTL.getGrade());

					} else {
						sb.append("No Data Found");
					}

				}
			else if (comments
					.contains("what is the attendence of the student  with the reg_num")) {
				String regNo = "";
				if (comments
						.contains("what is the attendence of the student  with the reg_num")) {
					regNo = comments.substring(
							"what is the attendence of the student  with the reg_num"
									.length()).trim();
				}
				StudentTL studentTL = db.getStudent(regNo);

				if (studentTL != null) {
					Integer totalNo = studentTL.getTotalNoofClasses();
					Integer totalNoAtt = studentTL.getTotalNoofClassesAtten();

					double per = (totalNoAtt * 100.0) / totalNo;

					sb.append(studentTL);
					sb.append("<br>Total No.of classes conducted " + totalNo);
					sb.append("<br>Total No.of classes Attented " + totalNoAtt);
					sb.append("<br>Percentage " + per);

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("how many students are there in")||comments.contains("strength of the")) {
				String deptName = "";
				if (comments.contains("how many students are there in")) {
					deptName = comments.substring(
							"how many students are there in".length()).trim();
				}
				if (comments.contains("strength of the")) {
					deptName = comments.substring(
							"strength of the".length()).trim();
				}
				List<StudentTL> students = chatDAO.getStudents(deptName.toUpperCase());

				if (students != null && students.size() > 0) {

					ListIterator<StudentTL> li = students.listIterator();
					while (li.hasNext()) {
						StudentTL studentTL = li.next();
						sb.append(studentTL + "<br>");
					}

					sb.append("Total no.of students are" + students.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("how many students are there in")) {
				String deptName = "";
				if (comments.contains("how many students are there in")) {
					deptName = comments.substring(
							"how many students are there in".length()).trim();
				}
				List<StudentTL> students = chatDAO.getStudents(deptName.toUpperCase());

				if (students != null && students.size() > 0) {

					ListIterator<StudentTL> li = students.listIterator();
					while (li.hasNext()) {
						StudentTL studentTL = li.next();
						sb.append(studentTL + "<br>");
					}

					sb.append("Total no.of students are" + students.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("list out the subjects of the")) {
				String courseName = "";
				if (comments.contains("list out the subjects of the")) {
					courseName = comments.substring(
							"list out the subjects of the".length()).trim();
				}
				List<SubjectTL> subjects = chatDAO.getSubjects(courseName);

				if (subjects != null && subjects.size() > 0) {

					ListIterator<SubjectTL> li = subjects.listIterator();
					while (li.hasNext()) {
						SubjectTL subjectTL = li.next();
						sb.append(subjectTL + "<br>");
					}

					sb.append("Total no.of subjects are" + subjects.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else if (comments.contains("what are icon activities")) {

				List<ActivityTL> acts = chatDAO.getActs();

				if (acts != null && acts.size() > 0) {

					ListIterator<ActivityTL> li = acts.listIterator();
					while (li.hasNext()) {
						ActivityTL activityTL = li.next();
						sb.append(activityTL + "<br>");
					}

					sb.append("Total no.of ICON Activities are" + acts.size());

				} else {
					sb.append("No Data Found");
				}

			}

			else {
				sb.append("Did not recognize your question");
			}
		}

		return sb.toString();

	}
	@RequestMapping("forgot")
	public String showforgot(HttpServletRequest request) {

		return "forgot";

	}

	@RequestMapping("studentchange")
	public String studentchange(HttpServletRequest request) {

		return "studentchange";

	}

	@RequestMapping("staffchange")
	public String staffchange(HttpServletRequest request) {

		return "staffchange";

	}

	@RequestMapping("logout")
	public String showLogout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:login.htm";

	}

	@RequestMapping("studentexamcomplete")
	public String studentexamcomplete(HttpServletRequest request) {

		return "studentexamcomplete";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String login(LoginForm loginForm, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		if (loginForm != null
				&& loginForm.getUsername().trim().equalsIgnoreCase("admin")
				&& loginForm.getPassword().trim().equals("admin")) {
			return "redirect:adminhome.htm";
		} else {
			Map<String, Object> m = db.getUser(loginForm.getUsername(),
					loginForm.getPassword());
			if (m != null) {
				HttpSession session = request.getSession();
				session.setAttribute("AuthUser", m);
				return "redirect:staffhome.htm";
			} else {
				StudentTL student = db.getStudent(loginForm.getUsername(),
						loginForm.getPassword());
				if (student != null) {
					HttpSession session = request.getSession();
					session.setAttribute("AuthStudent", student);
					return "redirect:studenthome.htm";

				} else {
					request.setAttribute("errMsg", "Invalid username/password");
				}
			}
		}

		return "index";

	}

	@RequestMapping(value = "addcollege", method = RequestMethod.POST)
	public String addcollege(HttpServletRequest request, CollegeTL collegeTL) {
		chatDAO.insertCollege(collegeTL);
		return "redirect:adminhome.htm";

	}

	@RequestMapping(value = "updatecollege.htm", method = RequestMethod.POST)
	public String updatecollege(HttpServletRequest request, CollegeTL collegeTL) {
		chatDAO.updateCollege(collegeTL);
		return "redirect:adminhome.htm";

	}

	@RequestMapping(value = "schangepassword", method = RequestMethod.POST)
	public String schnagepassword(HttpServletRequest response,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		Map<String, Object> m = (Map<String, Object>) session
				.getAttribute("AuthUser");
		String password = request.getParameter("password");
		m.put(password, password);
		db.updateUser((Integer) m.get("userId"), password);

		return "redirect:staffhome.htm?msg=successfully changed";

	}

	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public String chnagepassword(HttpServletRequest response,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		StudentTL studentTL = (StudentTL) session.getAttribute("AuthStudent");
		String password = request.getParameter("password");
		studentTL.setPassword(password);
		db.updateStudent(studentTL.getStudentId(), password);

		return "redirect:studenthome.htm?msg=successfully changed";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/forgot")
	public String forgot(LoginForm loginForm, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> m = db.getUser(loginForm.getUsername());
		if (m != null) {
			try {
				new MailService().send(m.get("emailId").toString(), "Password",
						m.get("password").toString());
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			StudentTL student = db.getStudent(loginForm.getUsername());
			if (student != null) {
				try {
					new MailService().send(student.getEmailId(), "Password",
							student.getPassword());
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				request.setAttribute("errMsg", "Invalid username");
			}
		}

		return "forgot";

	}

	@RequestMapping("adminhome")
	public String showAdminHome(HttpServletRequest request) {
		CollegeTL collegeTL = chatDAO.getCollege();
		if (collegeTL != null) {
			request.setAttribute("collegeTL", collegeTL);
			return "adminhome";

		} else {

			return "addcollege";
		}

	}

	@RequestMapping("studenthome")
	public String showstudenthome(HttpServletRequest request) {

		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		// List<SubjectTL> subjects = db.getSubjects(student.getCourseId(),
		// student.getSemester());
		List<SubjectTL> subjects = db.getSubjects();

		request.setAttribute("subjects", subjects);
		return "studenthome";

	}

	@RequestMapping("staffhome")
	public String showStaffHome(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Map<String, Object> m = (Map<String, Object>) session
				.getAttribute("AuthUser");

		Integer userId = (Integer) m.get("userId");
		List<SubjectTL> sujects = db.getStaffSubjects(userId);
		request.setAttribute("subjects", sujects);

		return "staffhome";

	}

	@RequestMapping("admindept")
	public String showAdminDept(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "admindept";

	}

	@RequestMapping("adminactivitys")
	public String showAdminAct(HttpServletRequest request) {
		List<ActivityTL> activitys = chatDAO.getActs();
		request.setAttribute("activitys", activitys);

		return "adminactivitys";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adddept")
	public String addDept(DeptTL dept, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		db.addDept(dept);

		return "redirect:admindept.htm";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addact")
	public String addact(ActivityTL activityTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		chatDAO.insertActivity(activityTL);

		return "redirect:adminactivitys.htm";

	}

	@RequestMapping("adminaddfaculty")
	public String showAddFac(HttpServletRequest request) {

		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminaddfaculty";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addfaculty")
	public String addFaculty(UserTL userTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		Random r = new Random();

		String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			password.append(alphabet.charAt(r.nextInt(alphabet.length())));
		} //
		userTL.setPassword(password.toString());

		db.addUser(userTL);

		try {
			new MailService()
					.send(userTL.getEmailId(), "Chat",
							"your username" + userTL.getUserName()
									+ " password" + userTL.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:adminfaculty.htm";

	}

	@RequestMapping("adminfaculty")
	public String showFac(HttpServletRequest request) {
		List<Map<String, Object>> users = db.getAllUsers();
		request.setAttribute("users", users);

		return "adminfaculty";

	}

	@RequestMapping("admincourses")
	public String showCourses(HttpServletRequest request) {
		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);

		return "admincourses";

	}

	@RequestMapping("adminaddcourses")
	public String showAddCourse(HttpServletRequest request) {

		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminaddcourses";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddcourses")
	public String addCourse(CourseTL courseTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {

		db.insertCourse(courseTL);
		return "redirect:admincourses.htm";

	}

	@RequestMapping("/adminaddsubject")
	public String showAddSubject(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "adminaddsubject";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddsubject")
	public String addSubject(SubjectTL subjectTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {

		db.addSubject(subjectTL);
		return "redirect:adminsubjects.htm";

	}

	@RequestMapping("adminsubjects.htm")
	public String showSubjects(HttpServletRequest request) {

		List<SubjectTL> subjects = db.getAllSubjects();
		request.setAttribute("subjects", subjects);

		return "adminsubjects";

	}

	@RequestMapping("adminranks.htm")
	public String adminranks(HttpServletRequest request) {

		try {
			db.getRanks();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminranks";

	}

	@RequestMapping("adminallotsubject")
	public String adminallotsubject(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminallotsubject";

	}

	@RequestMapping("adminallotedsubjects")
	public String adminallotedsubjects(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminallotedsubjects";

	}

	@RequestMapping(value = "/adminaddstaffsubject", method = RequestMethod.POST)
	public String adminaddstaffsubject(HttpServletRequest request) {

		StaffSubjectTL staffSubjectTL = new StaffSubjectTL();
		staffSubjectTL
				.setStaffId(new Integer(request.getParameter("facultyId")));
		staffSubjectTL.setSubjectId(new Integer(request
				.getParameter("subjectId")));

		staffSubjectTL.setStatus("Active");
		db.addStaffSubject(staffSubjectTL);

		return "redirect:adminallotsubject.htm?msg=successfully alloted";

	}

	@RequestMapping("/adminaddstudent")
	public String showAddStudent(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "adminaddstudent";

	}

	@RequestMapping("/adminaddplacement")
	public String showAddStudentPlace(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "adminaddplacement";

	}

	@RequestMapping(value = "/adminaddplacement", method = RequestMethod.POST)
	public String addStudentPlace(HttpServletRequest request,
			PlacementTL placementTL) {

		chatDAO.insertPlacement(placementTL);

		return "redirect:adminplacements.htm";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddstudent")
	public String addStudent(StudentTL studentTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		if (db.getStudent(studentTL.getRegNo()) == null) {
			Random r = new Random();

			String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
			StringBuffer password = new StringBuffer();
			for (int i = 0; i < 8; i++) {
				password.append(alphabet.charAt(r.nextInt(alphabet.length())));
			} //
			studentTL.setPassword(password.toString());
			try {
				new MailService().send(studentTL.getEmailId(), "Chat",
						"your username" + studentTL.getRegNo() + " password"
								+ studentTL.getPassword());
			} catch (Exception e) {
				// TODO: handle exception
			}

			db.addStudent(studentTL);
			return "redirect:adminstudents.htm";
		} else {
			return "redirect:adminaddstudent.htm?msg=RegNo Already Exist";
		}

	}

	@RequestMapping("adminplacements.htm")
	public String showStudentPlacements(HttpServletRequest request) {

		List<PlacementTL> placements = chatDAO.getPlacements();
		request.setAttribute("placements", placements);

		return "adminplacements";

	}

	@RequestMapping("adminstudents.htm")
	public String showStudent(HttpServletRequest request) {

		List<StudentTL> students = db.getAllStudents();
		request.setAttribute("students", students);

		return "adminstudents";

	}

	@RequestMapping("deletefac.htm")
	public String deletefac(HttpServletRequest request) {
		db.deleteUser(new Integer(request.getParameter("fid")));
		return "redirect:adminfaculty.htm";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getsubjectsstaff")
	public @ResponseBody
	String getCheckStaffSubjectAgencyInfoInJSON(
			@RequestParam("cid") Integer courseId) {

		int subjectCount = 0;
		int staffCount = 0;
		String sb = "";
		String sf = "";
		try {
			List<SubjectTL> subjects = db.getCourseSubjects(courseId);
			List<Map<String, Object>> staff = db.getDeptUsers(courseId);
			ObjectMapper m = new ObjectMapper();
			sb = m.writeValueAsString(subjects);
			sf = m.writeValueAsString(staff);
			if (subjects != null) {
				subjectCount = subjects.size();
			}
			if (staff != null) {
				staffCount = staff.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "{\"subjectCount\":\"" + subjectCount + "\",\"subjects\":\""
				+ sb + "\",\"staffCount\":\"" + staffCount
				+ "\",\"subjects\":\"" + sf + "\"}";
	}

	@RequestMapping(value = "facultysubject", method = RequestMethod.POST)
	public String facultysubject(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);

		return "facultysubject";

	}

	@RequestMapping(value = "studentsubject", method = RequestMethod.POST)
	public String studentsubject(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		List<Map<String, Object>> m = db.getStudentAns(new Integer(subjId),
				student.getStudentId());
		if (m != null && m.size() > 0) {
			request.setAttribute("already", m);
			Integer rank = db.getRank(student.getStudentId(), new Integer(
					subjId));
			request.setAttribute("rank", rank);

			List<Map<String, Object>> ma = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section A");
			List<Map<String, Object>> mb = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section B");
			List<Map<String, Object>> mc = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section C");

			request.setAttribute("alreadya", ma);
			request.setAttribute("alreadyb", mb);
			request.setAttribute("alreadyc", mc);
		}

		return "studentsubject";

	}

	@RequestMapping(value = "studentsubject", method = RequestMethod.GET)
	public String studentsubjects(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		List<Map<String, Object>> m = db.getStudentAns(new Integer(subjId),
				student.getStudentId());

		if (m != null && m.size() > 0) {
			request.setAttribute("already", m);

			List<Map<String, Object>> ma = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section A");
			List<Map<String, Object>> mb = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section B");
			List<Map<String, Object>> mc = db.getStudentAns(
					new Integer(subjId), student.getStudentId(), "Section C");

			request.setAttribute("alreadya", ma);
			request.setAttribute("alreadyb", mb);
			request.setAttribute("alreadyc", mc);
		}

		return "studentsubject";

	}

	@RequestMapping("facultyaddquestions")
	public String facultyaddquestions(HttpServletRequest request) {
		String sid = request.getParameter("sid");

		return "facultyaddquestions";

	}

	@RequestMapping("studentexam")
	public String studentexam(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		return ex(request, sid, "Section A");

	}

	public String ex(HttpServletRequest request, int sid, String stype) {

		List<QuestionTl> questions = db.getQuestion("Active", sid, stype);
		long seed = System.nanoTime();
		Collections.shuffle(questions, new Random(seed));
		request.setAttribute("questions", questions);

		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());
		request.setAttribute("stype", stype);

		return "studentexam";
	}

	@RequestMapping("studentmockexam")
	public String studentmockexam(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		List<QuestionTl> questions = db.getMockQuestion("Active", sid);
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());

		return "studenmocktexam";

	}

	@RequestMapping(value = "submitexam", method = RequestMethod.POST)
	public String submitexam(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String stype = request.getParameter("stype");
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		String sid = request.getParameter("sid");
		List<StudentAnsTL> queAns = new ArrayList<>();
		for (int index = 1; index <= 20; index++) {
			String q = request.getParameter("q" + index);
			if (q != null) {
				String qtype = request.getParameter("qtype" + index);
				String ans = "";
				if (qtype != null && qtype.equalsIgnoreCase("multiple")) {
					String[] mans = request.getParameterValues("a" + index);
					if (mans != null && mans.length > 0) {
						int indx = 0;
						for (String m : mans) {
							if (indx == 0) {
								ans += m;
							} else {
								ans += "," + m;
							}
							indx++;

						}
					} else {
						ans = null;
					}

				} else {
					ans = request.getParameter("a" + index);
				}
				StudentAnsTL studentAns = new StudentAnsTL();
				studentAns.setAns(ans);
				studentAns.setCreatedon(new Timestamp(new java.util.Date()
						.getTime()));
				studentAns.setQid(new Integer(q));
				studentAns.setSid(new Integer(sid));
				studentAns.setStudentId(student.getStudentId());

				queAns.add(studentAns);

			}

		}
		db.addStudentAns(queAns);
		if (session.getAttribute("sec") == null) {

			session.setAttribute("sec", stype);

		}
		String sec = session.getAttribute("sec").toString();
		System.out.println(sec);
		if (sec.equalsIgnoreCase("Section A")) {
			stype = stype + "Section B";
			session.setAttribute("sec", stype);
			return ex(request, new Integer(sid), "Section B");

		} else if (sec.equalsIgnoreCase("Section ASection B")) {
			stype = stype + "Section C";
			session.setAttribute("sec", stype);
			return ex(request, new Integer(sid), "Section C");
		} else {

			session.removeAttribute("sec");
			return "redirect:studentsubject.htm?subjId=" + sid;
		}

	}

	@RequestMapping(value = "submitmockexam", method = RequestMethod.POST)
	public String submockmitexam(HttpServletRequest request) {
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		String sid = request.getParameter("sid");
		/*
		 * List<StudentAnsTL> queAns = new ArrayList<>(); for (int index = 1;
		 * index <= 20; index++) { String q = request.getParameter("q" + index);
		 * if (q != null) { String qtype = request.getParameter("qtype" +
		 * index); String ans = ""; if (qtype != null &&
		 * qtype.equalsIgnoreCase("multiple")) { String[] mans =
		 * request.getParameterValues("a" + index); if (mans != null &&
		 * mans.length > 0) { for (String m : mans) { ans += m; } }
		 * 
		 * } else { ans = request.getParameter("a" + index); } StudentAnsTL
		 * studentAns = new StudentAnsTL(); studentAns.setAns(ans);
		 * studentAns.setCreatedon(new Timestamp(new
		 * java.util.Date().getTime())); studentAns.setQid(new Integer(q));
		 * studentAns.setSid(new Integer(sid));
		 * studentAns.setStudentId(student.getStudentId());
		 * 
		 * queAns.add(studentAns);
		 * 
		 * }
		 * 
		 * } db.addStudentMockAns(queAns);
		 * 
		 * 
		 * SubjectTL subject = db.getSubject(new Integer(sid));
		 * request.setAttribute("subject", subject);
		 * 
		 * List<Map<String, Object>> m=db.getStudentMockAns( new
		 * Integer(sid),student.getStudentId()); if(m!=null&&m.size()>0) {
		 * request.setAttribute("already", m); }
		 * 
		 * db.updateStudentMockAns(student.getStudentId(), "DeActive");
		 */

		List<QuestionTl> questions = db.getMockQuestion("Active", new Integer(
				sid));
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(new Integer(sid));
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());

		return "mockresult";

	}

	@RequestMapping("studentresult")
	public String studentresult(HttpServletRequest request) {
		String sid = request.getParameter("sid");
		List<QuestionTl> questions = db.getQuestion("Active", new Integer(sid));
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		// List<StudentAnsTL> studentAns=db.getStudentAns(new Integer(sid),
		// student.getStudentId());
		request.setAttribute("questions", questions);
		// request.setAttribute("studentAns", studentAns);

		return "studentresult";

	}

	@RequestMapping("facultyviewquestions")
	public String facultyviewquestions(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));

		List<QuestionTl> questions = db.getQuestion("Active", sid);
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);

		return "facultyviewquestions";

	}

	@RequestMapping("vq")
	public String vq(HttpServletRequest request) {
		int qid = Integer.parseInt(request.getParameter("qid"));

		QuestionTl question = db.getQuestion(qid);
		request.setAttribute("question", question);

		return "vq";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/facultyaddquestions")
	public String addFacultyAddQuestions(QuestionTl questionTl,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		db.addQuestion(questionTl);
		return "redirect:facultyaddquestions.htm?sid=" + questionTl.getSid();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getdeptfaculty")
	public @ResponseBody
	String getCheckAgencyDetailsJSON(@RequestParam("deptId") Integer deptId) {
		String result = null;

		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> users = db.getDeptFac(deptId);
		List<Map<String, Object>> courses = db.getDeptCourses(deptId);

		Map<String, Object> m = new HashMap<>();
		m.put("users", users);
		m.put("courses", courses);

		if (users != null) {
			try {

				result = mapper.writeValueAsString(m);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getcoursesubject")
	public @ResponseBody
	String getCheckSubjectyDetailsJSON(
			@RequestParam("courseId") Integer courseId,
			@RequestParam("sem") String sem) {
		String result = null;
		System.out.println(courseId + " " + sem);

		ObjectMapper mapper = new ObjectMapper();

		List<SubjectTL> subjects = db.getSubjects(courseId, sem);

		if (subjects != null) {
			try {

				result = mapper.writeValueAsString(subjects);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getfacsubject")
	public @ResponseBody
	String getfacsubject(@RequestParam("courseId") Integer courseId,
			@RequestParam("sem") String sem,
			@RequestParam("facultyId") Integer facultyId) {
		String result = null;
		System.out.println(courseId + " " + sem);

		ObjectMapper mapper = new ObjectMapper();

		List<SubjectTL> subjects = db.getSubjects(courseId, sem);

		if (subjects != null) {
			try {

				result = mapper.writeValueAsString(subjects);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/postexampattern")
	public @ResponseBody
	String postexampattern(HttpServletRequest request) {
		System.out.println(request.getParameter("subjectId"));

		String qpattern = request.getParameter("qpattern");
		Integer subjectId = new Integer(request.getParameter("subjectId"));

		db.updateSubjectExamPattern(qpattern, subjectId);
		System.out.println("ex");

		return "{s:'success'";
	}

	@RequestMapping("/studentregs")
	public String showRegStudent(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "studentreg";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/studentregs")
	public String regStudent(StudentTL studentTL, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		Random r = new Random();

		String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			password.append(alphabet.charAt(r.nextInt(alphabet.length())));
		} //
		studentTL.setPassword(password.toString());
		try {
			new MailService().send(studentTL.getEmailId(), "Chat",
					"your username" + studentTL.getRegNo() + " password"
							+ studentTL.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
		}

		db.addStudent(studentTL);
		return "redirect:login.htm?msg=Your successfully registered";

	}

}
