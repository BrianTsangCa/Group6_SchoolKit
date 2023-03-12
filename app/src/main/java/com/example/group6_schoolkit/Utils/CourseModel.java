package com.example.group6_schoolkit.Utils;

public class CourseModel {
    private int id;
    private int courseNo;
    private String courseName;
    private String courseDesc;
    private String instructor;
    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public CourseModel(int courseNo, String courseName, String courseDesc, String instructor, int taskId) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.instructor = instructor;
        this.taskId=taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public CourseModel() {
    }
}
