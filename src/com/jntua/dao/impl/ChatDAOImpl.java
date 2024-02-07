package com.jntua.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jntua.dao.ChatDAO;
import com.jntua.dao.model.ActivityTL;
import com.jntua.dao.model.ChatUserTL;
import com.jntua.dao.model.CollegeTL;
import com.jntua.dao.model.CourseTL;
import com.jntua.dao.model.DeptTL;
import com.jntua.dao.model.PlacementTL;
import com.jntua.dao.model.StudentTL;
import com.jntua.dao.model.SubjectTL;
import com.jntua.dao.model.UserTL;
import com.jntua.dao.util.CustomHibernateDaoSupport;

@Repository
public class ChatDAOImpl extends CustomHibernateDaoSupport implements ChatDAO {

	@Override
	public CollegeTL getCollege() {
		List<CollegeTL> colleges = getHibernateTemplate().loadAll(
				CollegeTL.class);
		return colleges != null && colleges.size() > 0 ? colleges.get(0) : null;
	}

	@Override
	public Integer insertCollege(CollegeTL collegeTL) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(collegeTL);
	}

	@Override
	public void updateCollege(CollegeTL collegeTL) {
		getHibernateTemplate().update(collegeTL);

	}

	@Override
	public Integer insertPlacement(PlacementTL placementTL) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(placementTL);
	}

	@Override
	public List<PlacementTL> getPlacements() {
		// TODO Auto-generated method stub
		return (List<PlacementTL>) getHibernateTemplate().loadAll(
				PlacementTL.class);
	}

	@Override
	public Integer insertActivity(ActivityTL activityTL) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(activityTL);
	}

	@Override
	public List<ActivityTL> getActs() {
		// TODO Auto-generated method stub
		return (List<ActivityTL>) getHibernateTemplate().loadAll(
				ActivityTL.class);
	}

	@Override
	public Integer insertChat(ChatUserTL chatUserTL) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(chatUserTL);
	}

	@Override
	public List<PlacementTL> getPlacements(String year) {
		// TODO Auto-generated method stub
		return (List<PlacementTL>)getHibernateTemplate().find("from PlacementTL where year=?",new Object[]{year});
	}

	@Override
	public List<UserTL> getUsers(String deptName) {
		System.out.println(deptName);
		Integer deptId=0;
		List<DeptTL> depts=(List<DeptTL>)getHibernateTemplate().find("from DeptTL where deptName=?",new Object[]{deptName});
		if(depts!=null&&depts.size()>0)
		{
			deptId=depts.get(0).getDeptId();
		}
		System.out.println(deptId);
		
		return (List<UserTL>)getHibernateTemplate().find("from UserTL where deptId=?",new Object[]{deptId});
	}

	@Override
	public List<UserTL> getUsersFn(String facName) {
		// TODO Auto-generated method stub
		return (List<UserTL>)getHibernateTemplate().find("from UserTL where firstName like '%"+facName+"%' or lastName like '%"+facName+"%' ");
	}

	@Override
	public List<UserTL> getUsersType(String desg) {
		// TODO Auto-generated method stub
		return (List<UserTL>)getHibernateTemplate().find("from UserTL where desg=?",new Object[]{desg});
	}

	@Override
	public List<UserTL> getUsersType(String desg, String deptName) {
		// TODO Auto-generated method stub
		Integer deptId=0;
		List<DeptTL> depts=(List<DeptTL>)getHibernateTemplate().find("from DeptTL where deptName=?",new Object[]{deptName});
		if(depts!=null&&depts.size()>0)
		{
			deptId=depts.get(0).getDeptId();
		}
		return (List<UserTL>)getHibernateTemplate().find("from UserTL where desg=? and deptId=?",new Object[]{desg,deptId});
	}

	@Override
	public List<StudentTL> getStudents(String deptName) {
		Integer deptId=0;
		Integer courseId=0;
		List<DeptTL> depts=(List<DeptTL>)getHibernateTemplate().find("from DeptTL where deptName=?",new Object[]{deptName});
		if(depts!=null&&depts.size()>0)
		{
			deptId=depts.get(0).getDeptId();
		}
		List<CourseTL> cs=(List<CourseTL>)getHibernateTemplate().find("from CourseTL where deptId=?",new Object[]{deptId});
		if(cs!=null&&cs.size()>0)
		{
			courseId=cs.get(0).getCourseId();
		}
		System.out.println(courseId+" cid");
		System.out.println(deptId+" did");
		return (List<StudentTL>)getHibernateTemplate().find("from StudentTL where courseId in(select courseId from CourseTL where deptId=?)",new Object[]{deptId});
	}

	@Override
	public List<SubjectTL> getSubjects(String courseName) {
		// TODO Auto-generated method stub
		return (List<SubjectTL>)getHibernateTemplate().find("from SubjectTL where courseId in(select courseId from CourseTL where courseName=?)",new Object[]{courseName});
	}

}
