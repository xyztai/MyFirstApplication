## 参考：https://blog.csdn.net/weixuan_/article/details/131596297
该文章展示了一个简单的Android应用，使用WebView加载网页。在AndroidManifest.xml中设置了必要的网络权限，MainActivity.java中初始化并配置了WebView，包括启用JavaScript支持和处理alert对话框。此外，还提供了一个辅助类AndroidBug5497Workaround以防止软键盘遮挡输入框。


1、安装 Android Studio
2、安装 Android SDK
3、创建一个空工程，指定编程语言是java
4、新建一个activity，否则安装apk后会把apk当做后台服务
5、使用webview打开URL连接
6、编译生成apk

