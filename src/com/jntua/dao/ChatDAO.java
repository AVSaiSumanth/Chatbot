package com.jntua.dao;

import java.util.List;

import com.jntua.dao.model.ActivityTL;
import com.jntua.dao.model.ChatUserTL;
import com.jntua.dao.model.CollegeTL;
import com.jntua.dao.model.PlacementTL;
import com.jntua.dao.model.StudentTL;
import com.jntua.dao.model.SubjectTL;
import com.jntua.dao.model.UserTL;

public interface ChatDAO {

	public CollegeTL getCollege();
	public Integer insertCollege(CollegeTL collegeTL);
	public void updateCollege(CollegeTL collegeTL);
	public Integer insertPlacement(PlacementTL placementTL);
	public List<PlacementTL> getPlacements();
	public Integer insertActivity(ActivityTL activityTL);
	public List<ActivityTL> getActs();
	public Integer insertChat(ChatUserTL chatUserTL);
	public List<PlacementTL> getPlacements(String year);
	public List<UserTL> getUsers(String deptName);
	public List<UserTL> getUsersFn(String facName);
	public List<UserTL> getUsersType(String desg);
	public List<UserTL> getUsersType(String desg,String deptName);
	public List<StudentTL> getStudents(String deptName);
	public List<SubjectTL> getSubjects(String courseName);
}
