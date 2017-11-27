package kr.co.cgb.academycommunity.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by PC on 2017-11-26.
 */

public class Lecture implements Serializable {

    private int id;
    private String lectureName;
    private Calendar startDate;
    private Calendar endDate;

    private User isTeacherData;

    public Lecture() {
    }

    public Lecture(int id, String lectureName, Calendar startDate, Calendar endDate, User isTeacherData) {
        this.id = id;
        this.lectureName = lectureName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isTeacherData = isTeacherData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public User getIsTeacherData() {
        return isTeacherData;
    }

    public void setIsTeacherData(User isTeacherData) {
        this.isTeacherData = isTeacherData;
    }
}
