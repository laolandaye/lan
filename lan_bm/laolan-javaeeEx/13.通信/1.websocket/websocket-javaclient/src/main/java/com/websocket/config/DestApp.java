package com.websocket.config;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
/*
https://blog.csdn.net/mengxb12138/article/details/81016782
 */
public class DestApp {

    public static void main(String[] args) {
        try {
            // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@ticker";
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@depth20";
            String url = "wss://stream.binance.com:9443/stream?streams=ethbtc@ticker/ethbtc@depth20/trxbtc@ticker/trxbtc@depth20";
            URI uri = new URI(url);
            WebSocketClient mWs = new WebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {
                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {

                }

                @Override
                public void onError(Exception e) {

                }
            };
            mWs.connect();
            System.out.println("haha");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}