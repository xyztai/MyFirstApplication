package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String myurl = "http://d.welcomeuhere.top:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        设置软键盘不遮挡内容---webview
        AndroidBug5497Workaround.assistActivity(this);
        //        隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WebView webView = findViewById(R.id.webview);

//        Android webView加载Html5页面，JS不能调用问题和click事件无效的解决方法
        WebSettings s = webView.getSettings();
        s.setBuiltInZoomControls(true);//进行控制缩放
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//让webview只显示一列，也就是自适应页面大小，不能左右滑动；但在使用中发现，只针对4.4以下有效；
        s.setUseWideViewPort(true);//让webview支持标签的viewport属性，前端html页面适配屏幕一般都会用到这个设置
        s.setLoadWithOverviewMode(true);//缩放志屏幕大小
        s.setSavePassword(true);//是否保存密码
        s.setSaveFormData(true);//设置webview是否保存表单数据，默认true，保存数据
        s.setJavaScriptEnabled(true);//允许支持js

        s.setJavaScriptCanOpenWindowsAutomatically(true);//设置脚本是否允许自动打开弹窗，默认false，不允许；
        s.setGeolocationEnabled(true);//设置定位是否可用，默认为false
        s.setDomStorageEnabled(true);//启动webview的h5的本地存储功能，DOM存储api是否可用，默认false；
        webView.requestFocus();//把输入焦点放在调用这个方法的控件上
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

//        重写alert()方法，避免js的alert()无效
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                result.confirm();
                return true;
            }
        });

        webView.loadUrl(myurl);
//                系统默认会通过手机浏览器打开网页；为了能够直接通过webview显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        该接口，主要是给webview提供时机，让其选择是否对URLloading进行拦截；
//                        关于该接口的返回值，true--拦截webview加载url；false--允许webview加载url
                view.loadUrl(url);
                return true;
            }
        });

    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }*/
}