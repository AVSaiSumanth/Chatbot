package com.jntua.dao.model;

import java.sql.Timestamp;

/**
 * Created by system1 on 4/3/2017.
 */

public class ClassRoom {
    private Integer classRoomId;
    private  Integer subjectId;
    private Timestamp classTime;
    private Integer staffId;
    public Integer getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Integer classRoomId) {
        this.classRoomId = classRoomId;
    }



    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }



    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }



    public Timestamp getClassTime() {
        return classTime;
    }

    public void setClassTime(Timestamp classTime) {
        this.classTime = classTime;
    }



}
