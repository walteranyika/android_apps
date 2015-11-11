package com.walter.workingwithwebview;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	WebView mywebview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 mywebview= (WebView) findViewById(R.id.webview);
		 mywebview.loadUrl("http://www.androidaspect.com/?m=1");
		 WebSettings webSettings = mywebview.getSettings();
		 webSettings.setJavaScriptEnabled(true);
		 webSettings.setBuiltInZoomControls(true);
		 mywebview.setWebViewClient(new WebViewClient());

	}
	private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
            }
     }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && mywebview.canGoBack()) {
	        mywebview.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
