package net.codewithasp.webviewsplashscreen;

import net.codewithasp.webviewsplashscreen.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WebView myWebView = (WebView) findViewById(R.id.web_engine);

		myWebView.setWebViewClient(new MyBrowser());
		myWebView.clearCache(true);
		myWebView.clearHistory();

		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setLoadsImagesAutomatically(true);
		myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		myWebView.loadUrl("http://www.ruetchatbot.epizy.com");
	}

	private class MyBrowser extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			if (findViewById(R.id.splach_screen).getVisibility() == View.VISIBLE) {
				// show webview
				findViewById(R.id.main_view).setVisibility(View.VISIBLE);
				// hide splash screen
				findViewById(R.id.splach_screen).setVisibility(View.GONE);
			}
		}
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains("http://www.ruetchatbot.epizy.com")) {
              view.loadUrl(url);
            } else {
              Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
              startActivity(i);
            }
            return true;
          }
	}
}
