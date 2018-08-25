1.配置rabbitmq-server yum源
/etc/yum.repos.d/rabbitmq-erlang.repo
官方github地址:https://github.com/rabbitmq/erlang-rpm

2.下载rabbitmq-server安装包(下载地址:http://www.rabbitmq.com/install-rpm.html)
下载: wget https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.7/rabbitmq-server-3.7.7-1.el7.noarch.rpm

3.安装erlang环境依赖
yum install epel-release
yum install unixODBC unixODBC-devel wxBase wxGTK SDL wxGTK-gl

4.安装rabbitmq-server
yum install rabbitmq-server-3.7.7-1.el7.noarch.rpm

5.安装yum版本锁定插件
yum install yum-plugin-versionlock

6.锁定erlang版本
yum versionlock erlang*

7.开机自启动
systemctl enable rabbitmq-server

启动:systemctl start rabbitmq-server

8.配置
启动插件:rabbitmq-plugins enable rabbitmq_management
新增用户:rabbitmqctl add_user root 123456
设置tag :rabbitmqctl set_user_tags root administrator
设置权限:rabbitmqctl set_permissions root ".*" ".*" ".*" 可配置  可写 可读权限




写在最后:软件的卸载
1.rpm -qa | grep -i erlang
2.



