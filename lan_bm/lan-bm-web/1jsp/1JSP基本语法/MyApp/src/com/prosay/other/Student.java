package com.prosay.other;

public class Student {
  private String studentName;
  private boolean man = true;

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setMan(boolean man) {
    this.man = man;
  }

  public boolean isMan() {
    return man;
  }
}
