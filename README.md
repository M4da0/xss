### 一、项目介绍

---

> XssGet是一个采用 SpringBoot+Vue 开发的 XSS payload 管理与 数据接收平台

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
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617000937249-f200baf2-6f53-4035-97e1-109d1139ac2a.png#align=left&display=inline&height=222&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=444&originWidth=932&size=136564&status=done&style=none&width=466)
如果你通过直接运行 jar 的方式启动项目，需要在启动时动态替换配置文件中的内容，具体内容请参照 [springboot启动jar修改配置文件信息 ](https://blog.csdn.net/weixin_40300139/article/details/107999094) 。


注意：启动应用前要利用 mysql 数据库创建名为 xss 的 database。创建完数据库后，第一次启动应用会自动为你创建相关的表。


如下：启动后应用的登录表单账号密码为 `wang:123` ，服务器端口为 8888 

![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617001691857-ff254d79-6c38-4b36-8b8e-b09f27cd2f43.png#align=left&display=inline&height=245&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=489&originWidth=1344&size=79999&status=done&style=none&width=672)
#### 2.2 自己打包运行
自己下载源码到 idea 中进行本地运行或打包运行。


#### 2.3 访问
项目启动完毕后访问登录界面即可。[http://应用ip:应用端口/](http://192.168.43.153:8433/home)login.html


### 三、项目演示

---

> 如果使用此应用，请务必仔细阅读本部分。主页面在：[http://应用ip:应用端口/home](http://192.168.43.153:8433/home)



#### 3.1 登录界面
默认登录账号密码为 madao:1234，利用 Spring Security 进行认证与授权。
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617001909814-33adfecf-1772-4413-8e76-49d4fd5c217b.png#align=left&display=inline&height=449&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=898&originWidth=1604&size=645851&status=done&style=none&width=802)
#### 3.2 接收数据界面
这里显示受害者遭受 `xss` 攻击并向我们的服务器发送包含 `ip`、`数据`等信息：

![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617002378961-11837798-25f7-4d8f-a7d2-e005a85a8f31.png#align=left&display=inline&height=342&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=684&originWidth=1896&size=40309&status=done&style=none&width=948)


hover `HTTP` 请求消息，查看详细内容：
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617002589209-d39d6778-5801-4276-92b3-0308a2b48af8.png#align=left&display=inline&height=331&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=662&originWidth=1893&size=54676&status=done&style=none&width=946.5)


受害者受骗时，基于  `WebSocket` ，实现消息推送：
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617002786418-2db5e3e1-1bc5-4f9a-85b7-b9c725e83b4f.png#align=left&display=inline&height=458&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=916&originWidth=1872&size=52537&status=done&style=none&width=936)


_更新：_1.增加了全局过滤器，会拦截所有访问该应用的请求，并在数据接收界面展示。2.接收数据界面新增了方法的展示。3.利用CDN优化了主界面的访问速度。如下：
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617092151967-f03b8f1e-2991-42c5-ae7f-83afd627c802.png#align=left&display=inline&height=283&margin=%5Bobject%20Object%5D&name=image.png&originHeight=566&originWidth=1183&size=202305&status=done&style=none&width=591.5)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617092125578-2603e1f2-3bc8-4477-b50b-e88b4c96a95c.png#align=left&display=inline&height=351&margin=%5Bobject%20Object%5D&name=image.png&originHeight=701&originWidth=1901&size=91909&status=done&style=none&width=950.5)






#### 3.3 管理Payloads
这里实现 payload 的添加、修改、删除、收藏等功能。这里的 payload 参考了 [BlueLotus_XSSReceiver](https://github.com/trysec/BlueLotus_XSSReceiver) 。
还想再添加的，算了，就写这么多吧。


添加 payload（注意文件名唯一）:
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617004682013-6779a954-a405-43df-a280-06936fc863d8.png#align=left&display=inline&height=450&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=900&originWidth=1879&size=58881&status=done&style=none&width=939.5)


查看 payload 详情与修改：
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617004763075-37ad04c2-962f-41d8-a5f8-e52b3867c62c.png#align=left&display=inline&height=479&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=959&originWidth=1896&size=87803&status=done&style=none&width=948)


一键生成 payload:
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617004823198-ab4e9132-afd6-4147-91c1-dc5dc98368b1.png#align=left&display=inline&height=483&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=965&originWidth=1899&size=78703&status=done&style=none&width=949.5)


收藏 payload:
点击收藏按钮，即可在 我的Payloads 中查看到刚刚收藏的Payload。


#### 3.4 钉钉消息提醒
当受害者发起请求时，通过钉钉机器人提醒。可以手动选择钉钉通知的开启与关闭，可以选择通知的格式。
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617005142169-44f684fd-0c4f-412b-b0c1-45c59c3a016f.png#align=left&display=inline&height=437&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=873&originWidth=1885&size=202016&status=done&style=none&width=942.5)
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617005555752-178e7857-d3e2-4b36-b5e3-93f2c2d6ea1c.png#align=left&display=inline&height=386&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=771&originWidth=1379&size=44733&status=done&style=none&width=689.5)


#### 3.5 小工具
目前只写了 Base64，其他的还在写，挖个坑。

![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1239731/1617005263429-49f30417-c9c2-4103-9106-324ae341b952.png#align=left&display=inline&height=390&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=780&originWidth=1914&size=29964&status=done&style=none&width=957)
### 四、写在后面

---

自己的水平不高，刚学没多久，大佬轻喷。项目仅供学习使用，严禁用于非法用途。
