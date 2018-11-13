package info.anwesha2k18.iitp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import info.anwesha2k18.iitp.R;

public class webActivity extends AppCompatActivity {
    WebView webView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url=bundle.getString("link");
        webView = (WebView)findViewById(R.id.webview);
        webView.loadUrl(url);
    }
}
