# FtpCrack
java版Ftp破解器
通过随机生成指定位数的密码，破解ftp，破解成功后自动提交密码到服务器
同时支持指定线程数量
# 使用:
1.部署ftp.php到你的php服务器;
2.更改Main.java中FTP_ADDRESS(目标ftp地址)、FTP_PORT(ftp端口)、SUBMIT_ADDRESS("密码接受服务器地址")为相应的值;
3.执行:javac Main.java编译
4.执行:java Main 密码位数 线程数量
5.破解成功后你的php服务器出现:名称为"ftp_密码"的文件夹
#本项目仅作学习使用，请勿用于非法用途