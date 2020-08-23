package com.lan.javase._7io.示列;

public class ExecGitMain {
    public static void main(String[] args) throws Exception {
        // "gehua", bjsjfc， " spark " , cqrcb
        String con = "bjsjfc";
        System.out.println("git pull 拉取最新代码：");
        GitPull.getExeGitPull(con);
        System.out.println("本地安装maven：");
        MavenInstall.getExecGitInstall(con);
    }
}
