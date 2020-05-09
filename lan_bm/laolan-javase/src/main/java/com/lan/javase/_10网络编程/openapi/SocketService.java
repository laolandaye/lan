package com.lan.javase._10网络编程.openapi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {
    //搭建服务器端
    public static void main(String[] args) throws IOException{
        SocketService socketService = new SocketService();
        //1、a)创建一个服务器端Socket，即SocketService 
        socketService.oneServer();
    }
    public  void oneServer(){
        try{
            ServerSocket server=null;
            try{
                server=new ServerSocket(5209);
                //b)指定绑定的端口，并监听此端口。
                System.out.println("服务器启动成功");
                //创建一个ServerSocket在端口5209监听客户请求
            }catch(Exception e) {
                    System.out.println("没有启动监听："+e);
                    //出错，打印出错信息
            }
            Socket socket=null;
            try{
                socket=server.accept();
                //2、调用accept()方法开始监听，等待客户端的连接 
                //使用accept()阻塞等待客户请求，有客户
                //请求到来则产生一个Socket对象，并继续执行
            }catch(Exception e) {
                System.out.println("Error."+e);
                //出错，打印出错信息
            }
            //4、获取输出流，响应客户端的请求
            InputStream is = null;
            OutputStream out = null;
            while(true){
                // 3.通过socket 获取输入流
                is = socket.getInputStream();
                // 4.一次性读取数据
                // 4.1 创建字节数组
                byte[] b = new byte[1024];
                // 4.2 据读取到字节数组中.
                int len = is.read(b);
                // 4.3 解析数组,打印字符串信息
                String msg = new String(b, 0, len);
                System.out.println(msg);
                // =================回写数据=======================
                // 5. 通过 socket 获取输出流
                out = socket.getOutputStream();
                // 6. 回写数据
                out.write("我很好,谢谢你".getBytes());


            } //继续循环

            //5、关闭资源
//            out.close();
//            is.close();
//            socket.close(); //关闭Socket
//            server.close(); //关闭ServerSocket
        }catch(Exception e) {//出错，打印出错信息
            System.out.println("Error."+e);
        }
    }
}