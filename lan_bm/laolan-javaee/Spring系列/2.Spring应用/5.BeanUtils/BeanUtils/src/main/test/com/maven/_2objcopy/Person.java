package com.maven._2objcopy;

import java.util.Date;

public class Person {
    private Double avgScore;
    private String courseName;
    private int num;
    private Date createTime;


    public Double getAvgScore() {
        return avgScore;
    }
    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Person [avgScore=" + avgScore + ", courseName=" + courseName
                + ", num=" + num + ", createTime=" + createTime + "]";
    }

}
