# ddns

#### 项目介绍
Java实现阿里云的动态域名解析（利用阿里云api，需要有公网ip）
现在都是动态IP，每次IP变了就得从新在阿里云后台改。用阿里云解析提供的接口，可以自动修改解析的IP。
需要公网IP，一般联通，电信宽带是有公网ip的，只不过路由器重启会更改公网ip，如果没有公网ip给客服打电话应该也能要到。

#### 软件架构
maven项目 
从http://chaipip.com/网站上爬取本机的公网ip
使用阿里云api 解析域名
#### 安装教程

target文件夹里的ddns-0.0.1-SNAPSHOT.jar包。
用解压软件打开ddns-0.0.1-SNAPSHOT.jar。
修改ddns.properties：

AccessKeyID=yourId（阿里控制台右上角里找）
AccessKeySecret=yourSecret（阿里控制台右上角里找）
DomainName=baidu.com

DomainName：是阿里注册的域名（格式：xxxx.com或xxxx.top等）

#### 使用说明

1. 首先需要阿里云的域名（便宜的6元首年）
2. 可以自己继续开发也可以直接修改配置文件，之后maven install ，target文件里的jar包就可以直接用。
3. 也可以按照上面直接修改jar包用

运行：（在Linux也可以放到crontab里自动运行）
java -jar ddns-0.0.1-SNAPSHOT.jar


