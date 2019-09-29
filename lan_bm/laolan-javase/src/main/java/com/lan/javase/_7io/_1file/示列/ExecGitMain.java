package com.lan.javase._7io._1file.示列;

public class ExecGitMain {
    public static void main(String[] args) throws Exception {
        System.out.println("git pull 拉取最新代码：");
        ExecGitPull.getExeGitPull();
        System.out.println("本地安装maven：");
        ExecGitInstall.getExecGitInstall();
    }
}
