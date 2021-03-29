### 一、项目介绍

---

> XssGet是一个采用 SpringBoot+Vue 开发的 XSS payload 管理与 数据接收平台

**后端技术栈：**

1. Spring Boot
1. Spring Security
1. MyBatis
1. MySQL
1. WebSocket
1. Fastjson
1. ...




**前端技术栈：**

1. Vue
1. ElementUI
1. axios
1. vue-router
1. WebSocket
1. ...



### 二、项目部署

---

> 如果运行此应用，请务必仔细阅读本部分。

#### 2.1 下载jar包运行
该项目的关于数据库、登录账号密码、服务器的端口等配置均在 `application.yaml` 文件中，默认登录用户密码为 `madao:1234`。如下：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164212633-979518000.png)
如果你通过直接运行 jar 的方式启动项目，需要在启动时动态替换配置文件中的内容，具体内容请参照 [springboot启动jar修改配置文件信息 ](https://blog.csdn.net/weixin_40300139/article/details/107999094) 。


注意：启动应用前要利用 mysql 数据库创建名为 xss 的 database。创建完数据库后，第一次启动应用会自动为你创建相关的表。


如下：启动后应用的登录表单账号密码为 `wang:123` ，服务器端口为 8888 

![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164213254-714050026.png)
#### 2.2 自己打包运行
自己下载源码到 idea 中进行本地运行或打包运行。


### 三、项目演示

---

> 如果使用此应用，请务必仔细阅读本部分。主页面在：[http://应用ip:应用端口/home](http://192.168.43.153:8433/home)



#### 3.1 登录界面
默认登录账号密码为 madao:1234，利用 Spring Security 进行认证与授权。
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164213643-208143601.png)
#### 3.2 接收数据界面
这里显示受害者遭受 `xss` 攻击并向我们的服务器发送包含 `ip`、`数据`等信息：

![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164214209-634083811.png)


hover `HTTP` 请求消息，查看详细内容：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164214463-1057379065.png)


受害者受骗时，基于  `WebSocket` ，实现消息推送：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164214724-128126676.png)


注意请求格式：接收面板数据目前仅展示发送到 http://该应用的ip地址:该应用的端口/recive?cookie=xxx 的请求信息。例如：我们服务器的 url 为 http://192.168.43.153，受害者受 xss 攻击去请求 [http://192.168.43.153/recive?cookie=whoami ](http://192.168.43.153)。那么该请求将被本应用拦截，并在接受界面展示，如下：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164215008-1443037738.png)
#### 3.3 管理Payloads
这里实现 payload 的添加、修改、删除、收藏等功能。这里的 payload 参考了 [BlueLotus_XSSReceiver](https://github.com/trysec/BlueLotus_XSSReceiver) 。
还想再添加的，算了，就写这么多吧。


添加 payload（注意文件名唯一）:
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164215286-2016973871.png)


查看 payload 详情与修改：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164215602-1685616679.png)


一键生成 payload:
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164215998-2115960107.png)


收藏 payload:
点击收藏按钮，即可在 我的Payloads 中查看到刚刚收藏的Payload。


#### 3.4 钉钉消息提醒
当受害者发起请求时，通过钉钉机器人提醒。可以手动选择钉钉通知的开启与关闭，可以选择通知的格式。
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164216309-1260610925.png)
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164216640-1544304062.png)


#### 3.5 小工具
目前只写了 Base64，其他的还在写，挖个坑。

![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164216920-1730750678.png)
### 四、写在后面

---

自己的水平不高，刚学没多久，大佬轻喷。项目仅供学习使用，严禁用于非法用途。

QQ：
![图片.png](https://img2020.cnblogs.com/blog/1859858/202103/1859858-20210329164217164-292083990.png)