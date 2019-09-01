package com.lan._1utils.maven._1mapobj;

import java.util.Arrays;
import java.util.List;

public class Po {

    private int id;
    private String name;
    private String noAge;
    private String no_age;
    private float no2;
    private double no3;
    private boolean flag;

    private String [] strs;
    private List<String> strs2;

    public float getNo2() {
        return no2;
    }

    public void setNo2(float no2) {
        this.no2 = no2;
    }

    public double getNo3() {
        return no3;
    }

    public void setNo3(double no3) {
        this.no3 = no3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoAge() {
        return noAge;
    }

    public void setNoAge(String noAge) {
        this.noAge = noAge;
    }

    public String getNo_age() {
        return no_age;
    }

    public void setNo_age(String no_age) {
        this.no_age = no_age;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public List<String> getStrs2() {
        return strs2;
    }

    public void setStrs2(List<String> strs2) {
        this.strs2 = strs2;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noAge='" + noAge + '\'' +
                ", no_age='" + no_age + '\'' +
                ", no2=" + no2 +
                ", no3=" + no3 +
                ", flag=" + flag +
                ", strs=" + Arrays.toString(strs) +
                ", strs2=" + strs2 +
                '}';
    }

    public Po() {
    }

    public Po(int id, String name, String noAge, String no_age, float no2, double no3, boolean flag, String[] strs, List<String> strs2) {
        this.id = id;
        this.name = name;
        this.noAge = noAge;
        this.no_age = no_age;
        this.no2 = no2;
        this.no3 = no3;
        this.flag = flag;
        this.strs = strs;
        this.strs2 = strs2;
    }
}
