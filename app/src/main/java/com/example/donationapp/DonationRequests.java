package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DonationRequests extends AppCompatActivity {

   // String link;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_requests);

        //docs.google.com/spreadsheets/d/17UOhoISn3nIlu1JBy19edzIgVBU3QHd0nLqPzGC__Mc/edit?usp=sharing
        //webView load websites when online,load local files when offline
        mWebView = (WebView) findViewById(R.id.webviewdonreq);
        loadLink();
    }

    public void loadLink() {
        //how to enable javascript into webView
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        //copied link of google sheet for donation request form
        mWebView.loadUrl("https://docs.google.com/spreadsheets/d/17UOhoISn3nIlu1JBy19edzIgVBU3QHd0nLqPzGC__Mc/edit?usp=sharing");
    }

}
