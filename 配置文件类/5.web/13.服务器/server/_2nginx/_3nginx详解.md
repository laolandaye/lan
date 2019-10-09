##虚拟主机
http
    
    server（多个）：一个虚拟主机
        listen  80；  监听的端口
        service_name      manage.leyou.com  监听的域名
        
         proxy_set_header X-Forwarded-Host $host;  头信息
         proxy_set_header X-Forwarded-Server $host;
         proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        
        location
            root   html;    路径，（相对绝对都行）
            index  index.html index.htm;    默认访问首页

##反向代理
    upstream tomcat1 {
        server 192.168.100.101:8078;	指定转发服务器
    }

    server 
        location    / 请求映射规则，/ 代表映射一切地址
            proxy_pass http://127.0.0.1:9001;   代理转发。所以manage.leyou.com和80端口的请求转发给
            proxy_connect_timeout 600;
            proxy_read_timeout 600;
  

##负载均衡
    upstream tomcat1 {
        server 192.168.100.101:8078;	指定转发服务器
        server 192.168.100.101:8079 weight=2;	指定转发服务器，及指定权重
    }
