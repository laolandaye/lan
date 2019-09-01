package com.maven._2objcopy;

public class Student extends Person{
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    @Override
    public String toString() {
        return "Student [studentName=" + studentName + ", getAvgScore()="
                + getAvgScore() + ", getCourseName()=" + getCourseName()
                + ", getNum()=" + getNum() + ", getCreateTime()="
                + getCreateTime() + ", toString()=" + super.toString()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + "]";
    }


}
