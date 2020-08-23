package com.lan.javase._10网络编程.openapi.wuxianxunhuan;

import com.lan.utils.JacksonUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户测试的，
 *      client 先发送信息
 *      收到 service 消息后解析数据
 */
public class OpenapiClient {

    public static void main(String[] args) {
        client();
    }

    /**
     * 客户端程序的编写
     */
    public static void client() {
        try {
            // 创建一个客户端的套接字，指定一个ip地址和端口。
            // 让它连接到我们服务器端上，在这个端口上等待连接的服务器进程
            // ip地址：你可以指定主机名，或者指定ip地址，让它直接返回。
            // 我们在编写程序的时候，可能没有两台机器，没有关系，我们可以在一台机器上，
            // 让我们服务端程序和客户端程序,通过网络进行通信。
            // 那么我们可以获取本地的ip地址，它有三种方式：
            // InetAddress.getByName("localhost")
            // InetAddress.getByName("127.0.0.1")是我们本地的回路地址
            // InetAddress.getByName(null)它也可以返回本地的IP地址
            //那么这三种方式，即使这我们的机器上没有网卡，也没有关系。
            //那么作为一台pc机,它都会有一个"127.0.0.1"作为本地的一个回路地址，那么我们用这个地址，
            //就可以测试我们的网络程序。
            //注：你在客户端发送连接请求的端口号，和服务器端等待连接的端口号一定要一致。
            //这就好像你打电话一样，那么我的分机号是6000，那么你打过来的时候你转这个分机号，你也要转6000
            //才能够和我进行通信，你不能说你随便转个分机号，你想转5000和我进行通信，那是不可能的。

            // 可以利用套接字获取输出流、输入流
             /*****************************************  请求token  ****************************************/
            Socket s = new Socket(InetAddress.getByName("localhost"), 6000);
            //输出流向服务器端发送数据
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();

            Map<String, String> dto = new HashMap();
            dto.put("type", "auth");
            dto.put("appKey", "297e7f556e370b00016e3728dd4307cd");
            dto.put("appSecret", "FzohF1O2u3MTAWg");
            String dtoStr = JacksonUtil.objectToJson(dto);
            os.write(dtoStr.getBytes());

            //输入流向服务器端读取数据
            byte[] buf = new byte[600];
            int len = is.read(buf);
            String res = new String(buf, 0, len);
            System.out.println("收到服务器发出的消息：" + res);
            String token = (String) JacksonUtil.jsonToPojo(res, Map.class).get("token");

            os.close();
            is.close();
            s.close();
            /*****************************************  请求token  ****************************************/

            /*****************************************  正式请求  ****************************************/
            Socket s2 = new Socket(InetAddress.getByName("localhost"), 6000);

            OutputStream os2 = s2.getOutputStream();
            InputStream is2 = s2.getInputStream();

            //输出流向服务器端发送数据
            Map<String, String> dto2 = new HashMap();
            dto2.put("type", "service");
            dto2.put("_url_", "testSocket/v1");
            dto2.put("token", token);
            dto2.put("appKey", "297e7f556e370b00016e3728dd4307cd");
            String dtoStr2 = JacksonUtil.objectToJson(dto2);
            os2.write(dtoStr2.getBytes());

            //输入流向服务器端读取数据
            byte[] buf2 = new byte[60000];
            int len2 = is2.read(buf2);
            String res2 = new String(buf2, 0, len2);
            System.out.println("收到服务器发出的消息：" + res2);
            os2.close();
            is2.close();
            s2.close();
            /*****************************************  正式请求  ****************************************/

            //完成通信之后，我们可以将输出流、输入流、套接字都关闭

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
