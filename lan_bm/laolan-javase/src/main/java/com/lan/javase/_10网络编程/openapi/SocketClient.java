package com.lan.javase._10网络编程.openapi;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("客户端 发送数据");
            // 1.创建 Socket ( ip , port ) , 确定连接到哪里.
            Socket client = new Socket("localhost", 5209);
            // 2.通过Scoket,获取输出流对象
            OutputStream os = client.getOutputStream();
            // 3.写出数据.
            os.write("你好么? tcp ,我来了".getBytes());
            // ==============解析回写=========================
            // 4. 通过Scoket,获取 输入流对象
            InputStream in = client.getInputStream();
            // 5. 读取数据数据
            byte[] b = new byte[100];
            int len = in.read(b);
            System.out.println(new String(b, 0, len));
            // 6. 关闭资源 .
            in.close();
            os.close();
            client.close();
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }

}