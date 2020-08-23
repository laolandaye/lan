package com.lan.javase._7io._8java序列化和反序列化.java序列化机制;

import javax.xml.stream.events.EndDocument;
import java.io.*;

public class 使用Externalizable接口实现序列化 {
    public static void main(String[] args) throws Exception, IOException {
//        SerializeUser();
        DeSerializeUser();
    }
    //序列化方法
    private static void SerializeUser() throws FileNotFoundException, IOException {
        User1 user = new User1();
        user.setName("Java的架构师技术栈");
        user.setAge(24);
        //序列化对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\code\\vpn\\aaa.txt"));
        oos.writeObject(user);
        oos.close();
        System.out.println("序列化对象成功");
    }
    //反序列化方法
    private static void DeSerializeUser() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("D:\\code\\vpn\\aaa.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User1 newUser = (User1)ois.readObject();
        System.out.println("反序列化对象成功"+newUser.toString());
    }
}

class User1 implements Externalizable {
    //序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;
    public User1() {}
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
        age = in.readInt();
    }
}
